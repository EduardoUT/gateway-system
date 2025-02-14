/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.excel;

import static com.mx.grupogateway.GlobalLogger.*;
import com.mx.grupogateway.project.ProjectController;
import com.mx.grupogateway.purchaseorder.PurchaseOrderController;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetailController;
import com.mx.grupogateway.site.SiteController;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.site.Site;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Clase de exportación de datos obtenidos del ExcelDataCell, el proceso se
 * ejecuta en segundo plano y mantiene actualizado el progeso por medio de un
 * jProgressBar y un jLabel.
 *
 * @author eduar
 */
class ExcelDataCellExportSwingWorker extends SwingWorker<Void, Integer> {

    private final JProgressBar jProgressBar;
    private final JLabel jLabel;
    private final ExcelDataCell excelDataCell;
    private final ProjectController projectController;
    private final SiteController siteController;
    private final PurchaseOrderController purchaseOrderController;
    private final PurchaseOrderDetailController purchaseOrderDetailController;

    public ExcelDataCellExportSwingWorker(ExcelDataCell excelDataCell,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.excelDataCell = excelDataCell;
        this.jProgressBar = jProgressBar;
        this.jLabel = jLabel;
        projectController = new ProjectController();
        siteController = new SiteController();
        purchaseOrderDetailController = new PurchaseOrderDetailController();
        purchaseOrderController = new PurchaseOrderController();
    }

    /**
     * Se procesa la información del ExcelDataCell acorde al respectivo modelo,
     * para cada inserción se valida que el registro no haya sido almacenado ya
     * en la BD, se actualiza el progreso en segundo plano en el jProgressBar en
     * Project por cada modelo procesado de su respectivo listado de tipo
     * HashSet.
     */
    private void exportDataCellExcel() {
        int progressCounter = 0;
        int totalData = excelDataCell.totalDataCell();
        for (Site site : excelDataCell.getSites()) {
            siteController.create(site);
            progressCounter++;
            int progress = progressCounter * 100 / totalData;
            publish(progress);
        }

        for (Project project : excelDataCell.getProjects()) {
            projectController.create(project);
            progressCounter++;
            int progress = progressCounter * 100 / totalData;
            publish(progress);
        }

        for (PurchaseOrderDetail purchaseOrderDetail : excelDataCell.getPurchaseOrderDetails()) {
            purchaseOrderDetailController.create(purchaseOrderDetail);
            progressCounter++;
            int progress = progressCounter * 100 / totalData;
            publish(progress);
        }

        for (PurchaseOrder purchaseOrder : excelDataCell.getPurchaseOrders()) {
            purchaseOrderController.create(purchaseOrder);
            progressCounter++;
            int progress = progressCounter * 100 / totalData;
            publish(progress);
        }
    }

    /**
     * Ejecuta en segundo plano la exportación de los datos a la BD.
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Void doInBackground() {
        try {
            exportDataCellExcel();
        } catch (Exception e) {
            registerLoggerSevere("{0}Error en la exportaci\u00f3n de los datos "
                    + "de la hoja de Excel: ", e
            );
        }
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
     * Informa en la etiqueta la finalización del proceso de exportación.
     */
    @Override
    protected void done() {
        jLabel.setText("Exportación de datos finalizada.");
    }
}
