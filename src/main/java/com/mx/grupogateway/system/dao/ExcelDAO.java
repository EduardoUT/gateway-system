/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.controller.ProjectController;
import com.mx.grupogateway.system.controller.PurchaseOrderController;
import com.mx.grupogateway.system.controller.PurchaseOrderDetailController;
import com.mx.grupogateway.system.controller.SiteController;
import com.mx.grupogateway.system.modelo.DataImport;
import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import com.mx.grupogateway.system.modelo.Site;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Clase de exportación de datos obtenidos del DataModel, el proceso se ejecuta
 * en segundo plano y mantiene actualizado el progeso por medio de un
 * jProgressBar y un jLabel.
 *
 * @author eduar
 */
public class ExcelDAO extends SwingWorker<Void, Integer> {

    private final JProgressBar jProgressBar;
    private final JLabel jLabel;
    private final DataImport dataImport;
    private final ProjectController projectController;
    private final SiteController siteController;
    private final PurchaseOrderController purchaseOrderController;
    private final PurchaseOrderDetailController purchaseOrderDetailController;

    public ExcelDAO(DataImport dataImport,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.dataImport = dataImport;
        this.jProgressBar = jProgressBar;
        this.jLabel = jLabel;
        this.projectController = new ProjectController();
        this.siteController = new SiteController();
        this.purchaseOrderDetailController = new PurchaseOrderDetailController();
        this.purchaseOrderController = new PurchaseOrderController();
    }

    /**
     * Realiza la consulta del identificador de projecto en la Base de Datos a
     * fin de evitar duplicidad.
     *
     * @param project
     * @return
     */
    private boolean isProjectNotStoredInDatabase(Project project) {
        List<Long> projectIdentifiers = projectController
                .listarProjectIdentifiers(project.getProjectId());
        return projectIdentifiers.isEmpty();
    }

    /**
     * Realiza la consulta del identificador de site en la Base de Datos a fin
     * de evitar duplicidad.
     *
     * @param site
     * @return
     */
    private boolean isSiteNotStoredInDatabase(Site site) {
        List<Long> siteIdentifiers = siteController
                .listarSiteIdentifiers(site.getSiteId());
        return siteIdentifiers.isEmpty();
    }

    /**
     * Realiza la consulta del identificador de orden de compra en la Base de
     * Datos a fin de evitar duplicidad.
     *
     * @param purchaseOrderDetail
     * @return
     */
    private boolean isPurchaseOrderDetailNotStoredInDatabase(
            PurchaseOrderDetail purchaseOrderDetail) {
        List<String> purchaseOrdersDetailIdentifiers
                = purchaseOrderDetailController
                        .listarPurchaseOrderDetailIdentifiers(
                                purchaseOrderDetail.getPurchaseOrderIdentifier()
                        );
        return purchaseOrdersDetailIdentifiers.isEmpty();
    }

    /**
     * Realiza la consulta de los identificadores de proyecto y orden de compra
     * asociados que conforman una clave compuesta en la Base de Datos a fin de
     * evitar duplicidad.
     *
     * @param purchaseOrder
     * @return
     */
    private boolean isPurchaseOrderNotStoredInDatabase(PurchaseOrder purchaseOrder) {
        Map<Long, String> purchaseOrderIdentifiers = purchaseOrderController
                .listarPurchaseOrderIdentifiers(
                        purchaseOrder.getPurchaseOrderDetail().getPurchaseOrderIdentifier(),
                        purchaseOrder.getProject().getProjectId()
                );
        return purchaseOrderIdentifiers.isEmpty();
    }

    /**
     * Se procesa la información obtenida del DataModel acorde al respectivo
     * modelo, para cada insrción se valida que el registro no haya sido
     * almacenado, se actualiza el progreso en segundo plano en el jProgressBar
     * en Project.
     */
    private void exportData() {

        int progressCounter = 0;
        int projectsSize = dataImport.getProjects().size();

        /*
        int siteSize = dataImport.getSites().size();
        int purchasOrderDetailsSize = dataImport.getPurchaseOrderDetails().size();
        int purchaseOrderSize = dataImport.getPurchaseOrders().size();
         */
        for (Site site : dataImport.getSites()) {
            if (isSiteNotStoredInDatabase(site)) {
                siteController.guardar(site);
            }
        }

        for (Project project : dataImport.getProjects()) {
            if (isProjectNotStoredInDatabase(project)) {
                projectController.guardar(project);
            }
            progressCounter++;
            int progress = progressCounter * 100 / projectsSize;
            publish(progress);
        }

        for (PurchaseOrderDetail purchaseOrderDetail : dataImport.getPurchaseOrderDetails()) {
            if (isPurchaseOrderDetailNotStoredInDatabase(purchaseOrderDetail)) {
                purchaseOrderDetailController.guardar(purchaseOrderDetail);
            }
        }

        for (PurchaseOrder purchaseOrder : dataImport.getPurchaseOrders()) {
            if (isPurchaseOrderNotStoredInDatabase(purchaseOrder)) {
                purchaseOrderController.guardar(purchaseOrder);
            }
        }

    }

    @Override
    protected Void doInBackground() throws Exception {
        exportData();
        return null;
    }

    /**
     * Realiza la actualización del hilo, se informa en la barra de progreso y
     * en la etiqueta JLabel se informa el status de importación.
     *
     * @param chunks
     */
    @Override
    protected void process(List<Integer> chunks) {
        int latestProgress = chunks.get(chunks.size() - 1);
        jProgressBar.setValue(latestProgress);
        jLabel.setText("Importando a la Base da Datos.");
    }

    /**
     * Informa en la etiqueta la finalización del proceso de importación.
     */
    @Override
    protected void done() {
        jLabel.setText("Importación de datos finalizada.");
    }
}
