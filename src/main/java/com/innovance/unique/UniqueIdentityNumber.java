package com.innovance.unique;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = {METHOD, FIELD})
@Retention(value = RUNTIME)
@Constraint(validatedBy = { UniqueIdentityNumberValidator.class})
public @interface UniqueIdentityNumber {
    String message() default "{Identity Number must be unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
