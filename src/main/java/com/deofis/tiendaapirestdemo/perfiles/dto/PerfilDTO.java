package com.deofis.tiendaapirestdemo.perfiles.dto;

import com.deofis.tiendaapirestdemo.clientes.domain.Cliente;
import com.deofis.tiendaapirestdemo.operaciones.domain.Operacion;
import com.deofis.tiendaapirestdemo.perfiles.domain.Carrito;
import com.deofis.tiendaapirestdemo.perfiles.domain.Favorito;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerfilDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    private String usuario;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Carrito carrito;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Favorito favorito;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Operacion> compras;
}
