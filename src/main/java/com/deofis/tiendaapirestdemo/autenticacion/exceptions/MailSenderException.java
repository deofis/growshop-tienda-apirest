package com.deofis.tiendaapirestdemo.autenticacion.exceptions;

public class MailSenderException extends RuntimeException {
    public MailSenderException(String exMensaje) {
        super(exMensaje);
    }
}
