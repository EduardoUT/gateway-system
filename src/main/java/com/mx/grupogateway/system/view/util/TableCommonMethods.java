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
     * selección de fila que se modificó y vacía los registros en la tabla.
     *
     * @param modeloTabla Modelo al que se limpiarán los registros.
     * @param tabla JTable al que se limpiará la selección.
     */
    public static void limpiarTabla(DefaultTableModel modeloTabla, JTable tabla) {
        modeloTabla.getDataVector().clear();
        tabla.clearSelection();
    }

    /**
     * Limpia la selección en la tabla.
     *
     * @param tabla JTable al que se limpiará la selección.
     */
    public static void limpiarSeleccion(JTable tabla) {
        tabla.clearSelection();
    }

    /**
     * Obtiene el valor del id correspondiente a la fila seleccionada en la
     * tabla, el usuario debe índicar el índice de la columna donde el campo
     * único identificador ID se encuentra.
     *
     * @param tabla Tabla seleccionada.
     * @param indexColumnID Índice donde el campo único identificador de
     * encuentra.
     * @return ïd del empleado.
     */
    public static int obtenerID(JTable tabla, Integer indexColumnID) {
        if (TableCommonMethods.filaEstaSeleccionada(tabla)) {
            int fila = TableCommonMethods.indiceFilaSeleccionada(tabla);
            int Id = Integer.valueOf(
                    tabla.getValueAt(fila, indexColumnID).toString()
            );
            return Id;
        }
        return 0;
    }

}
