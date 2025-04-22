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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true, unique = true)
    private String imgUrl;

    @Column(nullable = false, unique = true)
    private Integer accessNumber;

    @OneToMany(mappedBy = "cond")
    private List<Adm> adm;

    @OneToMany(mappedBy = "cond")
    private List<User> user;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "cond")
    private List<Place> place;

    @OneToMany(mappedBy = "cond")
    private List<Payment> payment;

    @OneToMany(mappedBy = "cond")
    private List<Reserve> reserve;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Owner owner;

    @OneToMany(mappedBy = "cond")
    private List<Communicates> communicates;

}
