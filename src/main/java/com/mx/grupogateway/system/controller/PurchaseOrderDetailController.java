/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderDetailDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;

/**
 *
 * @author eduar
 */
public class PurchaseOrderDetailController {

    private final PurchaseOrderDetailDAO purchaseOrderDetailDAO;

    public PurchaseOrderDetailController() {
        this.purchaseOrderDetailDAO = new PurchaseOrderDetailDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de PurchaseOrderDetail.
     *
     * @param purchaseOrderDetail
     */
    public void guardar(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetailDAO.guardar(purchaseOrderDetail);
    }
}
