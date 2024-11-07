/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.UsuarioDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Obtiene el usuario recopilado del formulario de registro, y el ID del
     * empleado asociado.
     *
     * @param usuario
     */
    public void guardar(Usuario usuario) {
        this.usuarioDAO.guardar(usuario);
    }

    /**
     *
     * @return List de tipo Usuario.
     */
    public List<Usuario> listar() {
        return this.usuarioDAO.listar();
    }

    /**
     * Permite comprobar y validar que un empleado tenga una cuenta de usuario
     * existente.
     *
     * @param idUsuario
     * @return
     */
    public Optional consultarIdUsuario(String idUsuario) {
        return this.usuarioDAO.consultarIdUsuario(idUsuario);
    }

    /**
     * Consulta si la password existe en la BD y es válida y no conrenga el
     * valor por defecto NULL al crearse un nuevo empleado.
     *
     * @param usuario
     * @return
     */
    public boolean esPasswordValida(Usuario usuario) {
        return this.usuarioDAO.esPasswordValida(usuario);
    }

    /**
     * Vslida si una password contiene NULL en la BD.
     *
     * @param idUsuario
     * @return
     */
    public boolean esPasswordNula(String idUsuario) {
        return this.usuarioDAO.esPasswordNula(idUsuario);
    }

    /**
     * Obtiene los identificadores de Categoría de empleado e identificador de
     * usuario.
     *
     * @param usuario
     * @return
     */
    public Empleado consultarPerfilUsuario(Usuario usuario) {
        return this.usuarioDAO.consultarPerfilUsuario(usuario);
    }

    /**
     * Actualiza la password acorde al id, nombre y password dados por el
     * usuario
     *
     * @param usuario
     */
    public void actualizarPassword(Usuario usuario) {
        this.usuarioDAO.actualizarPassword(usuario);
    }

    /**
     * Obtiene el identificador de usuario como referencia de la tablaUsuario.
     *
     * @param idUsuario
     * @return
     */
    public int eliminar(String idUsuario) {
        return this.usuarioDAO.eliminar(idUsuario);
    }
}
