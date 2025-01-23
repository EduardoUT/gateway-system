/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.assignment;

import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.purchaseorder.PurchaseOrderController;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetailController;
import java.util.List;

/**
 *
 * @author eduar
 */
public class PurchaseOrderAssignmentService {

    private final PurchaseOrderAssignmentImpl purchaseOrderAssignmentImpl;
    private final PurchaseOrderController purchaseOrderController;
    private final PurchaseOrderDetailController purchaseOrderDetailController;

    public PurchaseOrderAssignmentService() {
        purchaseOrderAssignmentImpl = new PurchaseOrderAssignmentImpl(
                new ConnectionFactory().realizarConexion()
        );
        purchaseOrderController = new PurchaseOrderController();
        purchaseOrderDetailController = new PurchaseOrderDetailController();
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
    public void create(PurchaseOrderAssignment purchaseOrderAssignment) {
        List<Long> purchaseOrderProjectIdentifiers
                = purchaseOrderController.listarPurchaseOrderProjectIdentifiers(
                        purchaseOrderAssignment.getPurchaseOrder()
                );
        if (!purchaseOrderProjectIdentifiers.isEmpty()) {
            purchaseOrderAssignmentImpl.create(
                    purchaseOrderAssignment,
                    purchaseOrderProjectIdentifiers
            );
            purchaseOrderDetailController.update(
                    purchaseOrderAssignment
                            .getPurchaseOrder()
                            .getPurchaseOrderDetail()
            );
        }
    }

    /**
     * Lista de asignaciones, con el empleado asociado al proyecto.
     *
     * @return PurchaseOrderAssignment con el detalle de asignación de proyecto
     * y orden de compra.
     */
    public List<PurchaseOrderAssignment> getAll() {
        return purchaseOrderAssignmentImpl.getAll();
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
    public void updateAssignment(Integer empleadoActualId, Integer empleadoNuevoId,
            PurchaseOrderAssignment purchaseOrderAssignment) {
        List<Long> purchaseOrderProjectIdentifiers
                = purchaseOrderController.listarPurchaseOrderProjectIdentifiers(
                        purchaseOrderAssignment.getPurchaseOrder()
                );
        if (!purchaseOrderProjectIdentifiers.isEmpty()) {
            purchaseOrderAssignmentImpl.updateAssignment(
                    empleadoActualId,
                    empleadoNuevoId,
                    purchaseOrderAssignment,
                    purchaseOrderProjectIdentifiers
            );
        }
    }
}
