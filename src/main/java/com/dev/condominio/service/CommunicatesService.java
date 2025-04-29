package com.dev.condominio.service;

import com.dev.condominio.repository.CommunicatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunicatesService {

    private final CommunicatesRepository communicatesRepository;



}
