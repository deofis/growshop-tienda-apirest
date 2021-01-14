package com.deofis.tiendaapirestdemo.autenticacion.exceptions;

public class TokenException extends RuntimeException {
    public TokenException(String exMensaje) {
        super(exMensaje);
    }
}
