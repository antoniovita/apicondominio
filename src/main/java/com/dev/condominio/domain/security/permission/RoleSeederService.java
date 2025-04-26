package com.dev.condominio.domain.security.permission;

import com.dev.condominio.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleSeederService {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void createDefaultRoles() {

        // who created the cond entity
        if (roleRepository.count() == 0) {
            Role ownerRole = new Role(
                    UUID.randomUUID(),
                    "ROLE_OWNER",
                    Set.of(
                            Permission.POST_COMMUNICATES,
                            Permission.EDIT_COMMUNICATES,
                            Permission.VOTE,
                            Permission.CREATE_RESERVE,
                            Permission.MANAGE_USER
                    )
            );

            // residents
            Role defaultRole = new Role(
                    UUID.randomUUID(),
                    "ROLE_DEFAULT",
                    Set.of(
                            Permission.VOTE,
                            Permission.CREATE_RESERVE
                    )
            );

            //people who work in the cond
            Role admRole = new Role(
                    UUID.randomUUID(),
                    "ROLE_ADM",
                    Set.of(
                            Permission.POST_COMMUNICATES,
                            Permission.EDIT_COMMUNICATES,
                            Permission.MANAGE_USER
                    )
            );

            Role companyRole = new Role(
                    UUID.randomUUID(),
                    "ROLE_COMPANY",
                    Set.of(

                    )
            );

            roleRepository.save(ownerRole);
            roleRepository.save(defaultRole);
            roleRepository.save(admRole);
            roleRepository.save(companyRole);
        }
    }
}