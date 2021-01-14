package com.deofis.tiendaapirestdemo.clientes.repositories;

import com.deofis.tiendaapirestdemo.clientes.domain.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

}
