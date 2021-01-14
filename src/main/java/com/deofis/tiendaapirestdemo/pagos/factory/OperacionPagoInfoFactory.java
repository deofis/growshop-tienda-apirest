package com.deofis.tiendaapirestdemo.pagos.factory;

import com.deofis.tiendaapirestdemo.operaciones.exceptions.OperacionException;
import com.deofis.tiendaapirestdemo.pagos.domain.MedioPagoEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OperacionPagoInfoFactory {

    public static OperacionPagoInfo getOperacionPagoInfo(String medioPago, Map<String, Object> atributos) {
        if (medioPago.equalsIgnoreCase(String.valueOf(MedioPagoEnum.EFECTIVO))) {
            return new EfectivoPagoInfo(atributos);
        } else if (medioPago.equalsIgnoreCase(String.valueOf(MedioPagoEnum.PAYPAL))) {
            return new PayPalPagoInfo(atributos);
        } else {
            throw new OperacionException("Medio de pago " + medioPago + " aun" +
                    " no ha sido implementado");
        }
    }
}
