package com.deofis.tiendaapirestdemo.perfiles.repositories;

import com.deofis.tiendaapirestdemo.perfiles.domain.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritosRepository extends JpaRepository<Favorito, Long> {

}
