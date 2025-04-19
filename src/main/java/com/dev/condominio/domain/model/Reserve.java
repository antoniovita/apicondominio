package com.dev.condominio.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Reserve {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "cond_id", nullable = false)
    private Cond cond;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;
}
