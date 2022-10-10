/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.UsuarioDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Usuario;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UsuarioController {

    private UsuarioDAO usuarioDAO;

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
}
