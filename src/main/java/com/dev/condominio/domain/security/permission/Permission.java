package com.dev.condominio.domain.security.permission;

public enum Permission {

    POST_COMMUNICATES("Post communicates."),
    EDIT_COMMUNICATES("Edit communicates."),
    VOTE("Participate in votations."),
    CREATE_RESERVE("Create reserves"),
    MANAGE_USER("Manage users."),
    MANAGE_EVERYTHING("Manage everything. Company only."),
    CREATE_VOTATION("Create votation.");

    private final String description;

    Permission(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
