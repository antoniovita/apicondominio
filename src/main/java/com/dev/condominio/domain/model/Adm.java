package com.dev.condominio.domain.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Adm {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "cond_id", nullable = false)
    private Cond cond;
}
