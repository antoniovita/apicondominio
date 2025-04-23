package com.dev.condominio.dto.user;

import com.dev.condominio.domain.security.types.UserType;
import com.dev.condominio.domain.security.Permission;
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
    private UserType type;
    private UUID condId;
    private Set<Permission> permissions;
}
