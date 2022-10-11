/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Empleado {

    private Integer empleadoId;
    private final String nombre;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private Integer cargoId;
    private final Integer usuarioId;
    
    private static final Integer DEFAULT_USUARIO_ID = 0;

    /**
     * Constructor para crear un Empleado.
     *
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param categoriaId
     */
    public Empleado(String nombre, String apellidoPaterno,
            String apellidoMaterno) {
        validarEmpleado(nombre, apellidoPaterno, apellidoMaterno);
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.usuarioId = DEFAULT_USUARIO_ID;
    }

    /**
     * Constructor para obtener datos Empleado de BD.
     *
     * @param empleadoId
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param categoriaId
     * @param usuarioId
     */
    public Empleado(Integer empleadoId, String nombre, String apellidoPaterno,
            String apellidoMaterno, Integer categoriaId, Integer usuarioId) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.cargoId = categoriaId;
        this.usuarioId = usuarioId;
    }

    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
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

    /**
     * @return the cargoId
     */
    public int getCargoId() {
        return cargoId;
    }

    /**
     * @param cargoId the cargoId to set
     */
    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    private void validarEmpleado(String nombre, String apellidoPaterno,
            String apellidoMaterno) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El campo" + nombre
                    + "está vacío.");
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new IllegalArgumentException("El campo" + apellidoPaterno
                    + "está vacío.");
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new IllegalArgumentException("El campo" + apellidoMaterno
                    + "está vacío.");
        }
    }

    @Override
    public String toString() {
        return String.format("[ID: %d | Nombre: %s | Apellido P: %s "
                + "| Apellido M: %s | ID Categoría: %d | ID Usuario: %d]",
                this.empleadoId,
                this.nombre,
                this.apellidoPaterno,
                this.apellidoMaterno,
                this.cargoId,
                this.usuarioId);
    }

}
