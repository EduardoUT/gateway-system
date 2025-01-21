/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.crud;

import java.util.List;

/**
 * Para adaptar la información obtenida de un servicio en las filas de un
 * JTable.
 *
 * @author eduar
 */
public interface DataModelForJTable {

    /**
     * Representa el modelo de filas en un JTable.
     *
     * @return Lista de filas para llenar un JTable.
     */
    List<Object[]> getDataModelForJTable();
}
