package com.dev.condominio.repository;

import com.dev.condominio.domain.model.Cond;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CondRepository extends JpaRepository<Cond, UUID>  {
}
