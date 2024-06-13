/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.modelo.ProyectoAsignado;
import com.mx.grupogateway.system.util.IdentificadoresProyectoAsignacion;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelAsignacion {

    private final DefaultTableModel modeloTabla;
    private final List<ProyectoAsignado> proyectosAsignados;
    private final JTable tabla;

    public TableDataModelAsignacion(DefaultTableModel modeloTabla, JTable tabla,
            List<ProyectoAsignado> proyectosAsignados) {
        this.tabla = tabla;
        this.modeloTabla = modeloTabla;
        this.proyectosAsignados = proyectosAsignados;
    }

    public void cargarTablaAsignaciones() {
        modeloTabla.getDataVector().clear();
        tabla.clearSelection();
        modeloTabla.setColumnIdentifiers(
                IdentificadoresProyectoAsignacion.values()
        );
        proyectosAsignados.forEach((proyectoAsignado) -> {
            modeloTabla.addRow(
                    new Object[]{
                        proyectoAsignado.getEmpleado().getIdEmpleado(),
                        proyectoAsignado.getEmpleado().getNombre(),
                        proyectoAsignado.getEmpleado().getApellidoPaterno(),
                        proyectoAsignado.getEmpleado().getApellidoMaterno(),
                        proyectoAsignado.getFechaAsignacion(),
                        proyectoAsignado.getIdProyecto(),
                        proyectoAsignado.getPoNo(),
                        proyectoAsignado.getImporte(),
                        proyectoAsignado.getTotalPagar(),
                        proyectoAsignado.getStatus(),
                        proyectoAsignado.getCustomer(),
                        proyectoAsignado.getProjectName(),
                        proyectoAsignado.getPoNo(),
                        proyectoAsignado.getPoStatus(),
                        proyectoAsignado.getPoLineNo(),
                        proyectoAsignado.getSiteCode(),
                        proyectoAsignado.getSiteName(),
                        proyectoAsignado.getItemDesc(),
                        proyectoAsignado.getRequestedQty(),
                        proyectoAsignado.getDueQty(),
                        proyectoAsignado.getBilledQty(),
                        proyectoAsignado.getUnitPrice(),
                        proyectoAsignado.getLineAmount(),
                        proyectoAsignado.getUnit(),
                        proyectoAsignado.getPaymentTerms(),
                        proyectoAsignado.getCategory(),
                        proyectoAsignado.getPublishDate()
                    }
            );
        });
    }

}
