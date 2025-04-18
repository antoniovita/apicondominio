package com.dev.condominio.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Payment {

    @Id
    private UUID id;

    @Column(nullable = true, unique = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = false)
    private String status;

    @Column(nullable = true, unique = true)
    private String receiptUrl;

    @OneToOne(mappedBy = "payment")
    private Reserve reserve;


}
