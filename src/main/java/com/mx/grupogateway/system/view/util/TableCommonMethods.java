/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class TableCommonMethods {

    /**
     * Al dar click en una fila en la tabla devuelve el índice de la fila.
     *
     * @param tabla JTable a evaluar.
     * @return Número de fila seleccionada.
     */
    public static int indiceFilaSeleccionada(JTable tabla) {
        return tabla.getSelectedRow();
    }

    /**
     * Comprueba si una determinada tabla está seleccionada.
     *
     * @param tabla JTable a ser evaluada.
     * @return Boleano y mensaje de aviso en caso de no estar seleccionada.
     */
    public static boolean filaEstaSeleccionada(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Para hacer esta acción debe seleccionar "
                    + "primero una fila en la tabla.",
                    "Fila no seleccionada.",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Cuando haya modificaciones en los registros de una tabla, se limpia la
     * selección de fila que se modificó.
     *
     * @param modeloTabla DafaultTableModel específicado por el usuario.
     * @param tabla JTable al que se limpiará la selección.
     */
    public static void limpiarSeleccionTabla(DefaultTableModel modeloTabla, JTable tabla) {
        modeloTabla.getDataVector().clear();
        tabla.clearSelection();
    }

}
