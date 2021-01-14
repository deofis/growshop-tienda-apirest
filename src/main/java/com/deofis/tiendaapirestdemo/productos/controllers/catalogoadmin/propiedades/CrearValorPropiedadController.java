package com.deofis.tiendaapirestdemo.productos.controllers.catalogoadmin.propiedades;

import com.deofis.tiendaapirestdemo.productos.domain.PropiedadProducto;
import com.deofis.tiendaapirestdemo.productos.domain.ValorPropiedadProducto;
import com.deofis.tiendaapirestdemo.productos.exceptions.ProductoException;
import com.deofis.tiendaapirestdemo.productos.services.CatalogoAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CrearValorPropiedadController {

    private final CatalogoAdminService catalogoAdminService;

    @PostMapping("/productos/propiedades/{propiedadId}/valores")
    public ResponseEntity<?> crearValorAPropiedad(@PathVariable Long propiedadId,
                                                  @RequestBody ValorPropiedadProducto valorPropiedadProducto) {
        Map<String, Object> response = new HashMap<>();
        PropiedadProducto propiedadActualizada;

        try {
            propiedadActualizada = this.catalogoAdminService.crearValorPropiedad(propiedadId, valorPropiedadProducto);
        } catch (ProductoException e) {
            response.put("mensaje", "Error al crear el nuevo valor para la propiedad");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("propiedadProducto", propiedadActualizada);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
