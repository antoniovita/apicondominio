package com.dev.condominio.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "warning")
public class Communicates {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = false, unique = false)
    private String content;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "cond_id")
    private Cond cond;

}
