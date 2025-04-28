package com.dev.condominio.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserMinimalResponse {
    private String name;
    private Short bloco;
    private Short apt;
}
