package com.dev.condominio.dto.cond;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String imgUrl;

    @NotNull
    private String address;

    @NotNull
    private String cnpj;

    @NotNull
    private Set<UUID> roleIds;

    @NotNull
    private UUID condId;
}
