package com.dev.condominio.domain.security.config;

import com.dev.condominio.repository.CondRepository;
import com.dev.condominio.repository.RoleRepository;
import com.dev.condominio.repository.UserRepository;
import com.dev.condominio.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public UserService userService(UserRepository userRepository, CondRepository condRepository, RoleRepository roleRepository) {
        return new UserService(userRepository, condRepository, roleRepository);
    }

}