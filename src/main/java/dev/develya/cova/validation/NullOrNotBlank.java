package dev.develya.cova.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidatorConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "Field must not be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
