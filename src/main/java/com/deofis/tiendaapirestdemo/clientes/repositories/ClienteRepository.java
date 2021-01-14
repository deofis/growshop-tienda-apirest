package com.deofis.tiendaapirestdemo.clientes.repositories;

import com.deofis.tiendaapirestdemo.clientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
