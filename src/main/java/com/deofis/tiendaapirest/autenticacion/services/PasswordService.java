package com.deofis.tiendaapirest.autenticacion.services;

import com.deofis.tiendaapirest.autenticacion.domain.Usuario;
import com.deofis.tiendaapirest.autenticacion.dto.CambiarPasswordRequest;

public interface PasswordService {

    /**
     * Cambiar la contraseña del usuario con la sesión actual.
     * @param passwordRequest
     */
    Usuario cambiarPassword(CambiarPasswordRequest passwordRequest);

    Usuario cambiarPassword(String token, CambiarPasswordRequest cambiarPasswordRequest);

    void recuperarPassword(String userEmail);
}
