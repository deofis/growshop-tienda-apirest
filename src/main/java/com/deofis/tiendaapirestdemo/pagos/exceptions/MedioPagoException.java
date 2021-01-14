package com.deofis.tiendaapirestdemo.pagos.exceptions;

public class MedioPagoException extends RuntimeException {
    public MedioPagoException(String exMensaje) {
        super(exMensaje);
    }
}
