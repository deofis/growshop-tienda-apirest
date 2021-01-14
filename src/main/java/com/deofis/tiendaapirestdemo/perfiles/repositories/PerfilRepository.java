package com.deofis.tiendaapirestdemo.perfiles.repositories;

import com.deofis.tiendaapirestdemo.autenticacion.domain.Usuario;
import com.deofis.tiendaapirestdemo.perfiles.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByUsuario(Usuario usuario);
}
