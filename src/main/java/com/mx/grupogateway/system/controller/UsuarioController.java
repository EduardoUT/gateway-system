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
     * @param idEmpleado
     */
    public void guardar(Usuario usuario, String idEmpleado) {
        this.usuarioDAO.guardar(usuario, idEmpleado);
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
     * @param empleado
     * @return
     */
    public Optional consultarIdUsuario(Empleado empleado) {
        return this.usuarioDAO.consultarIdUsuario(empleado);
    }

    public ArrayList<String> consultarPerfilUsuario(
            String nombreUsuario, String password) {
        return this.usuarioDAO.consultarUsuario(nombreUsuario, password);
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
