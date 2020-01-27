package com.mykart.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdValiadtor implements ConstraintValidator<Identification, Integer> {

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext arg1) {
		if(!(id <= 0))
			return true;
		return false;
	}

}
