package com.dev.condominio.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;


@Table(name = "table_cond")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cond {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = true, unique = true)
    private String imgUrl;

    @Column(nullable = false, unique = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String cnpj;

    // all users including owner
    @OneToMany(mappedBy = "cond")
    private List<User> user;

    @OneToMany(mappedBy = "cond", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Place> place;

    @OneToMany(mappedBy = "cond", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Payment> payment;

    @OneToMany(mappedBy = "cond", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Reserve> reserve;

    //just the user that created the cond
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    @OneToMany(mappedBy = "cond")
    private List<Communicates> communicates;

}
