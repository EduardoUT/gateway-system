/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mx.grupogateway.system.view;

/**
 *
 * @author eduar
 */
public enum UsuarioColumnTitles {
    ID_USUARIO("ID Usuario"),
    NOMBRE_USUARIO("Nombre Usuario");

    private final String columnTitle;

    private UsuarioColumnTitles(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    @Override
    public String toString() {
        return this.columnTitle;
    }
}
