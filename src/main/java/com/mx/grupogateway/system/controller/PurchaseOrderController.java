/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eduar
 */
public class PurchaseOrderController {

    private final PurchaseOrderDAO purchaseOrderDAO;

    public PurchaseOrderController() {
        this.purchaseOrderDAO = new PurchaseOrderDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de PurchaseOrder.
     *
     * @param purchaseOrder
     */
    public void guardar(PurchaseOrder purchaseOrder) {
        this.purchaseOrderDAO.guardar(purchaseOrder);
    }

    /**
     * Consulta los identificadores idProject y purchaseOrderIdentifier en un
     * HashMap al ser estos dos identificadores parte de una tabla compuesta.
     *
     * @param purchaseOrderIdentifier Key: Identificador de project.
     * @param purchaseOrderProjectId Value: purchaseOrderIdentifier.
     * @return
     */
    public Map<Long, String> listarPurchaseOrderIdentifiers(
            String purchaseOrderIdentifier, Long purchaseOrderProjectId) {
        return this.purchaseOrderDAO.listarPurchaseOrderIdentifiers(
                purchaseOrderIdentifier,
                purchaseOrderProjectId
        );
    }

    /**
     * Consulta los identificadores de project de acuerdo al
     * purchaseOrderIdentifier.
     *
     * @param purchaseOrder
     * @return
     */
    public List<Long> listarPurchaseOrderProjectIdentifiers(
            PurchaseOrder purchaseOrder) {
        return this.purchaseOrderDAO.listarPurchaseOrderProjectIdentifiers(
                purchaseOrder
        );
    }
}
