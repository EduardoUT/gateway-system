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
public class Empleado {

    private String empleadoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private EmpleadoCategoria empleadoCategoria;
    private Usuario usuario;

    /**
     * El valor por defecto es 0.
     */
    public static final Integer DEFAULT_USUARIO_ID = 0;

    /**
     * Constructor para crear un Empleado.
     *
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     */
    public Empleado(String nombre, String apellidoPaterno,
            String apellidoMaterno) {
        validarEmpleado(nombre, apellidoPaterno, apellidoMaterno);
        this.empleadoId = generarId();
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        //this.usuarioId = DEFAULT_USUARIO_ID;
    }

    /**
     * Constructor para obtener datos Empleado de BD.
     *
     * @param empleadoId
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param empleadoCategoria
     * @param usuario
     */
    public Empleado(String empleadoId, String nombre, String apellidoPaterno,
            String apellidoMaterno, EmpleadoCategoria empleadoCategoria, 
            Usuario usuario) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.empleadoCategoria = empleadoCategoria;
        this.usuario = usuario;
    }

    public Empleado(String nombre, String apellidoPaterno,
            String apellidoMaterno, Usuario usuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.usuario = usuario;
        //this.usuarioId = usuarioId;
    }

    /**
     * Constructor para asignar el empleadoId.
     *
     * @param empleadoId
     */
    public Empleado(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    /**
     * @return the idEmpleado
     */
    public String getIdEmpleado() {
        return empleadoId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    private void validarEmpleado(String nombre, String apellidoPaterno,
            String apellidoMaterno) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El campo nombre"
                    + " está vacío.");
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new IllegalArgumentException("El campo apellidoPaterno"
                    + " está vacío.");
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new IllegalArgumentException("El campo apellidoMaterno"
                    + " está vacío.");
        }
    }

    /**
     * Método que generá el identificador único de los empleados creados.
     *
     * @return Cadena aleatoria de 18 carácteres de formato UUID.
     */
    private String generarId() {
        return UUID.randomUUID().toString().substring(0, 18);
    }

    @Override
    public String toString() {
        return String.format("[ID: %s | Nombre: %s | Apellido P: %s "
                + "| Apellido M: %s | ID Categoría: %d | ID Usuario: %d]",
                this.empleadoId,
                this.nombre,
                this.apellidoPaterno,
                this.apellidoMaterno,
                this.empleadoCategoria.getCategoriaId(),
                this.usuario.getUsuarioId());
    }

}
