/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gateway.modelo;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Usuario {

    private final Integer usuarioId;
    private final String nombreUsuario;
    private final String password;

    public Usuario(Integer usuarioId, String nombreUsuario, String password) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d | Nombre Usuario: %s | Contraseña: %s]",
                this.usuarioId,
                this.nombreUsuario,
                this.password);
    }

}
