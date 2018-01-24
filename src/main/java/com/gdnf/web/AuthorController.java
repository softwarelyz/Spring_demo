package com.gdnf.web;

import com.gdnf.dao.AuthorDAO;
import com.gdnf.entity.Author;
import com.gdnf.entity.Book;
import com.gdnf.validator.AuthorValidatorOfSpringWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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

    @GetMapping("/author_list")
    public String list(Model model) {
        model.addAttribute("authors", authorDAO.findAll());
        return "author_list";
    }


    @GetMapping("/author_add")
    private String add(Author author){
        return "add_author";
    }

    @PostMapping("/author_add")
    private String store(@Valid Author author,BindingResult result,RedirectAttributesModelMap flash) {
        if(result.hasErrors()) {
            return "add_author";
        }
        authorDAO.save(author);
        flash.addFlashAttribute("msg", "签约成功!");
        return "redirect:author_list";
    }

    @GetMapping("/author_delete")
    public String deleteBook(Long id,RedirectAttributesModelMap flash){
        authorDAO.delete(id);
        flash.addFlashAttribute("msg", "删除成功!");
        return "redirect:author_list";
    }

    @GetMapping("/update")
    public String toEdie(Model model,Long id){
        model.addAttribute("authors",authorDAO.getOne(id));
        return "update_author";
    }

    @PostMapping("/author_update")
    public String edit(@Valid Author author, BindingResult result, Model model, RedirectAttributesModelMap flash){
        if (result.hasErrors()){
            return "update_author";
        }
        authorDAO.save(author);
        flash.addFlashAttribute("msg", "修改成功!");
        return "redirect:/author_list";
    }
}
