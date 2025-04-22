package com.dev.condominio.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;
import com.dev.condominio.domain.security.Permission;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = false)
    private Short bloco;

    @Column(nullable = false, unique = false)
    private Integer apt;

    @Column(nullable = false, unique = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "cond_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cond cond;

    @OneToMany(mappedBy = "user_id")
    private List<Reserve> reserve;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "permission", nullable = false)
    private Set<Permission> permissions = new HashSet<>();






}
