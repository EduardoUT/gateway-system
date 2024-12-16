/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.UsuarioDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean isConnectionStatusNotActive() {
        return this.usuarioDAO.isStatusConnectionNotActive();
    }

    /**
     * Almacena un nuevo usuario y obtiene el identificador generado si la
     * ejecución fue exitosa, devuelve -1 en caso contrario.
     *
     * @param usuario
     * @return Identificador del usuario guardado.
     */
    public int guardar(Usuario usuario) {
        return this.usuarioDAO.guardar(usuario);
    }

    /**
     *
     * @return List de tipo Usuario.
     */
    public List<Object[]> listar() {
        List<Usuario> usuarios = this.usuarioDAO.listar();
        List<Object[]> dataModelUsuarios = new ArrayList<>();
        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                dataModelUsuarios.add(
                        new Object[]{
                            usuario.getIdUsuario(),
                            usuario.getNombreUsuario(),
                            usuario.getClaveSeguridad()
                        }
                );
            }
            return dataModelUsuarios;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Permite comprobar y validar que un empleado tenga una cuenta de usuario
     * existente.
     *
     * @param idUsuario
     * @return
     */
    public Integer consultarIdUsuario(Integer idUsuario) {
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
    public boolean esPasswordNula(Integer idUsuario) {
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
     * @return
     */
    public int actualizarPasswordNula(Usuario usuario) {
        int idUsuario = this.consultarIdUsuario(usuario.getIdUsuario());
        if (idUsuario > 0 && this.esPasswordNula(idUsuario)) {
            this.usuarioDAO.actualizarPasswordNula(usuario);
        }
        return idUsuario;
    }

    public int actualizarPassword(Usuario usuario) {
        return this.usuarioDAO.actualizarPassword(usuario);
    }

    /**
     * Obtiene el identificador de usuario como referencia de la tablaUsuario.
     *
     * @param idUsuario
     * @return
     */
    public int eliminar(Integer idUsuario) {
        return this.usuarioDAO.eliminar(idUsuario);
    }
}
