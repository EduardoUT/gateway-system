/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.model;

import com.mx.grupogateway.system.modelo.PurchaseOrder;
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
    private final List<PurchaseOrder> purchaseOrders;

    public TableDataModelProyecto(DefaultTableModel modeloTabla, JTable tabla,
            List<PurchaseOrder> purchaseOrders) {
        this.tabla = tabla;
        this.modeloTabla = modeloTabla;
        this.purchaseOrders = purchaseOrders;
    }

    public void cargarModeloTablaProyecto() {
        AccionesTabla.limpiarTabla(modeloTabla, tabla);
        modeloTabla = (DefaultTableModel) tabla.getModel();
        tabla.getTableHeader().setFont(tabla.getFont());
        purchaseOrders.forEach((purchaseOrder) -> {
            modeloTabla.addRow(
                    new Object[]{
                        purchaseOrder.getProject().getProjectId(),
                        purchaseOrder.getProject().getProjectCode(),
                        purchaseOrder.getProject().getProjectName(),
                        purchaseOrder.getProject().getCustomer(),
                        purchaseOrder.getPurchaseOrderDetail().getPoStatus(),
                        purchaseOrder.getPurchaseOrderDetail().getPurchaseOrderIdentifier(),
                        purchaseOrder.getProject().getSite().getShipmentNo(),
                        purchaseOrder.getPoLineNo(),
                        purchaseOrder.getProject().getSite().getSiteCode(),
                        purchaseOrder.getProject().getSite().getSiteName(),
                        purchaseOrder.getPurchaseOrderDetail().getItemCode(),
                        purchaseOrder.getPurchaseOrderDetail().getItemDesc(),
                        purchaseOrder.getPurchaseOrderDetail().getRequestedQty(),
                        purchaseOrder.getDueQty(),
                        purchaseOrder.getBilledQty(),
                        purchaseOrder.getUnitPrice(),
                        purchaseOrder.getPurchaseOrderDetail().getLineAmount(),
                        purchaseOrder.getUnit(),
                        purchaseOrder.getPurchaseOrderDetail().getPaymentTerms(),
                        purchaseOrder.getProject().getCategory(),
                        purchaseOrder.getProject().getSite().getBiddigArea(),
                        purchaseOrder.getProject().getPublishDate()
                    }
            );
        });
    }
}
