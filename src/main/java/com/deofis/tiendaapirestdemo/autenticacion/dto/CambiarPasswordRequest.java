package com.deofis.tiendaapirestdemo.autenticacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambiarPasswordRequest {

    @NotNull
    private String password;
}
