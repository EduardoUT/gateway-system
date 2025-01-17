/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.util;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Realiza el ajuste interno de las columnas de un JTable acorde al contenido de
 * las celdas.
 *
 * @author Eduardo Reyes Hernández
 */
public class MarginTable {

    private MarginTable() {
    }

    private static final Integer MARGEN_COLUMNA = 2;

    /**
     * Cada vez que el contenido de los registros se actualice debe llamarse
     * después esta función.
     *
     * Toma laa tabla a la que se desea ajustar el tamaño de sus columnas, y por
     * medio de un ciclo for, cuenta el número de columnas que posee, lo pasa al
     * método ajustarAnchoColumnas() para realizar el ajuste automático.
     *
     * El modelo de la tabla debe poseer las siguientes configuraciones para que
     * surta efecto:
     *
     * Las columnas deben tener habilitada la opción autoResizeMode ->
     * ALL_COLUMNS.
     *
     * Puede habilitar la opción de TableHeader -> ResizeMode, pero no tendría
     * sentido si no se desea romper el ajuste automático que la función
     * proporciona.
     *
     * @param tabla JTable a ser ajustada.
     */
    public static void setMarginColumns(JTable tabla) {
        for (int indiceColumna = 0; indiceColumna < tabla.getColumnCount();
                indiceColumna++) {
            ajustarMargen(tabla, indiceColumna, MARGEN_COLUMNA);
        }
    }

    /**
     * Permite ajustar el ancho de las columnas y las filas de las tablas,
     * acorde al contenido más largo, a fin de mostrar completamente toda la
     * información de cada fila.
     *
     * @param tabla JTable a ser ajustada.
     * @param indiceColumna Número de la columna en el JTable.
     * @param margenColumna Constante MARGEN_COLUMNA.
     */
    private static void ajustarMargen(JTable tabla, Integer indiceColumna,
            Integer margenColumna) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) tabla.getColumnModel();
        TableColumn col = colModel.getColumn(indiceColumna);
        int ancho;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = tabla.getTableHeader().getDefaultRenderer();
        }
        Component component = renderer.getTableCellRendererComponent(tabla, col.getHeaderValue(), false, false, 0, 0);
        ancho = component.getPreferredSize().width;
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            renderer = tabla.getCellRenderer(fila, indiceColumna);
            component = renderer.getTableCellRendererComponent(tabla, tabla.getValueAt(fila, indiceColumna), false, false, fila, indiceColumna);
            ancho = Math.max(ancho, component.getPreferredSize().width);
        }
        ancho += 2 * margenColumna;
        col.setPreferredWidth(ancho);
    }
}
