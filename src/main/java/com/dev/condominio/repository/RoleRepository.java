package com.dev.condominio.repository;

import com.dev.condominio.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID>  {
    Optional<Role> findByName(String name);
}
