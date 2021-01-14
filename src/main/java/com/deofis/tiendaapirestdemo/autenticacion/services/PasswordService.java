package com.deofis.tiendaapirestdemo.autenticacion.services;

import com.deofis.tiendaapirestdemo.autenticacion.domain.Usuario;
import com.deofis.tiendaapirestdemo.autenticacion.dto.CambiarPasswordRequest;

public interface PasswordService {

    /**
     * Cambiar la contraseña del usuario con la sesión actual.
     * @param passwordRequest
     */
    Usuario cambiarPassword(CambiarPasswordRequest passwordRequest);

    Usuario cambiarPassword(String token, CambiarPasswordRequest cambiarPasswordRequest);

    void recuperarPassword(String userEmail);
}
