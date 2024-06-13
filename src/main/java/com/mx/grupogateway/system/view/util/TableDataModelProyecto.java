/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.modelo.Proyecto;
import com.mx.grupogateway.system.util.IdentificadoresProyecto;
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

    public void cargarTablaProyectos() {
        modeloTabla.getDataVector().clear();
        tabla.clearSelection();
        modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setColumnIdentifiers(
                IdentificadoresProyecto.values()
        );
        proyectos.forEach((proyecto) -> {
            modeloTabla.addRow(
                    new Object[]{
                        proyecto.getIdProyecto(),
                        proyecto.getPoNo(),
                        proyecto.getPoStatus(),
                        proyecto.getPoLineNo(),
                        proyecto.getProjectCode(),
                        proyecto.getProjectName(),
                        proyecto.getCustomer(),
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
