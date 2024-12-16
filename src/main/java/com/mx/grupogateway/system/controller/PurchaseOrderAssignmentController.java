/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderAssignmentDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderAssignment;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import com.mx.grupogateway.system.modelo.Site;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author eduar
 */
public class PurchaseOrderAssignmentController {

    private final PurchaseOrderAssignmentDAO purchaseOrderAssignmentDAO;
    private final PurchaseOrderController purchaseOrderController;
    private final PurchaseOrderDetailController purchaseOrderDetailController;

    public PurchaseOrderAssignmentController() {
        this.purchaseOrderAssignmentDAO = new PurchaseOrderAssignmentDAO(
                new ConnectionFactory().realizarConexion()
        );
        this.purchaseOrderController = new PurchaseOrderController();
        this.purchaseOrderDetailController = new PurchaseOrderDetailController();
    }

    /**
     * Evalúa si la asignación por medio del purchaseOrderIdentifier contiene
     * una o más líneas.
     *
     * Guarda una lista de tipo ProjectAssignment de acuerdo alas
     * purchasOrderIdentifier obtenidas en el listado consultado.
     *
     * Actualiza el PO_STATUS correspondiente al purchaseOrderIdentifier
     * proporcionado.
     *
     * @param purchaseOrderAssignment
     */
    public void guardar(PurchaseOrderAssignment purchaseOrderAssignment) {
        List<Long> purchaseOrderProjectIdentifiers
                = this.purchaseOrderController.
                        listarPurchaseOrderProjectIdentifiers(
                                purchaseOrderAssignment.getPurchaseOrder()
                        );
        if (!purchaseOrderProjectIdentifiers.isEmpty()) {
            this.purchaseOrderAssignmentDAO.guardar(
                    purchaseOrderAssignment,
                    purchaseOrderProjectIdentifiers
            );
            this.purchaseOrderDetailController
                    .actualizarPurchaseOrderDetailStatus(
                            purchaseOrderAssignment
                                    .getPurchaseOrder()
                                    .getPurchaseOrderDetail()
                    );
        }
    }

    /**
     * Lista de asignaciones, con el empleado asociado al proyecto.
     *
     * @return
     */
    public List<Object[]> listar() {
        List<PurchaseOrderAssignment> purchaseOrderAssignments
                = this.purchaseOrderAssignmentDAO.listar();
        List<Object[]> dataModelPurchaseOrderAssignments = new ArrayList<>();
        if (!purchaseOrderAssignments.isEmpty()) {
            for (PurchaseOrderAssignment purchaseOrderAssignment : purchaseOrderAssignments) {
                Empleado empleado = purchaseOrderAssignment.getEmpleado();
                PurchaseOrder purchaseOrder = purchaseOrderAssignment.getPurchaseOrder();
                PurchaseOrderDetail purchaseOrderDetail = purchaseOrder.getPurchaseOrderDetail();
                Project project = purchaseOrder.getProject();
                Site site = project.getSite();
                dataModelPurchaseOrderAssignments.add(
                        new Object[]{
                            empleado.getIdEmpleado(),
                            empleado.getNombre(),
                            empleado.getApellidoPaterno(),
                            empleado.getApellidoMaterno(),
                            purchaseOrderAssignment.getFechaAsignacion(),
                            project.getProjectId(),
                            purchaseOrderDetail.getPurchaseOrderDetailIdentifier(),
                            purchaseOrderAssignment.getImporte(),
                            purchaseOrderAssignment.getTotalPagar(),
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
     * Consulta el listado de projectId que coinciden con el
     * purchaseOrderIdentifier, posteriormente realiza la actualización para los
     * registros coincidentes.
     *
     * @param empleadoNuevoId
     * @param empleadoActualId
     * @param purchaseOrderAssignment
     */
    public void actualizar(Integer empleadoActualId, Integer empleadoNuevoId,
            PurchaseOrderAssignment purchaseOrderAssignment) {
        List<Long> purchaseOrderProjectIdentifiers
                = this.purchaseOrderController
                        .listarPurchaseOrderProjectIdentifiers(
                                purchaseOrderAssignment.getPurchaseOrder()
                        );
        if (!purchaseOrderProjectIdentifiers.isEmpty()) {
            this.purchaseOrderAssignmentDAO.actualizar(
                    empleadoActualId,
                    empleadoNuevoId,
                    purchaseOrderAssignment,
                    purchaseOrderProjectIdentifiers
            );
        }
    }
}
