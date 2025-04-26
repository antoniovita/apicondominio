package com.dev.condominio.controller;

import com.dev.condominio.dto.user.UserRequest;
import com.dev.condominio.dto.user.UserResponse;
import com.dev.condominio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    //anyone can register
    // POST /user create user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    //ADM only
    // GET /user get all users
    @GetMapping
    @PreAuthorize("hasAuthority('')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


    //anyone from the cond can see - to implement
    // GET /user/cond/{condId} get users by condId
    @GetMapping("/cond/{condId}")
    public ResponseEntity<List<UserResponse>> getUsersByCondId(@PathVariable UUID condId) {
        return ResponseEntity.ok(userService.findAllByCondId(condId));
    }

    // GET /user/{id} get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.userToResponse(userService.findById(id)));
    }


    // just the user and the ones with the MANAGE_USER permission such as ROLE_ADM and ROLE_OWNER
    // PUT /user/{id} update user
    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('MANAGE_USER')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }


    // DELETE /user/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
