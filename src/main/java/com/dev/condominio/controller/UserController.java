package com.dev.condominio.controller;

import com.dev.condominio.domain.model.User;
import com.dev.condominio.dto.user.UserMinimalResponse;
import com.dev.condominio.dto.user.UserRequest;
import com.dev.condominio.dto.user.UserResponse;
import com.dev.condominio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    //method to get info from the user authenticated
    public User getCurrentAuthenticatedUser() {
        //getting info from the context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        }
        throw new UsernameNotFoundException("Usuário não autenticado.");
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
    @PreAuthorize("hasAuthority('MANAGE_EVERYTHING')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


    //ADMs from the cond or OWNER can get users
    // GET /user/cond/{condId} get users by condId
    @GetMapping("/cond/{condId}")
    public ResponseEntity<List<UserResponse>> getUsersByCondId(@PathVariable UUID condId) {
        User currentUser = getCurrentAuthenticatedUser();

        //verify if the user is from the cond they want to get all users
        if (!currentUser.getCond().equals(condId) || (!currentUser.hasRole("ROLE_ADM") && !currentUser.hasRole("ROLE_OWNER"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<UserResponse> users = userService.findAllByCondId(condId);
        return ResponseEntity.ok(users);
    }


    // GET /user/cond/{condId} get users by condId JUST SIMPLE INFORMATION, DATA: name, bloco and apt
    @GetMapping("/cond/{condId}/minimal")
    public ResponseEntity<List <UserMinimalResponse>> getMinimalUserByCondId(@PathVariable UUID condId) {
        User currentUser = getCurrentAuthenticatedUser();

        if (!currentUser.getCond().equals(condId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<UserMinimalResponse> users = userService.findMinimalUsersByCondId(condId);
        return ResponseEntity.ok(users);

    }

    // just the user and the ones with the MANAGE_USER permission such as ROLE_ADM and ROLE_OWNER
    // GET /user/{id} get user by id
    @GetMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('MANAGE_USER')")
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

    // just the user and the ones with the MANAGE_USER permission such as ROLE_ADM and ROLE_OWNER
    // DELETE /user/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('MANAGE_USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
