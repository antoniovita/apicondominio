package com.dev.condominio.dto.user;

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
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    public String name;

    @NotBlank
    public String cpf;

    @NotBlank
    public String email;

    @NotNull
    public Short apt;

    @NotNull
    public Short bloco;

    @NotNull
    public Set<String> roles;

    @NotNull
    public UUID condId;
}
