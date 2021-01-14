package com.deofis.tiendaapirestdemo.perfiles.services;

import com.deofis.tiendaapirestdemo.autenticacion.domain.Usuario;
import com.deofis.tiendaapirestdemo.clientes.domain.Cliente;
import com.deofis.tiendaapirestdemo.globalservices.CrudService;
import com.deofis.tiendaapirestdemo.perfiles.domain.Carrito;
import com.deofis.tiendaapirestdemo.perfiles.domain.Favorito;
import com.deofis.tiendaapirestdemo.perfiles.domain.Perfil;
import com.deofis.tiendaapirestdemo.perfiles.dto.PerfilDTO;

public interface PerfilService extends CrudService<Perfil, Long> {

    /**
     * Se encarga de tomar los datos del cliente y asignarlos al usuario registrado, creando el nuevo perfil.
     * NO actualiza datos de un usuario ya registrado.
     * NOTA: Crear el perfil implica: Asignar cliente, usuario, carrito, compras y favoritos.
     * @param cliente Cliente del nuevo usuario con sus datos.
     * @param usuarioEmail String del email del nuevo usuario.
     */
    void cargarPerfil(Cliente cliente, String usuarioEmail);

    /**
     * Se encarga de tomar los datos  del cliente y usuario, asociarlos y crear el nuevo perfil.
     * NOTA: Crear el perfil implica: Asignar cliente, usuario, carrito, compras y favoritos.
     * @param cliente Cliente del nuevo usuario con sus datos.
     * @param usuario Usuario usuario nuevo.
     */
    void cargarPerfil(Cliente cliente, Usuario usuario);

    /**
     * Actualiza los datos de cliente asociados al perfil del usuario logueado.
     * @param clienteActualizado Cliente datos del cliente actualizado.
     * @return PerfilDTO datos del perfil luego del cambio.
     */
    PerfilDTO actualizarPerfil(Cliente clienteActualizado);

    /**
     * Muestra los datos completos del perfil: nombre de usuario, datos de cliente, y
     * el carrito.
     * @return PerfilDTO el perfil actual.
     */
    PerfilDTO verPerfil();

    /**
     * Este objeto devuelve el Perfil completo (no el DTO).
     * @return Perfil completo.
     */
    Perfil obtenerPerfil();

    /**
     * Obtener datos del cliente del usuario logueado.
     * @return Cliente datos del cliente del usuario logueado.
     */
    Cliente obtenerDatosCliente();

    /**
     * Obtiene el carrito del perfil actual.
     * @return Carrito del perfil.
     */
    Carrito obtenerCarrito();

    /**
     * Llama al carrito service para vaciarlo.
     */
    void vaciarCarrito();

    /**
     * Obtiene el objeto Favoritos que pertenece al usuario logueado.
     * @return Favoritos del perfil.
     */
    Favorito obtenerFavoritos();
}
