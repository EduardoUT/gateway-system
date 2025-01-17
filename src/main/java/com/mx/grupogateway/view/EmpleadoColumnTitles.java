/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mx.grupogateway.view;

/**
 *
 * @author eduar
 */
public enum EmpleadoColumnTitles {
    ID_EMPLEADO("ID Empleado"),
    NOMBRE("Nombre"),
    APELLIDO_PATERNO("Apellido Paterno"),
    APELLIDO_MATERNO("Apellido Materno"),
    ID_USUARIO("ID Usuario"),
    CARGO("Cargo");

    private final String columnTitle;

    private EmpleadoColumnTitles(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    @Override
    public String toString() {
        return columnTitle;
    }
}
