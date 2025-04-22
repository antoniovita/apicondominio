package com.dev.condominio.domain.security.types;

import com.dev.condominio.domain.security.Permission;
import java.util.EnumSet;
import java.util.Set;

public enum UserType {

    SINDICO(EnumSet.of(
            Permission.MANAGE_USER,
            Permission.POST_COMMUNICATES,
            Permission.EDIT_COMMUNICATES,
            Permission.VOTE,
            Permission.CREATE_RESERVE
    )),

    DEFAULT(EnumSet.of(
            Permission.VOTE,
            Permission.CREATE_RESERVE
    ));

    private final Set<Permission> defaultPermissions;

    UserType(Set<Permission> defaultPermissions) {
        this.defaultPermissions = defaultPermissions;
    }

    public Set<Permission> getDefaultPermissions() {
        return defaultPermissions;
    }
}
