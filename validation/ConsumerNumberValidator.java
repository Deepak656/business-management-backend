// ConsumerNumberValidator.java
package com.example.lpgmanagement.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConsumerNumberValidatorImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsumerNumberValidator {
    String message() default "Invalid consumer number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

