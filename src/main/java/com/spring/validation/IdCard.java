package com.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdCardValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface IdCard {
    String message() default "{com.spring.validation.IdCard.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}