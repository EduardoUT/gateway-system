/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.model;

import com.mx.grupogateway.system.modelo.Proyecto;
import com.mx.grupogateway.system.view.util.AccionesTabla;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelProyecto {

    private DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final List<Proyecto> proyectos;

    public TableDataModelProyecto(DefaultTableModel modeloTabla, JTable tabla,
            List<Proyecto> proyectos) {
        this.tabla = tabla;
        this.modeloTabla = modeloTabla;
        this.proyectos = proyectos;
    }

    public void cargarModeloTablaProyecto() {
        AccionesTabla.limpiarTabla(modeloTabla, tabla);
        modeloTabla = (DefaultTableModel) tabla.getModel();
        tabla.getTableHeader().setFont(tabla.getFont());
        proyectos.forEach((proyecto) -> {
            modeloTabla.addRow(
                    new Object[]{
                        proyecto.getIdProyecto(),
                        proyecto.getProjectCode(),
                        proyecto.getProjectName(),
                        proyecto.getCustomer(),
                        proyecto.getPoStatus(),
                        proyecto.getPoNo(),
                        proyecto.getPoLineNo(),
                        proyecto.getShipmentNo(),
                        proyecto.getSiteCode(),
                        proyecto.getSiteName(),
                        proyecto.getItemCode(),
                        proyecto.getItemDesc(),
                        proyecto.getRequestedQty(),
                        proyecto.getDueQty(),
                        proyecto.getBilledQty(),
                        proyecto.getUnitPrice(),
                        proyecto.getLineAmount(),
                        proyecto.getUnit(),
                        proyecto.getPaymentTerms(),
                        proyecto.getCategory(),
                        proyecto.getBiddingArea(),
                        proyecto.getPublishDate()
                    }
            );
        });
    }
}
