/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.model;

import com.mx.grupogateway.system.modelo.PurchaseOrderAssignment;
import com.mx.grupogateway.system.view.util.AccionesTabla;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelAsignacion {

    private final DefaultTableModel modeloTabla;
    private final List<PurchaseOrderAssignment> purchaseOrderAssignments;
    private final JTable tabla;

    public TableDataModelAsignacion(DefaultTableModel modeloTabla, JTable tabla,
            List<PurchaseOrderAssignment> purchaseOrderAssignments) {
        this.tabla = tabla;
        this.modeloTabla = modeloTabla;
        this.purchaseOrderAssignments = purchaseOrderAssignments;
    }

    public void cargarModeloTablaAsignaciones() {
        AccionesTabla.limpiarTabla(modeloTabla, tabla);
        tabla.getTableHeader().setFont(tabla.getFont());
        purchaseOrderAssignments.forEach((purchaseOrderAssignment) -> {
            modeloTabla.addRow(
                    new Object[]{
                        purchaseOrderAssignment.getEmpleado().getIdEmpleado(),
                        purchaseOrderAssignment.getEmpleado().getNombre(),
                        purchaseOrderAssignment.getEmpleado().getApellidoPaterno(),
                        purchaseOrderAssignment.getEmpleado().getApellidoMaterno(),
                        purchaseOrderAssignment.getFechaAsignacion(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getProjectId(),
                        purchaseOrderAssignment.getPurchaseOrder().getPurchaseOrderDetail().getPurchaseOrderIdentifier(),
                        purchaseOrderAssignment.getImporte(),
                        purchaseOrderAssignment.getTotalPagar(),
                        purchaseOrderAssignment.getStatus(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getCustomer(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getProjectName(),
                        purchaseOrderAssignment.getPurchaseOrder().getPurchaseOrderDetail().getPoStatus(),
                        purchaseOrderAssignment.getPurchaseOrder().getPoLineNo(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getSite().getSiteCode(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getSite().getSiteName(),
                        purchaseOrderAssignment.getPurchaseOrder().getPurchaseOrderDetail().getItemDesc(),
                        purchaseOrderAssignment.getPurchaseOrder().getPurchaseOrderDetail().getRequestedQty(),
                        purchaseOrderAssignment.getPurchaseOrder().getDueQty(),
                        purchaseOrderAssignment.getPurchaseOrder().getBilledQty(),
                        purchaseOrderAssignment.getPurchaseOrder().getUnitPrice(),
                        purchaseOrderAssignment.getPurchaseOrder().getPurchaseOrderDetail().getLineAmount(),
                        purchaseOrderAssignment.getPurchaseOrder().getUnit(),
                        purchaseOrderAssignment.getPurchaseOrder().getPurchaseOrderDetail().getPaymentTerms(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getCategory(),
                        purchaseOrderAssignment.getPurchaseOrder().getProject().getPublishDate()
                    }
            );
        });
    }
}
