package com.gdnf.validator;

import com.gdnf.entity.Author;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by admin on 2018/1/22.
 */
public class AuthorValidatorOfSpringWay implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Author.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Author author = (Author)target;

        if (author.getName() == null || author.getName().length() < 5){
            errors.rejectValue("name",null,"名字不能为空，并且要保持 5 位以上。");
        }

        if (!Pattern.matches("1[35678][0-9]{9}",author.getTelephone())){
            errors.rejectValue("telephone", null, "电话号的格式不正确。");
        };

    }
}
