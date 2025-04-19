package com.dev.condominio.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Cond {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true, unique = true)
    private String imgUrl;

    @Column(nullable = false, unique = true)
    private Integer accessNumber;

    @OneToMany(mappedBy = "cond")
    private List<Adm> adm;

    @OneToMany(mappedBy = "cond")
    private List<User> user;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "cond")
    private List<Place> place;

    @OneToMany(mappedBy = "cond")
    private List<Payment> payment;

    @OneToMany(mappedBy = "cond")
    private List<Reserve> reserve;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

}
