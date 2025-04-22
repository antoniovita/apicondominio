package com.dev.condominio.domain.security;

public enum Permission {

    POST_COMMUNICATES("Postar comunicados"),
    EDIT_COMMUNICATES("Editar comunicados"),
    VOTE("Participar de votações"),
    CREATE_RESERVE("Criar reservas"),
    MANAGE_USER("Gerenciar usuários");

    private final String description;

    Permission(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
