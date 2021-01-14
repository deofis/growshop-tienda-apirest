package com.deofis.tiendaapirestdemo.operaciones.controllers;

import com.deofis.tiendaapirestdemo.operaciones.domain.Operacion;
import com.deofis.tiendaapirestdemo.operaciones.exceptions.OperacionException;
import com.deofis.tiendaapirestdemo.operaciones.services.OperacionService;
import com.deofis.tiendaapirestdemo.pagos.factory.OperacionPagoInfo;
import com.deofis.tiendaapirestdemo.perfiles.exceptions.PerfilesException;
import com.deofis.tiendaapirestdemo.productos.exceptions.ProductoException;
import com.deofis.tiendaapirestdemo.productos.exceptions.SkuException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrarOperacionController {

    private final OperacionService operacionService;

    /**
     * Registra una nueva operación de compra/venta para el cliente que se pasa como parte de la
     * operación en sí.
     * URL: ~/api/operaciones/nueva
     * HttpMethod: POST
     * HttpStatus: CREATED
     * @param operacion RequestBody con la operacion a registrar.
     * @return ResponseEntity con la operacion registrada y OrderPayload con los datos que vienen en
     *                          respuesta a la API de PayPal (incluyendo la URL para aprobar el pago).
     */
    @PostMapping("/operaciones/nueva")
    public ResponseEntity<?> registrarOperacion(@Valid @RequestBody Operacion operacion) {
        Map<String, Object> response = new HashMap<>();
        OperacionPagoInfo pagoInfo;

        try {
            pagoInfo = this.operacionService.registrarNuevaOperacion(operacion);
        } catch (OperacionException | ProductoException | PerfilesException | SkuException e) {
            response.put("mensaje", "Error al registrar la nueva compra");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("compra", pagoInfo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
