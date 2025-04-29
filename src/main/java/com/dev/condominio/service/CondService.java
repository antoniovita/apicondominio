package com.dev.condominio.service;

import com.dev.condominio.domain.model.Cond;
import com.dev.condominio.domain.model.User;
import com.dev.condominio.dto.cond.CondMinimalResponse;
import com.dev.condominio.dto.cond.CondRequest;
import com.dev.condominio.dto.cond.CondResponse;
import com.dev.condominio.repository.CondRepository;
import com.dev.condominio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CondService {

    private final CondRepository condRepository;
    private final UserRepository userRepository;


    public CondResponse condToResponse(Cond cond) {
        return new CondResponse(
                cond.getId(),
                cond.getName(),
                cond.getImgUrl(),
                cond.getAddress(),
                cond.getCnpj(),
                cond.getOwner().getId(),
                cond.getOwner().getName()
        );
    }

    //route
    //just the company can access
    public List<CondResponse> findAll() {
        return condRepository.findAll().stream().map(this::condToResponse).toList();
    }


    //route
    //just the cond users can access
    public CondResponse findById(UUID id) {
        Cond cond = condRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Condomínio não encontrado com ID: " + id));

        return condToResponse(cond);
    }


    // route
    // get cond info but just simple information
    public CondMinimalResponse getMinimalCondInfo(UUID id) {

        Cond cond = condRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Condomínio não encontrado com ID: " + id));

        return new CondMinimalResponse(
                cond.getName(),
                cond.getImgUrl(),
                cond.getAddress(),
                cond.getOwner().getName()
        );
    }




    // route
    //create cond
    public CondResponse createCond(CondRequest request) {
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NoSuchElementException("Usuário proprietário não encontrado com ID: " + request.getOwnerId()));

        Cond cond = new Cond();
        cond.setName(request.getName());
        cond.setCnpj(request.getCnpj());
        cond.setAddress(request.getAddress());
        cond.setImgUrl(request.getImgUrl());
        cond.setOwner(owner);

        Cond savedCond = condRepository.save(cond);

        return condToResponse(savedCond);
    }

    //route
    //update cond
    public CondResponse updateCond(UUID id, CondRequest request) {
        Cond cond = condRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Condomínio não encontrado com ID: " + id));

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NoSuchElementException("Usuário proprietário não encontrado com ID: " + request.getOwnerId()));

        cond.setName(request.getName());
        cond.setCnpj(request.getCnpj());
        cond.setAddress(request.getAddress());
        cond.setImgUrl(request.getImgUrl());
        cond.setOwner(owner);

        Cond updatedCond = condRepository.save(cond);

        return condToResponse(updatedCond);
    }


    //route
    //delete cond by id
    public void deleteCond(UUID id) {
        if (!condRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }
        condRepository.deleteById(id);
    }

}
