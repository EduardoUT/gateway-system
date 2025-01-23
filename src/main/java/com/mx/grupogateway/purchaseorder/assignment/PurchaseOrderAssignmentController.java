/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.assignment;

import com.mx.grupogateway.crud.DataModelForJTable;
import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.site.Site;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author eduar
 */
public class PurchaseOrderAssignmentController implements DataModelForJTable {

    private final PurchaseOrderAssignmentService purchaseOrderAssignmentService;

    public PurchaseOrderAssignmentController() {
        purchaseOrderAssignmentService = new PurchaseOrderAssignmentService();
    }

    /**
     * Crea un nuevo PurchaseOrderAssignment a un Employee.
     *
     * @param purchaseOrderAssignment Orden de compra a a asignar.
     */
    public void create(PurchaseOrderAssignment purchaseOrderAssignment) {
        purchaseOrderAssignmentService.create(purchaseOrderAssignment);
    }

    /**
     * Recibe un List de tipo PurchaseOrderAssignment para construir el modelo
     * de filas de un JTable.
     *
     * @return Lista de Object[] con los datos del PurchaseOrderAssignment a
     * mostrar en el JTable.
     */
    @Override
    public List<Object[]> getDataModelForJTable() {
        List<PurchaseOrderAssignment> purchaseOrderAssignments
                = purchaseOrderAssignmentService.getAll();
        List<Object[]> dataModelPurchaseOrderAssignments = new ArrayList<>();
        if (!purchaseOrderAssignments.isEmpty()) {
            for (PurchaseOrderAssignment purchaseOrderAssignment : purchaseOrderAssignments) {
                Employee empleado = purchaseOrderAssignment.getEmployee();
                PurchaseOrder purchaseOrder = purchaseOrderAssignment.getPurchaseOrder();
                PurchaseOrderDetail purchaseOrderDetail = purchaseOrder.getPurchaseOrderDetail();
                Project project = purchaseOrder.getProject();
                Site site = project.getSite();
                dataModelPurchaseOrderAssignments.add(
                        new Object[]{
                            empleado.getId(),
                            empleado.getName(),
                            empleado.getPaternalSurname(),
                            empleado.getMaternalSurname(),
                            purchaseOrderAssignment.getAssignmentDate(),
                            project.getId(),
                            purchaseOrderDetail.getId(),
                            purchaseOrderAssignment.getAmount(),
                            purchaseOrderAssignment.getTotalPayment(),
                            purchaseOrderAssignment.getStatus(),
                            project.getCustomer(),
                            project.getProjectName(),
                            purchaseOrderDetail.getPoStatus(),
                            purchaseOrder.getPoLineNo(),
                            site.getSiteCode(),
                            site.getSiteName(),
                            purchaseOrderDetail.getItemDesc(),
                            purchaseOrderDetail.getRequestedQty(),
                            purchaseOrder.getDueQty(),
                            purchaseOrder.getBilledQty(),
                            purchaseOrder.getUnitPrice(),
                            purchaseOrderDetail.getLineAmount(),
                            purchaseOrder.getUnit(),
                            purchaseOrderDetail.getPaymentTerms(),
                            project.getCategory(),
                            project.getPublishDate()
                        }
                );
            }
            return dataModelPurchaseOrderAssignments;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Actualiza una asignación a un Employee diferente.
     *
     * @param empleadoActualId
     * @param empleadoNuevoId
     * @param purchaseOrderAssignment
     */
    public void updateAssignment(Integer empleadoActualId, Integer empleadoNuevoId,
            PurchaseOrderAssignment purchaseOrderAssignment) {
        purchaseOrderAssignmentService.updateAssignment(
                empleadoActualId, empleadoNuevoId, purchaseOrderAssignment);
    }
}
