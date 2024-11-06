/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Contiene métodos que son comunmente utilizados en los formularios.
 *
 * @author Eduardo Reyes Hernández
 */
public class AccionesTabla {

    private AccionesTabla() {
    }

    /**
     * Ejecuta una busqueda de registros acorde al combobox y el texto a buscar,
     * actualiza la tabla a los reguistros que coincidan.
     *
     * @param tabla Tabla a filtrar.
     * @param campo Campo a evaluar.
     * @param comboBox Seleccion (debe tener almenos un valor que coincida con
     * uno de los identificaores de columna de la tabla).
     */
    public static void filtrarResultados(JTable tabla, JTextField campo,
            JComboBox<String> comboBox) {
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tabla.getModel());
        campo.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = campo.getText();
                String seleccion = (String) comboBox.getSelectedItem();
                comboBox.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(
                            RowFilter.regexFilter(
                                    "(?i)" + text,
                                    tabla
                                            .getColumnModel()
                                            .getColumnIndex(
                                                    seleccion
                                            )
                            )
                    );
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        tabla.setRowSorter(rowSorter);
    }

    /**
     * Al dar click en una fila en la tabla devuelve el índice de la fila.
     *
     * @param tabla JTable a evaluar.
     * @return Número de fila seleccionada.
     */
    public static int indiceFila(JTable tabla) {
        return tabla.getSelectedRow();
    }

    /**
     * Obtiene el valor de una tabla acorde a los índices de la fila y columnas
     * seleccionadas.
     *
     * @param tabla Tabla a ser evaluada.
     * @param fila Índice de la fila.
     * @param columna Índice de la columna.
     * @return Retorna un objeto con el valor.
     */
    public static Object obtenerValorTabla(JTable tabla, int fila, int columna) {
        return tabla.getValueAt(fila, columna);
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
     * @return Id del registro seleccionado.
     */
    public static int obtenerID(JTable tabla, Integer indexColumnID) {
        if (filaEstaSeleccionada(tabla)) {
            int fila = indiceFila(tabla);
            return Integer.parseInt(
                    tabla.getValueAt(fila, indexColumnID).toString());
        }
        return 0;
    }

    /**
     * Obtiene el UUID de la fila seleccionada en la tabla.
     *
     * @param tabla Tabla seleccionada.
     * @param indexColumnID Índice donde el campo ID en la tabla se encuentra.
     * @return UUID del registro seleccionado.
     */
    public static String obtenerUUID(JTable tabla, Integer indexColumnID) {
        if (filaEstaSeleccionada(tabla)) {
            int fila = indiceFila(tabla);
            return String.valueOf(tabla.getValueAt(fila, indexColumnID));
        }
        return "";
    }
}
