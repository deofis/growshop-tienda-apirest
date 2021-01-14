package com.deofis.tiendaapirest.productos.controllers.marcas;

import com.deofis.tiendaapirest.productos.domain.Marca;
import com.deofis.tiendaapirest.productos.exceptions.ProductoException;
import com.deofis.tiendaapirest.productos.services.MarcaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ObtenerMarcasController {

    private final MarcaService marcaService;

    /**
     * Obtener un listado de todas las marcas.
     * URL: ~/api/productos/marcas
     * HttpMehotd: GET
     * HttpStatus: OK
     * @return ResponseEntity con las marcas ordenadas por nombre.
     */
    @GetMapping("/productos/marcas")
    public ResponseEntity<?> obtenerMarcas() {
        Map<String, Object> response = new HashMap<>();
        List<Marca> marcas;

        try {
            marcas = this.marcaService.obtenerMarcas();
        } catch (ProductoException e) {
            response.put("mensaje", "Error al obtener el listado de marcas.");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (marcas.size() == 0) {
            response.put("mensaje", "No existen marcas en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("marcas", marcas);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Obtener una marca.
     * URL: ~/api/productos/marcas/ver/1
     * HttpMethod: GET
     * HttpStatus: OK
     * @param id PathVariable Long de la marca a obtener.
     * @return ResponseEntity con la marca.
     */
    @GetMapping("/productos/marcas/ver/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Marca marca;

        try {
            marca = this.marcaService.obtenerMarca(id);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al obtener la marca");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

}
