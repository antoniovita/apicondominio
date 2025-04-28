package com.dev.condominio.service;

import com.dev.condominio.repository.CondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CondService {

    private final CondRepository condRepository;
}
