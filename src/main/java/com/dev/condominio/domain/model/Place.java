package com.dev.condominio.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = false)
    private String type;

    @OneToMany(mappedBy = "place_id")
    private Reserve reserve;

}
