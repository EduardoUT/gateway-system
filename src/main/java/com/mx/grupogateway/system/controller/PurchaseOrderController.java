/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.PurchaseOrderDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import com.mx.grupogateway.system.modelo.Site;
import java.util.ArrayList;
import java.util.Collections;
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
     * Lista el modelo PurchaseOrder almacenados en la Base de Datos, el cual
     * contiene el modelo completo de datos importados de Excel.
     *
     * @return
     */
    public List<Object[]> listar() {
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderDAO.listar();
        List<Object[]> dataModelPurchaseOrder = new ArrayList<>();
        if (!purchaseOrders.isEmpty()) {
            for (PurchaseOrder purchaseOrder : purchaseOrders) {
                PurchaseOrderDetail purchaseOrderDetail = purchaseOrder.getPurchaseOrderDetail();
                Project project = purchaseOrder.getProject();
                Site site = project.getSite();
                dataModelPurchaseOrder.add(
                        new Object[]{
                            project.getProjectId(),
                            project.getProjectCode(),
                            project.getProjectName(),
                            project.getCustomer(),
                            purchaseOrderDetail.getPoStatus(),
                            purchaseOrderDetail.getPurchaseOrderDetailIdentifier(),
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
