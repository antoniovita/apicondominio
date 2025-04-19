package com.dev.condominio.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String receiptUrl;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
