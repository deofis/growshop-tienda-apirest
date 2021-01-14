package com.deofis.tiendaapirestdemo.autenticacion.services;

import com.deofis.tiendaapirestdemo.autenticacion.domain.Usuario;
import com.deofis.tiendaapirestdemo.autenticacion.domain.VerificationToken;

/**
 * Servicio para generar tokens de validación.
 */

public interface VerificationTokenService {

    String generarVerificationToken(Usuario usuario);

    VerificationToken getVerificationToken(String token);

    void delete(VerificationToken verificationToken);
}
