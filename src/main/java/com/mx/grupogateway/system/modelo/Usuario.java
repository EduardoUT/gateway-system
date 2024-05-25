/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.system.security.ProtectorData;
import com.mx.grupogateway.system.util.UUIDOperations;
import java.util.UUID;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Usuario {

    private String usuarioId;
    private String nombreUsuario;
    private String password;
    private String claveSeguridad;

    /**
     * Constructor para crear un nuevo usuario con una clave de seguridad
     * generada automáticamente.
     *
     * @param nombreUsuario
     * @param password
     */
    public Usuario(String nombreUsuario, String password) {
        this.usuarioId = UUIDOperations.generarIdentificador();
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.claveSeguridad = generarClaveSeguridad();
    }

    /**
     * Constructor para recuperar datos del Usuario de la BD.
     *
     * @param usuarioId
     * @param nombreUsuario
     * @param claveSeguridad
     */
    public Usuario(String usuarioId, String nombreUsuario,
            String claveSeguridad) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.claveSeguridad = claveSeguridad;
    }

    public Usuario(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the usuarioId
     */
    public String getIdUsuario() {
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

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Genera una clave de 16 carácteres encriptada.
     *
     * @return Hash de la clave generada.
     */
    private String generarClaveSeguridad() {
        return ProtectorData.encriptar(
                UUID.randomUUID().toString()
                        .substring(19, 35));
    }

    @Override
    public String toString() {
        return String.format("[ID: %s | Nombre Usuario: %s | Contraseña: %s | "
                + "Clave Seguridad: %s]",
                this.getIdUsuario(),
                this.nombreUsuario,
                this.password,
                this.claveSeguridad);
    }
}
