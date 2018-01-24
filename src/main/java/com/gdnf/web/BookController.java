package com.gdnf.web;

import com.gdnf.dao.AuthorDAO;
import com.gdnf.dao.BookDAO;
import com.gdnf.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
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
    public String addBook(@Valid Book book, BindingResult errors,RedirectAttributesModelMap flash,Model model) {
        if (invalidBook(book,errors,model)){
            return "book_add";
        }
        bookDAO.save(book);
        flash.addFlashAttribute("msg", "添加成功!");
        flash.addFlashAttribute("msgType", "success");
        return "redirect:/index";
    }

    @GetMapping("/book/delete")
    public String deleteBook(Long id,RedirectAttributesModelMap flash){
        bookDAO.delete(id);
        flash.addFlashAttribute("msg","删除成功");
        return "redirect:/index";
    }

    @GetMapping("book/update")
    public String toEdie(Model model,Long id){
        model.addAttribute("book",bookDAO.getOne(id));
        model.addAttribute("authors",authorDAO.findAll());
        return "book_update";
    }

    @PostMapping("/update")
    public String edit(@Valid Book book, BindingResult result, Model model,RedirectAttributesModelMap flash){
        if (invalidBook(book,result,model)){
            return "book_update";
        }
        bookDAO.save(book);
        flash.addFlashAttribute("msg", "修改成功!");
        return "redirect:/index";
    }

    private boolean invalidBook(Book book, Errors result, Model model) {
        if (book.getAuthor() == null || book.getAuthor().getId() < 1) {
            result.rejectValue("author", null, "您需要填写作者的信息哦!");
        }
        if(result.hasErrors()) {
            model.addAttribute("authors",authorDAO.findAll());
        }
        return result.hasErrors();
    }

}
