/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.system.util.UUIDOperations;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Empleado {

    private String empleadoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Usuario usuario;
    private EmpleadoCategoria empleadoCategoria;
    

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
        this.empleadoId = UUIDOperations.generarIdentificador();
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
            String apellidoMaterno, Usuario usuario, 
            EmpleadoCategoria empleadoCategoria) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.usuario = usuario;
        this.empleadoCategoria = empleadoCategoria;
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

    @Override
    public String toString() {
        return String.format("[ID: %s | Nombre: %s | Apellido P: %s "
                + "| Apellido M: %s | ID Categoría: %s | ID Usuario: %s]",
                this.empleadoId,
                this.nombre,
                this.apellidoPaterno,
                this.apellidoMaterno,
                this.getEmpleadoCategoria().getCategoriaId(),
                this.getUsuario().getUsuarioId());
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return the empleadoCategoria
     */
    public EmpleadoCategoria getEmpleadoCategoria() {
        return empleadoCategoria;
    }

}
