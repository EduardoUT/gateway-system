/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.detail;

import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.purchaseorder.PurchaseOrderStatus;
import java.util.List;

/**
 *
 * @author eduar
 */
public class PurchaseOrderDetailController {

    private final PurchaseOrderDetailImpl purchaseOrderDetailImpl;

    public PurchaseOrderDetailController() {
        this.purchaseOrderDetailImpl = new PurchaseOrderDetailImpl(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de PurchaseOrderDetail.
     *
     * @param purchaseOrderDetail
     */
    public void create(PurchaseOrderDetail purchaseOrderDetail) {
        this.purchaseOrderDetailImpl.create(purchaseOrderDetail);
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
        return this.purchaseOrderDetailImpl.getAllById(purchaseOrderIdentifier);
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
        this.purchaseOrderDetailImpl.update(purchaseOrderDetail);
    }
}
