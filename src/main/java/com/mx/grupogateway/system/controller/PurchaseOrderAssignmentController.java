/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderAssignmentDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.PurchaseOrderAssignment;
import java.util.List;

/**
 *
 * @author eduar
 */
public class PurchaseOrderAssignmentController {

    private final PurchaseOrderAssignmentDAO purchaseOrderAssignmentDAO;
    private final PurchaseOrderController purchaseOrderController;

    public PurchaseOrderAssignmentController() {
        this.purchaseOrderAssignmentDAO = new PurchaseOrderAssignmentDAO(
                new ConnectionFactory().realizarConexion()
        );
        this.purchaseOrderController = new PurchaseOrderController();
    }

    /**
     * Evalúa si la asignación por medio del purchaseOrderIdentifier contiene
     * una o más líneas.
     *
     * Guarda una lista de tipo ProjectAssignment de acuerdo alas
     * purchasOrderIdentifier obtenidas en el listado consultado.
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
        }
    }

    /**
     * Lista de asignaciones, con el empleado asociado al proyecto.
     *
     * @return
     */
    public List<PurchaseOrderAssignment> listar() {
        return this.purchaseOrderAssignmentDAO.listar();
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
                    empleadoActualId, empleadoNuevoId, purchaseOrderAssignment, purchaseOrderProjectIdentifiers
            );
        }
    }
}
