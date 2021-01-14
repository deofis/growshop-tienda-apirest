package com.deofis.tiendaapirestdemo.productos.repositories;

import com.deofis.tiendaapirestdemo.productos.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findAllByOrderByNombreAsc();

    Optional<Marca> findByNombre(String nombre);

    List<Marca> findAllByNombreContainingIgnoringCase(String nombre);
}
