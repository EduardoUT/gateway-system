/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gateway.modelo;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Empleado {

    private Integer empleadoId;
    private final String nombre;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private Integer categoriaId;
    private Integer usuarioId;

    public Empleado(String nombre, String apellidoPaterno,
            String apellidoMaterno) {
        validarEmpleado(nombre, apellidoPaterno, apellidoMaterno);
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
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
     * @return the categoriaId
     */
    public int getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
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
                this.categoriaId,
                this.usuarioId);
    }

}
