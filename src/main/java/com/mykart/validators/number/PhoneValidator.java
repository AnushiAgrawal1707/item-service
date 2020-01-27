package com.mykart.validators.number;
import java.util.regex.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone,Integer>
{
    @Override
    public boolean isValid(Integer phone, ConstraintValidatorContext constraintValidatorContext) {
        String s=phone.toString();

        if(validates(s))
            return true;
        return false;


    }

    public boolean validates(String s)
    {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
       Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));


    }
}