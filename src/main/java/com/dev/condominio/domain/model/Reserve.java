package com.dev.condominio.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reserve")
public class Reserve {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private LocalDateTime date;

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
