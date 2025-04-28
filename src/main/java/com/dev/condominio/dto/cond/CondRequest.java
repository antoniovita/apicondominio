package com.dev.condominio.dto.cond;

import com.dev.condominio.domain.security.validation.annotation.CNPJ;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondRequest {

    @NotBlank
    private String name;

    private String imgUrl;

    @NotNull
    private String address;

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotNull
    private UUID ownerId;
}
