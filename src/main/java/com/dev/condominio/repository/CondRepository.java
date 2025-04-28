package com.dev.condominio.repository;

import com.dev.condominio.domain.model.Cond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CondRepository extends JpaRepository<Cond, UUID>  {
}
