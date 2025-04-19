package com.dev.condominio.service;

import com.dev.condominio.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(UUID id);
    UserReponse updateUser(UUID id);
}
