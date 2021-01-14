package com.deofis.tiendaapirestdemo.productos.repositories;

import com.deofis.tiendaapirestdemo.productos.domain.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {

}
