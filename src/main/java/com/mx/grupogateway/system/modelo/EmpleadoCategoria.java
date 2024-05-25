/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoCategoria {

    private String idCategoria;
    private String nombreCategoria;

    public EmpleadoCategoria(String idCategoria, String nombreCategoria) {
        validarCategoriaEmpleado(idCategoria, nombreCategoria);
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public EmpleadoCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the categoriaId
     */
    public String getCategoriaId() {
        return idCategoria;
    }

    /**
     * @return the nombreCargo
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    private void validarCategoriaEmpleado(String idCategoria,
            String nombreCargo) {
        if (idCategoria.equals("") || idCategoria.isEmpty()) {
            throw new IllegalArgumentException("El campo " + idCategoria
                    + " está vacío");
        }

        if (nombreCargo == null || nombreCargo.isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCargo
                    + " está vacío.");
        }
    }

    @Override
    public String toString() {
        return String.format("[ID Categoria: %s | Nombre: %s]",
                this.idCategoria,
                this.nombreCategoria);
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

}
