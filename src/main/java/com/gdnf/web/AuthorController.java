package com.gdnf.web;

import com.gdnf.dao.AuthorDAO;
import com.gdnf.entity.Author;
import com.gdnf.validator.AuthorValidatorOfSpringWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by admin on 2018/1/19.
 */
@Controller
public class AuthorController {
    @Autowired
    private AuthorDAO authorDAO;

    /*@InitBinder // 是在所有方法之前去执行的一个方法，可以做很多初始化方面的工作
    public void init(DataBinder binder) {
        binder.addValidators(new AuthorValidatorOfSpringWay());
    }*/

    @GetMapping("/author_add")
    private String add(Author author){
        return "add_author";
    }

    @PostMapping("/author_add")
    private String store(@Valid Author author, BindingResult result,Model model){

        if(result.hasErrors()) {
            return "add_author";
        }
        authorDAO.save(author);
        //model.addAttribute("authors",authorDAO.findAll());
        return "redirect:index";
    }
}
