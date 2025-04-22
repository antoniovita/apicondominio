package com.dev.condominio.service;

import com.dev.condominio.domain.model.User;
import com.dev.condominio.domain.security.Permission;
import com.dev.condominio.domain.security.types.UserType;
import com.dev.condominio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    //defines this userRepository is the one from the package so it is able to be used here
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //find all users
    public List<User> findAll() {
        return userRepository.findAll();
    }


    //find user by id
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }


    //create user
    public User createUser(User user) {
        return userRepository.save(user);
    }


    //delete user by id
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }
        userRepository.deleteById(id);
    }

    //update user
    public User updateUser(UUID id, User updatedData) {
        User user = findById(id);
        user.setName(updatedData.getName());
        user.setCpf(updatedData.getCpf());
        user.setBloco(updatedData.getBloco());
        user.setApt(updatedData.getApt());
        user.setCond(updatedData.getCond());
        user.setType(updatedData.getType());
        return userRepository.save(user);
    }


    // update the permissions from UserType
    @Transactional
    public User updateUserTypeAndPermissions(UUID userId, UserType newType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        user.setType(newType);

        Set<Permission> permissions = newType.getDefaultPermissions();
        user.setPermissions(permissions);

        return userRepository.save(user);
    }
}
