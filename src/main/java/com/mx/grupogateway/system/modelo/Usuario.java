/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.system.util.SecurityPassword;
import java.util.UUID;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Usuario {

    private Integer idUsuario;
    private String nombreUsuario;
    private String password;
    private String claveSeguridad;

    public Usuario() {
        idUsuario = 0;
    }

    /**
     * Constructor para actualizar una password nula.
     *
     * @param idUsuario
     * @param nombreUsuario
     * @param password
     */
    public Usuario(Integer idUsuario, String nombreUsuario,
            String password) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = encriptarPass(password);
    }

    /**
     * Constructor para crear un nuevo usuario con una clave de seguridad
     * generada por defecto.
     *
     * @param nombreUsuario
     * @param password
     */
    public Usuario(String nombreUsuario, String password) {
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
    public Usuario(Integer idUsuario, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Constructor para crear un nuevo Usuario con nombre de usuario,
     * identificador y sin password desde un objeto Empleado.
     *
     * @param empleado Recibe un objeto Empleado para crear el nombre de Usuario
     * con el nombre del empleado.
     */
    public Usuario(Empleado empleado) {
        this.idUsuario = 0;
        this.nombreUsuario = generarNombreUsuario(
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno()
        );
        this.password = "NULL";
    }

    /**
     * Constructor para asignar el identificador de un usuario.
     *
     * @param idUsuario
     */
    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the usuarioId
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the usuarioId to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Este método recibe la password para encriptar, si el valor no requiere
     * ser encriptado asignar false.
     *
     * @param password the password to set
     * @param encriptar true para encriptar.
     */
    public void setPassword(String password, boolean encriptar) {
        if (encriptar) {
            System.out.println(password);
            this.password = encriptarPass(password);
        } else {
            this.password = password;
        }
    }

    /**
     * @return the claveSeguridad
     */
    public String getClaveSeguridad() {
        return claveSeguridad;
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

    /**
     *
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @return Genera el nombre del usuario apartir de su nombre y apellidos.
     */
    private String generarNombreUsuario(String nombre,
            String apellidoPaterno, String apellidoMaterno) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(nombre.substring(0, 1))
                .append(apellidoPaterno)
                .append(apellidoMaterno.substring(0, 1));
        String usuario = stringBuilder.toString().toLowerCase();
        this.setNombreUsuario(usuario);
        return this.nombreUsuario;
    }

    private String encriptarPass(String password) {
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
}
