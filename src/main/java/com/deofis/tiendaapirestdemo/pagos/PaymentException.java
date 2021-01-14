package com.deofis.tiendaapirestdemo.pagos;

public class PaymentException extends RuntimeException {
    public PaymentException(String exMensaje) {
        super(exMensaje);
    }
}
