package com.deofis.tiendaapirestdemo.perfiles.repositories;

import com.deofis.tiendaapirestdemo.perfiles.domain.DetalleCarrito;
import com.deofis.tiendaapirestdemo.productos.domain.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {

    void deleteBySku(Sku sku);

}
