/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.util.UUID;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Usuario {

    private Integer usuarioId;
    private final String nombreUsuario;
    private final String password;
    private final String claveSeguridad;

    /**
     * Constructor para crear un nuevo usuario con una clave de seguridad
     * generada automáticamente.
     *
     * @param nombreUsuario
     * @param password
     */
    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.claveSeguridad = generarClaveSeguridad();
    }

    /**
     * Constructor para recuperar datos del Usuario de la BD.
     *
     * @param usuarioId
     * @param nombreUsuario
     * @param password
     * @param claveSeguridad
     */
    public Usuario(Integer usuarioId, String nombreUsuario, String password,
            String claveSeguridad) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.claveSeguridad = claveSeguridad;
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

    /**
     * @return the claveSeguridad
     */
    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    private String generarClaveSeguridad() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return String.format("[ID: %d | Nombre Usuario: %s | Contraseña: %s]",
                this.usuarioId,
                this.nombreUsuario,
                this.password);
    }
}
