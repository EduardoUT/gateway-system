/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.system.security.SecurityPassword;
import com.mx.grupogateway.system.util.UUIDOperations;
import java.util.UUID;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Usuario {

    private String idUsuario;
    private String nombreUsuario;
    private String password;
    private String claveSeguridad;

    public Usuario() {
    }

    /**
     * Constructor para crear un nuevo Usuario con nombre de usuario,
     * identificador y sin password desde un objeto Empleado.
     *
     * @param empleado Recibe un objeto Empleado para crear el nombre de Usuario
     * con el nombre del empleado.
     */
    public Usuario(Empleado empleado) {
        this.idUsuario = UUIDOperations.generarIdentificador();
        this.nombreUsuario = generarNombreUsuario(empleado.getNombre(),
                empleado.getApellidoPaterno(), empleado.getApellidoMaterno()
        );
        this.password = "NULL";
    }

    /**
     * Constructor para crear un nuevo usuario con una clave de seguridad
     * generada automáticamente.
     *
     * @param nombreUsuario
     * @param password
     */
    public Usuario(String nombreUsuario, char[] password) {
        this.idUsuario = UUIDOperations.generarIdentificador();
        this.nombreUsuario = nombreUsuario;
        this.password = encriptarPass(password);
        this.claveSeguridad = generarClaveSeguridad();
    }

    /**
     * Constructor usado para listar un usuario con id y nombre.
     *
     * @param idUsuario
     * @param nombreUsuario
     */
    public Usuario(String idUsuario, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Constructor para actualizar una password nula.
     *
     * @param idUsuario
     * @param nombreUsuario
     * @param password
     */
    public Usuario(String idUsuario, String nombreUsuario,
            char[] password) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = encriptarPass(password);
    }

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the usuarioId
     */
    public String getIdUsuario() {
        return idUsuario;
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
     * @param idUsuario the usuarioId to set
     */
    public void setUsuarioId(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Genera una clave de 16 carácteres encriptada.
     *
     * @return Hash de la clave generada.
     */
    private String generarClaveSeguridad() {
        return SecurityPassword.encriptar(
                UUID.randomUUID().toString()
                        .substring(19, 35));
    }

    private String generarNombreUsuario(String nombre,
            String apellidoPaterno, String apellidoMaterno) {
        String usuario = nombre.substring(0, 1)
                .concat(apellidoPaterno)
                .concat(apellidoMaterno.substring(0, 1))
                .toLowerCase();
        this.nombreUsuario = usuario;
        return this.nombreUsuario;
    }

    private String encriptarPass(char[] password) {
        return SecurityPassword.encriptar(password);
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

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
