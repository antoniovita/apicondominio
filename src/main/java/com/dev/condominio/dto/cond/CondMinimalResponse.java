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
public class CondMinimalResponse {

    private String name;

    private String imgUrl;

    private String address;

    private String ownerName;

}
