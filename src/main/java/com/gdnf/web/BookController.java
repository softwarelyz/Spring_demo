package com.gdnf.web;

import com.gdnf.dao.AuthorDAO;
import com.gdnf.dao.BookDAO;
import com.gdnf.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.nio.charset.Charset;

/**
 * Created by admin on 2018/1/15.
 */
@Controller
public class BookController extends WebMvcConfigurerAdapter {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @GetMapping("/index")
    public String listAll(Model model){
        model.addAttribute("books", bookDAO.findAll());
        return "book_list";
    }

    @GetMapping("/book/add")
    public String addBook(Book book,Model model ){
        System.out.println("changg ");
        model.addAttribute("authors",authorDAO.findAll());
        return "book_add";
    }

    @PostMapping("/book/store")
    public String addBook(@Valid Book book, BindingResult errors,Model model) {
        if (book.getAuthor() == null || book.getAuthor().getId() < 1) {
            errors.rejectValue("author", null, "您需要填写作者信息");
        }
        if (errors.hasErrors()) {
            model.addAttribute("authors", authorDAO.findAll());
            return "book_add";
        }
        bookDAO.save(book);
        return "redirect:/index";
    }

    @GetMapping("/book/delete")
    public String deleteBook(Long id){
        bookDAO.delete(id);
        return "redirect:/index";
    }

    @GetMapping("book/update")
    public String toEdie(Model model,Long id){
        Book book=bookDAO.getBooksById(id);
        model.addAttribute("book",book);
        model.addAttribute("authors",authorDAO.findAll());
        return "book_update";
    }

    @PostMapping("/update")
    public String edit(Book book){
        bookDAO.save(book);
        return "redirect:/index";
    }

}
