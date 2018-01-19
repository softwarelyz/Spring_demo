package com.gdnf.web;

import com.gdnf.dao.BookDAO;
import com.gdnf.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by admin on 2018/1/18.
 */
@Controller
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/thymeleaf")
    public String getText(Model model){
        Iterable<Book> books = bookDAO.findAll();
        model.addAttribute("books",books);
        model.addAttribute("age",20);
        model.addAttribute("country","中国");
        model.addAttribute("now",new Date());
        return "tutor/thymeleaf";
    }

}
