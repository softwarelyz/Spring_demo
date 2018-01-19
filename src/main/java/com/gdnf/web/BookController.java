package com.gdnf.web;

import com.gdnf.dao.BookDAO;
import com.gdnf.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

/**
 * Created by admin on 2018/1/15.
 */
@Controller
public class BookController extends WebMvcConfigurerAdapter {

    @Autowired
    BookDAO bookDAO = null;

    @GetMapping("/index")
    public String listAll(Model model){
        model.addAttribute("books", bookDAO.findAll());
        return "book_list";
    }

    @GetMapping("/book/add")
    public String addBook(){
        return "book_add";
    }

    @PostMapping("/book/store")
    public String addBook(Book book){
        bookDAO.save(book);
        System.out.println(book.getName());
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
        return "book_update";
    }

    @PostMapping("/update")
    public String edit(Book book){
        bookDAO.save(book);
        return "redirect:/index";
    }



}
