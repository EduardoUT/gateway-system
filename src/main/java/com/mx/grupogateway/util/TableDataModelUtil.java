/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.util;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelUtil {

    private TableDataModelUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void loadTableDataModel(TableModel tableModel,
            JTable jTable, List<Object[]> rowsData, String[] columns) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableModel;
        AccionesTabla.limpiarTabla(defaultTableModel, jTable);
        defaultTableModel.setColumnIdentifiers(columns);
        jTable.getTableHeader().setFont(jTable.getFont());
        for (Object[] rowData : rowsData) {
            defaultTableModel.addRow(rowData);
        }
        MarginTable.setMarginColumns(jTable);
    }
}
