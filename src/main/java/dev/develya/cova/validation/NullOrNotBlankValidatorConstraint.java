package dev.develya.cova.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidatorConstraint implements ConstraintValidator<NullOrNotBlank, String> {
    @Override
    public void initialize(NullOrNotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || !value.trim().isEmpty();
    }
}
