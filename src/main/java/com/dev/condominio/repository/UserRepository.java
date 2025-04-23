package com.dev.condominio.repository;

import com.dev.condominio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
