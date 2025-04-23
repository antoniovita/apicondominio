package com.dev.condominio.domain.model;

import com.dev.condominio.domain.security.types.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import com.dev.condominio.domain.security.Permission;

@Entity
@Table(name = "table_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = false)
    private String password;

    @Column(nullable = false, unique = false)
    private Short bloco;

    @Column(nullable = false, unique = false)
    private Short apt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;


    @ManyToOne
    @JoinColumn(name = "cond_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cond cond;

    @OneToMany(mappedBy = "user")
    private List<Reserve> reserve;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "permission", nullable = false)
    private Set<Permission> permissions = new HashSet<>();

    @PreUpdate
    private void assignDefaultPermissions() {
        if (permissions == null || permissions.isEmpty()) {
            this.permissions = new HashSet<>(type.getDefaultPermissions());
        }
    }






}
