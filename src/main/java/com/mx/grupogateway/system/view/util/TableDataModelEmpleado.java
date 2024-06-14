/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.modelo.Empleado;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelEmpleado {

    private final DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final List<Empleado> empleados;

    public TableDataModelEmpleado(DefaultTableModel modeloTabla, JTable tabla,
            List<Empleado> empleados) {
        this.tabla = tabla;
        this.modeloTabla = modeloTabla;
        this.empleados = empleados;
    }

    public void cargarModeloTablaEmpleados() {
        modeloTabla.getDataVector().clear();
        tabla.clearSelection();
        tabla.getTableHeader().setFont(tabla.getFont());
        empleados.forEach(empleado -> {
            modeloTabla.addRow(
                    new Object[]{
                        empleado.getIdEmpleado(),
                        empleado.getNombre(),
                        empleado.getApellidoPaterno(),
                        empleado.getApellidoMaterno(),
                        empleado.getUsuario().getIdUsuario(),
                        empleado.getEmpleadoCategoria().getNombreCategoria()
                    }
            );
        });
    }
}
