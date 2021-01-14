package com.deofis.tiendaapirest.ecommerce.controllers;

import com.deofis.tiendaapirest.ecommerce.services.EcommerceConfigService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EcommerceConfigController {

    private final EcommerceConfigService ecommerceConfigService;

    @PostMapping("/configuracion/color-nav")
    public ResponseEntity<String> cambiarColorNav(@RequestParam String color) {
        this.ecommerceConfigService.cambiarColorNav(color);
        return new ResponseEntity<>("Color del nav cambiado", HttpStatus.OK);
    }

    @PostMapping("/configuracion/color-fondo")
    public ResponseEntity<String> cambiarColorFondo(@RequestParam String color) {
        this.ecommerceConfigService.cambiarColorFondo(color);
        return new ResponseEntity<>("Color de fondo cambiado", HttpStatus.OK);
    }

}
