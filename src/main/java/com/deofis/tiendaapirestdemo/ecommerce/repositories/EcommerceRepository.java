package com.deofis.tiendaapirestdemo.ecommerce.repositories;

import com.deofis.tiendaapirestdemo.ecommerce.domain.EcommerceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcommerceRepository extends JpaRepository<EcommerceConfig, Long> {

}
