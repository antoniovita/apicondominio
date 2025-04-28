package com.dev.condominio.domain.security.validation.validator;

import com.dev.condominio.domain.security.validation.annotation.CNPJ;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<CNPJ, String> {

    private static final String CNPJ_REGEX = "\\d{14}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null ) {
            return true;
        }
        return value.matches(CNPJ_REGEX);
    }
}
