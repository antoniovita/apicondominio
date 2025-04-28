package com.dev.condominio.domain.security.validation.annotation;


import com.dev.condominio.domain.security.validation.validator.CnpjValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CnpjValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CNPJ {
    String message() default "CNJP inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
