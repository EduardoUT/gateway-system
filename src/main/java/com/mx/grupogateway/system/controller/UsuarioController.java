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
     * @param empleadoId
     * @return
     */
    public boolean estaRegistrado(Empleado empleado) {
        return this.usuarioDAO.estaRegistrado(empleado);
    }

    /**
     * Obtiene el identificador de usuario como referencia de la tablaUsuario.
     *
     * @param usuarioId
     * @return
     */
    public int eliminar(Integer usuarioId) {
        return this.usuarioDAO.eliminar(usuarioId);
    }
}
