package com.dev.condominio.domain.security.validation.validator;

import com.dev.condominio.domain.security.validation.annotation.CPF;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CPF, String> {

    private static final String CPF_REGEX = "\\d{11}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(CPF_REGEX);
    }
}
