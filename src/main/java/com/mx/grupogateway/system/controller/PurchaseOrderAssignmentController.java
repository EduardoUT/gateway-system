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
     * Actualiza la asignación de un proyectoa otro empleado.
     *
     * @param idEmpleado
     * @param poNo
     * @param idEmpleadoAsignado
     * @return
     */
    public int actualizar(String idEmpleado, String poNo,
            String idEmpleadoAsignado) {
        return this.purchaseOrderAssignmentDAO.actualizar(
                idEmpleado, poNo, idEmpleadoAsignado
        );
    }
}
