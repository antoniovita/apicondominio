package com.dev.condominio.dto.user;

import com.dev.condominio.domain.security.types.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public UserType type;

    @NotNull
    public UUID condId;
}
