package com.dev.condominio.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.UUID;

public class Owner {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "cond_id", nullable = true)
    private Cond cond;

    @Column(nullable = false, unique = false)
    private Boolean subscriptionActive;

    private Subscription subscription;
}
