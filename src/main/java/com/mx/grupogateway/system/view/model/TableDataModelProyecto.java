/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.model;

import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.view.util.AccionesTabla;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelProyecto {

    private DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final List<Project> projects;

    public TableDataModelProyecto(DefaultTableModel modeloTabla, JTable tabla,
            List<Project> projects) {
        this.tabla = tabla;
        this.modeloTabla = modeloTabla;
        this.projects = projects;
    }

    public void cargarModeloTablaProyecto() {
        AccionesTabla.limpiarTabla(modeloTabla, tabla);
        modeloTabla = (DefaultTableModel) tabla.getModel();
        tabla.getTableHeader().setFont(tabla.getFont());
        projects.forEach((project) -> {
            modeloTabla.addRow(
                    new Object[]{
                        project.getProjectId(),
                        project.getProjectCode(),
                        project.getProjectName(),
                        project.getCustomer(),
                        project.getPublishDate()
                    }
            );
        });
    }
}
