package com.deofis.tiendaapirest.productos.controllers.catalogoadmin.productos;

import com.deofis.tiendaapirest.productos.exceptions.ProductoException;
import com.deofis.tiendaapirest.productos.services.CatalogoAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CancelarCreacionProductoController {

    private final CatalogoAdminService catalogoAdminService;

    /**
     * Cancela la creación de un producto, en caso de solicitarlo DURANTE la creación del mismo.
     * URL: ~/api/productos/1/cancelar
     * HttpMethod: DELETE
     * HttpStatus: OK
     * @param productoId PathVariable Long id del producto a cancelar su creación.
     * @return ResponseEntity con mensaje de cancelación/error.
     */
    @DeleteMapping("/productos/{productoId}/cancelar")
    public ResponseEntity<?> cancelarCreacionProducto(@PathVariable Long productoId) {
        Map<String, Object> response = new HashMap<>();

        try {
            this.catalogoAdminService.cancelarCreacionProducto(productoId);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al cancelar la creación del producto");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Creación del producto cancelada con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
