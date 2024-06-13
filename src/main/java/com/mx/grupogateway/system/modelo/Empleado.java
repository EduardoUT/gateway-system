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

    private String idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Usuario usuario;
    private EmpleadoCategoria empleadoCategoria;

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
        this.idEmpleado = empleadoId;
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
    }
    
    public Empleado(String idEmpleado, String nombre, String apellidoPaterno,
            String apellidoMaterno) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

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
        this.idEmpleado = UUIDOperations.generarIdentificador();
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        //this.usuarioId = DEFAULT_USUARIO_ID;
    }

    /**
     * Constructor para asignar el empleadoId.
     *
     * @param idEmpleado
     */
    public Empleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the idEmpleado
     */
    public String getIdEmpleado() {
        return idEmpleado;
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
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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

    public void setEmpleadoCategoriaId(String idCategoria) {
        this.empleadoCategoria.setIdCategoria(idCategoria);
    }

    public void setUsuarioId(String idUsuario) {
        this.usuario.setUsuarioId(idUsuario);
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
                this.idEmpleado, this.getNombre(), this.getApellidoPaterno(), this.getApellidoMaterno(),
                this.getEmpleadoCategoria().getCategoriaId(),
                this.getUsuario().getIdUsuario());
    }
}
