package com.deofis.tiendaapirest.productos.repositories;

import com.deofis.tiendaapirest.productos.domain.ValorPropiedadProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public interface ValorPropiedadRepository extends JpaRepository<ValorPropiedadProducto, Long> {

}
