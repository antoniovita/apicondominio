package com.dev.condominio.domain.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.UUID;

public class Subscription {
    @Id
    private UUID id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String receiptUrl;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
