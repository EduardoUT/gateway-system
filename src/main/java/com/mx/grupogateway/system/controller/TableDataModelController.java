/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderAssignment;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.util.AccionesTabla;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelController {

    public void setTableDataModelEmpleados(DefaultTableModel modeloTablaEmpleados,
            JTable tablaEmpleados, List<Empleado> empleados) {
        AccionesTabla.limpiarTabla(modeloTablaEmpleados, tablaEmpleados);
        tablaEmpleados.getTableHeader().setFont(tablaEmpleados.getFont());
        for (Empleado empleado : empleados) {
            modeloTablaEmpleados.addRow(
                    new Object[]{
                        empleado.getIdEmpleado(),
                        empleado.getNombre(),
                        empleado.getApellidoPaterno(),
                        empleado.getApellidoMaterno(),
                        empleado.getUsuario().getIdUsuario(),
                        empleado.getEmpleadoCategoria().getNombreCategoria()
                    }
            );
        }
    }

    public void cargarModeloTablaAsignaciones(DefaultTableModel modeloTablaAsignaciones,
            JTable tablaAsignaciones, List<PurchaseOrderAssignment> purchaseOrderAssignments) {
        AccionesTabla.limpiarTabla(modeloTablaAsignaciones, tablaAsignaciones);
        tablaAsignaciones.getTableHeader().setFont(tablaAsignaciones.getFont());
        for (PurchaseOrderAssignment purchaseOrderAssignment : purchaseOrderAssignments) {
            modeloTablaAsignaciones.addRow(
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
        }
    }

    public void cargarModeloTablaProyecto(DefaultTableModel modeloTablaPurchaseOrder,
            JTable tablaPuchaseOrders, List<PurchaseOrder> purchaseOrders) {
        AccionesTabla.limpiarTabla(modeloTablaPurchaseOrder, tablaPuchaseOrders);
        modeloTablaPurchaseOrder = (DefaultTableModel) tablaPuchaseOrders.getModel();
        tablaPuchaseOrders.getTableHeader().setFont(tablaPuchaseOrders.getFont());
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            modeloTablaPurchaseOrder.addRow(
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
        }
    }

    public void cargarModeloTablaUsuario(DefaultTableModel modeloTablaUsuario,
            JTable tablaUsuario, List<Usuario> usuarios) {
        AccionesTabla.limpiarTabla(modeloTablaUsuario, tablaUsuario);
        modeloTablaUsuario = (DefaultTableModel) tablaUsuario.getModel();
        tablaUsuario.getTableHeader().setFont(tablaUsuario.getFont());
        for (Usuario usuario : usuarios) {
            modeloTablaUsuario.addRow(
                    new Object[]{
                        usuario.getIdUsuario(),
                        usuario.getNombreUsuario(),
                        usuario.getClaveSeguridad()
                    }
            );
        }
    }
}
