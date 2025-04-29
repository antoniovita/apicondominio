package com.dev.condominio.controller;

import com.dev.condominio.domain.model.User;
import com.dev.condominio.dto.cond.CondRequest;
import com.dev.condominio.dto.cond.CondResponse;
import com.dev.condominio.dto.user.UserResponse;
import com.dev.condominio.service.CondService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/cond")
@RequiredArgsConstructor
public class CondController {

    private final CondService condService;

    //method to get info from the user authenticated
    public User getCurrentAuthenticatedUser() {
        //getting info from the context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        }
        throw new UsernameNotFoundException("Usuário não autenticado.");
    }

    // route
    // create a cond
    @PostMapping
    public ResponseEntity<CondResponse> createCond (@Valid @RequestBody CondRequest request) {
        return ResponseEntity.ok(condService.createCond(request));
    }

    //route
    // update cond info
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_COND')")
    public ResponseEntity<CondResponse> updateCond( @PathVariable UUID id, @RequestBody CondRequest request) {
        return ResponseEntity.ok(condService.updateCond(id, request));
    }


    //route
    // get all conds and complete info, company only
    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_EVERYTHING')")
    public ResponseEntity<List<CondResponse>> getAllCond() {
        return ResponseEntity.ok(condService.findAll());
    }

    //route
    //get cond complete info, company only
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_EVERYTHING')")
    public ResponseEntity<CondResponse> getCondById( @PathVariable UUID id) {
        return ResponseEntity.ok(condService.findById(id));
    }

    //route
    //get cond info just simple, everybody from the cond can see
    @GetMapping("/simple")
    public ResponseEntity<CondResponse> getCondByIdSimple(UUID id) {

        User currentUser = getCurrentAuthenticatedUser();

        if (!currentUser.getCond().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(condService.findById(id));
    }



}
