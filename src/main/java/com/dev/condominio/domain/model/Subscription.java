package com.dev.condominio.domain.model;

import com.dev.condominio.domain.model.Owner;
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
@Table(name = "table_subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String stripeSubscriptionId;

    @Column(nullable = false, unique = false)
    private String stripePriceId;

    @Column(nullable = false, unique = false)
    private String status;


    private boolean cancelAtPeriodEnd;

    private LocalDateTime currentPeriodStart;
    private LocalDateTime currentPeriodEnd;


    private String receiptUrl;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Owner owner;

}
