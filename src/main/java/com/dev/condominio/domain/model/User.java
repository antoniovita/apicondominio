package com.dev.condominio.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = false)
    private Short bloco;

    @Column(nullable = false, unique = false)
    private Integer apt;

    @Column(nullable = false, unique = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "cond_id", nullable = false)
    private Cond cond;

    @OneToMany(mappedBy = "user_id")
    private List<Reserve> reserve;




}
