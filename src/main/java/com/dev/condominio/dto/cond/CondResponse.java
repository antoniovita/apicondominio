package com.dev.condominio.dto.cond;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondResponse {

    private UUID id;

    private String name;

    private String imgUrl;

    private String address;

    private String cnpj;

    private UUID ownerId;

    private String ownerName;

}
