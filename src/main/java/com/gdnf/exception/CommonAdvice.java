package com.gdnf.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2018/1/24.
 */
@Component
@ControllerAdvice
public class CommonAdvice {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleEmptyResult(HttpServletRequest request, Model model, Exception ex) {
        model.addAttribute("err", "[error] : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Model model, Exception e){
        model.addAttribute("err","[error] : "+ e.getMessage());
        return "error";
    }

}
