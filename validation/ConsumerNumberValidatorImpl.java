package com.example.lpgmanagement.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConsumerNumberValidatorImpl.java
@Component
public class ConsumerNumberValidatorImpl implements ConstraintValidator<ConsumerNumberValidator, String> {

    private static final String CONSUMER_NUMBER_PATTERN = "^[A-Z0-9]{10}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(CONSUMER_NUMBER_PATTERN);
    }
}
