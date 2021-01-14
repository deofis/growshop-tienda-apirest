package com.deofis.tiendaapirestdemo.pagos.repositories;

import com.deofis.tiendaapirestdemo.pagos.domain.MedioPago;
import com.deofis.tiendaapirestdemo.pagos.domain.MedioPagoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {

    Optional<MedioPago> findByNombre(MedioPagoEnum medioPago);
}
