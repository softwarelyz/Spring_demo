package com.gdnf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by admin on 2018/1/22.
 */
public class BookNameValidator implements ConstraintValidator<BookName,String> {

    @Override
    public void initialize(BookName constraintAnnotation) {
        System.out.println("初始化..");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.indexOf("haha") == -1) {
            return false;
        }
        return true;
    }
}
