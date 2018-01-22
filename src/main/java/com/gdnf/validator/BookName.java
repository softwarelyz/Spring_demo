package com.gdnf.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by admin on 2018/1/22.
 */
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = BookNameValidator.class)
public @interface BookName {

    String message() default "书名的格式不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
