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
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @NotNull
    private Short apt;

    @NotNull
    private Short bloco;

    @NotNull
    private Set<UUID> roleIds;

    @NotNull
    private UUID condId;
}
