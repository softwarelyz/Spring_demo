package com.gdnf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by admin on 2018/1/22.
 */
public class PhoneNumValidator implements ConstraintValidator<PhoneNum,String> {
    @Override
    public void initialize(PhoneNum constraintAnnotation) {
        System.out.println("初始化...");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean phone = value.matches("1[3,5,6,7,8][0-9]{9,9}");
        if(value == null || phone == false) {
            return false;
        }
        return true;
    }
}
