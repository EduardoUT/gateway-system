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

    private final Integer idCategoria;
    private final String nombreCategoria;

    public EmpleadoCategoria(Integer cargoId, String nombreCargo) {
        validarCategoriaEmpleado(cargoId, nombreCargo);
        this.idCategoria = cargoId;
        this.nombreCategoria = nombreCargo;
    }

    /**
     * @return the categoriaId
     */
    public int getCategoriaId() {
        return idCategoria;
    }

    /**
     * @return the nombreCargo
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    private void validarCategoriaEmpleado(Integer cargoId,
            String nombreCargo) {
        if (cargoId < 0) {
            throw new IllegalArgumentException("El campo " + cargoId
                    + " es negativo");
        }

        if (nombreCargo == null || nombreCargo.isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCargo
                    + " está vacío.");
        }
    }

    @Override
    public String toString() {
        return this.nombreCategoria;
    }

}
