package com.dev.condominio.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "table_warning")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cond cond;

}
