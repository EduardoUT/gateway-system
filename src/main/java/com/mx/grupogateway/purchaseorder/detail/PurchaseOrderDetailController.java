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
        purchaseOrderDetailImpl = new PurchaseOrderDetailImpl(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de PurchaseOrderDetail.
     *
     * @param purchaseOrderDetail
     */
    public void create(PurchaseOrderDetail purchaseOrderDetail) {
        if (isPurchaseOrderDetailNotStoredInDatabase(purchaseOrderDetail)) {
            purchaseOrderDetailImpl.create(purchaseOrderDetail);
        }
    }

    /**
     * Consulta el listado de purchaseOrderIdentifier de acuerdo al mismo
     * identificador proporcionado.
     *
     * @param purchaseOrderIdentifier
     * @return
     */
    public List<String> getAlById(String purchaseOrderIdentifier) {
        return purchaseOrderDetailImpl.getAllById(purchaseOrderIdentifier);
    }

    /**
     * Realiza el cambio del Status a ASSIGNED correspondiente al
     * purchasOrderIdentifier proporcionado, útil para el manoejo y control de
     * nuevas asignaciones.
     *
     * @param purchaseOrderDetail
     */
    public void update(PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetail.setPoStatus(PurchaseOrderStatus.ASSIGNED.toString());
        purchaseOrderDetailImpl.update(purchaseOrderDetail);
    }

    /**
     * Realiza la consulta de la existencia del identificador de
     * PurchaseOrderDetail en la Base de Datos con el fin de evitar duplicidad
     * antes de ser guardado.
     *
     * @param purchaseOrderDetail
     * @return
     */
    private boolean isPurchaseOrderDetailNotStoredInDatabase(
            PurchaseOrderDetail purchaseOrderDetail) {
        List<String> purchaseOrdersDetailIdentifiers
                = purchaseOrderDetailImpl.getAllById(purchaseOrderDetail.getId());
        return purchaseOrdersDetailIdentifiers.isEmpty();
    }
}
