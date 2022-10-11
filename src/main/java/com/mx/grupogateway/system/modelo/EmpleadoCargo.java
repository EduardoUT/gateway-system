/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoCargo {

    private final Integer cargoId;
    private final String nombreCargo;

    public EmpleadoCargo(Integer cargoId, String nombreCargo) {
        validarCategoriaEmpleado(cargoId, nombreCargo);
        this.cargoId = cargoId;
        this.nombreCargo = nombreCargo;
    }

    /**
     * @return the categoriaId
     */
    public int getCargoId() {
        return cargoId;
    }

    /**
     * @return the nombreCargo
     */
    public String getNombreCargo() {
        return nombreCargo;
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
        return this.nombreCargo;
    }

}
