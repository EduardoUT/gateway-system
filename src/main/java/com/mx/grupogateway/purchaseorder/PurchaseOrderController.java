/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder;

import com.mx.grupogateway.crud.DataModelForJTable;
import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.site.Site;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eduar
 */
public class PurchaseOrderController implements DataModelForJTable {

    private final PurchaseOrderImpl purchaseOrderImpl;

    public PurchaseOrderController() {
        purchaseOrderImpl = new PurchaseOrderImpl(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de PurchaseOrder.
     *
     * @param purchaseOrder
     */
    public void create(PurchaseOrder purchaseOrder) {
        if (isPurchaseOrderNotStoredInDatabase(purchaseOrder)) {
            purchaseOrderImpl.create(purchaseOrder);
        }
    }

    /**
     * Recibe un List de tipo PurchaseOrder para construir el modelo de filas de
     * un JTable.
     *
     * @return Lista de Object[] con los datos del PurchaseOrder a mostrar en el
     * JTable.
     */
    @Override
    public List<Object[]> getDataModelForJTable() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderImpl.getAll();
        List<Object[]> dataModelPurchaseOrder = new ArrayList<>();
        if (!purchaseOrders.isEmpty()) {
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                PurchaseOrderDetail purchaseOrderDetail = purchaseOrder.getPurchaseOrderDetail();
                Project project = purchaseOrder.getProject();
                Site site = project.getSite();
                dataModelPurchaseOrder.add(
                        new Object[]{
                            project.getId(),
                            project.getProjectCode(),
                            project.getProjectName(),
                            project.getCustomer(),
                            purchaseOrderDetail.getPoStatus(),
                            purchaseOrderDetail.getId(),
                            site.getShipmentNo(),
                            purchaseOrder.getPoLineNo(),
                            site.getSiteCode(),
                            site.getSiteName(),
                            purchaseOrderDetail.getItemCode(),
                            purchaseOrderDetail.getItemDesc(),
                            purchaseOrderDetail.getRequestedQty(),
                            purchaseOrder.getDueQty(),
                            purchaseOrder.getBilledQty(),
                            purchaseOrder.getUnitPrice(),
                            purchaseOrderDetail.getLineAmount(),
                            purchaseOrder.getUnit(),
                            purchaseOrderDetail.getPaymentTerms(),
                            project.getCategory(),
                            site.getBiddigArea(),
                            project.getPublishDate()
                        }
                );
            }
            return dataModelPurchaseOrder;
        } else {
            return Collections.emptyList();
        }
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
        return this.purchaseOrderImpl.getAllPurchaseOrderIdentifiers(
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
        return this.purchaseOrderImpl.getAllById(
                purchaseOrder.getPurchaseOrderDetail().getId()
        );
    }

    /**
     * Realiza la consulta de la existencia del identificador de PurchaseOrder
     * en la Base de Datos con el fin de evitar duplicidad antes de ser
     * guardado.
     *
     * @param purchaseOrder
     * @return
     */
    private boolean isPurchaseOrderNotStoredInDatabase(PurchaseOrder purchaseOrder) {
        Map<Long, String> purchaseOrderIdentifiers = purchaseOrderImpl
                .getAllPurchaseOrderIdentifiers(
                        purchaseOrder.getPurchaseOrderDetail().getId(),
                        purchaseOrder.getProject().getId()
                );
        return purchaseOrderIdentifiers.isEmpty();
    }
}
