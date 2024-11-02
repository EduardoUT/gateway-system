/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.PurchaseOrder;

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
}
