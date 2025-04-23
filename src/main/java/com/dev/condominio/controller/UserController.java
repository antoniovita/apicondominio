package com.dev.condominio.controller;

import com.dev.condominio.domain.security.types.UserType;
import com.dev.condominio.dto.user.UserRequest;
import com.dev.condominio.dto.user.UserResponse;
import com.dev.condominio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }


    // GET /user
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // PUT /user/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // PUT /user/{id}/type?newType=SINDICO
    @PutMapping("/{id}/type")
    public ResponseEntity<UserResponse> updateUserType(@PathVariable UUID id, @RequestParam UserType newType) {
        return ResponseEntity.ok(userService.updateUserTypeAndPermissions(id, newType));
    }

    // DELETE /user/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
