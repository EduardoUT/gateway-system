/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderDetailDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import com.mx.grupogateway.system.modelo.PurchaseOrderStatus;
import java.util.List;

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

    /**
     * Consulta el listado de purchaseOrderIdentifier de acuerdo al mismo
     * identificador proporcionado.
     *
     * @param purchaseOrderIdentifier
     * @return
     */
    public List<String> listarPurchaseOrderDetailIdentifiers(
            String purchaseOrderIdentifier) {
        return this.purchaseOrderDetailDAO.listarPurchaseOrderDetailIdentifiers(
                purchaseOrderIdentifier
        );
    }

    /**
     * Realiza el cambio del Status a ASSIGNED correspondiente al
     * purchasOrderIdentifier proporcionado, útil para el manoejo y control de
     * nuevas asignaciones.
     *
     * @param purchaseOrderDetail
     */
    public void actualizarPurchaseOrderDetailStatus(
            PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetail
                .setPoStatus(PurchaseOrderStatus.ASSIGNED.toString());
        this.purchaseOrderDetailDAO.actualizarPurchaseOrderDetailStatus(
                purchaseOrderDetail
        );
    }
}
