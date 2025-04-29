package com.dev.condominio.dto.communicates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicatesResponse {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime date;
    private UUID userId;
    private UUID condId;
}
