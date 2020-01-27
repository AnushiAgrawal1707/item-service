package com.mykart.validators.number;

import com.mykart.validators.IdValiadtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= PhoneValidator.class)
public @interface Phone {

    String message() default "Phone number is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
