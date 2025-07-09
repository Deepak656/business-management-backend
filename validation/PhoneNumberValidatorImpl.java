package com.example.lpgmanagement.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// PhoneNumberValidatorImpl.java
@Component
public class PhoneNumberValidatorImpl implements ConstraintValidator<PhoneNumberValidator, String> {

    private static final String PHONE_NUMBER_PATTERN = "^[0-9]{10}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(PHONE_NUMBER_PATTERN);
    }
}
