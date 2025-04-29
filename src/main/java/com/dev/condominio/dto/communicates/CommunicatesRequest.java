package com.dev.condominio.dto.communicates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicatesRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID condId;

}
