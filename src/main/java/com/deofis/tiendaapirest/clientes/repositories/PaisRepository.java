package com.deofis.tiendaapirest.clientes.repositories;

import com.deofis.tiendaapirest.clientes.domain.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    Optional<Pais> findByNombre(String pais);

    List<Pais> findAllByOrderByNombreAsc();

}
