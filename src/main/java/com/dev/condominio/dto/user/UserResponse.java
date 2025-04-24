package com.dev.condominio.dto.user;

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
public class UserResponse {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private Short apt;
    private Short bloco;
    private Set<String> roles;
    private UUID condId;
    private Set<String> permissions;
}
