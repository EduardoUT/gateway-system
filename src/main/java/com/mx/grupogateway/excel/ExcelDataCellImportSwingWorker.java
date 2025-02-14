/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.excel;

import static com.mx.grupogateway.GlobalLogger.*;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.site.Site;
import static com.mx.grupogateway.util.ExcelDataCellFormatter.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Permite ejecutat en segundo plano el proceso de importación de los datos del
 * archivo de Excel.
 *
 * @author eduar
 */
class ExcelDataCellImportSwingWorker extends SwingWorker<Void, Integer> {

    private final String excelFilePath;
    private final ExcelDataCell excelDataCell;
    private final JProgressBar jProgressBar;
    private final JLabel jLabel;
    private final ExcelDataCellExportSwingWorker excelDataCellExportSwingWorker;

    public ExcelDataCellImportSwingWorker(String excelFilePath,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.excelFilePath = excelFilePath;
        excelDataCell = new ExcelDataCell();
        this.jProgressBar = jProgressBar;
        this.jProgressBar.setStringPainted(true);
        this.jLabel = jLabel;
        excelDataCellExportSwingWorker = new ExcelDataCellExportSwingWorker(
                excelDataCell,
                jProgressBar,
                jLabel
        );
    }

    /**
     * Realiza la importación y distribución de los datos de la hoja de Excel
     * acorde a las clases modelo, mismas que son contenidas en una clase única
     * con un listado de dipo HashSet acorde al tipo de Objeto, esta clase se
     * llama ExcelDataCell.
     *
     * @see ExcelDataCell
     */
    private void importDataCellExcel() {
        File file = new File(excelFilePath);
        try (XSSFWorkbook xssfw = new XSSFWorkbook(file)) {
            XSSFSheet sheet = xssfw.getSheetAt(0);
            int numLastRow = sheet.getLastRowNum();
            for (int i = 1; i <= numLastRow; i++) {
                Row row = sheet.getRow(i);

                Site site = new Site();
                site.setId(getCellValueLong(row, 6));
                site.setSiteCode(getCellValueString(row, 14));
                site.setSiteName(getCellValueString(row, 16));
                site.setBiddigArea(getCellValueString(row, 37));
                site.setShipmentNo(getCellValueInteger(row, 13));

                Project project = new Project();
                project.setId(getCellValueLong(row, 0));
                project.setSite(site);
                project.setProjectCode(getCellValueString(row, 5));
                project.setProjectName(getCellValueString(row, 4));
                project.setCustomer(getCellValueString(row, 3));
                project.setCategory(getCellValueString(row, 34));
                project.setPublishDate(getCellValueTimestamp(row, 41));

                PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                purchaseOrderDetail.setId(getCellValueString(row, 11));
                purchaseOrderDetail.setPoStatus(getCellValueString(row, 10));
                purchaseOrderDetail.setItemCode(getCellValueLong(row, 17));
                purchaseOrderDetail.setItemDesc(getCellValueString(row, 18));
                purchaseOrderDetail.setRequestedQty(getCellValueBigDecimal(row, 19));
                purchaseOrderDetail.setLineAmount(getCellValueBigDecimal(row, 23));
                purchaseOrderDetail.setPaymentTerms(getCellValueString(row, 27));

                PurchaseOrder purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                        .withPurchaseOrderDetail(purchaseOrderDetail)
                        .withProject(project)
                        .withPoLineNo(getCellValueInteger(row, 12))
                        .withDueQty(getCellValueBigDecimal(row, 20))
                        .withBilledQty(getCellValueBigDecimal(row, 21))
                        .withUnit(getCellValueString(row, 24))
                        .withUnitPrice(getCellValueBigDecimal(row, 22))
                        .build();
                excelDataCell.addSite(site);
                excelDataCell.addProject(project);
                excelDataCell.addPurchaseOrderDetail(purchaseOrderDetail);
                excelDataCell.addPurchaseOrders(purchaseOrder);
                int progress = i * 100 / numLastRow;
                publish(progress);
            }
        } catch (InvalidFormatException e) {
            registerLoggerSevere("Error al procesar formato de excel: {0}", e);
        } catch (IOException e) {
            registerLoggerSevere("Error de IO: {0}", e);
        } catch (RuntimeException e) {
            registerLoggerSevere("Error: {0}", e);
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            importDataCellExcel();
        } catch (Exception e) {
            registerLoggerSevere("Error en el proceso de importación de los "
                    + "datos de la hoja de Excel: {0}", e);
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int latestProgress = chunks.get(chunks.size() - 1);
        jProgressBar.setValue(latestProgress);
        jLabel.setText("Importando datos de Excel.");
    }

    @Override
    protected void done() {
        jProgressBar.setValue(0);
        jLabel.setText("Importación de datos finalizada.");
        excelDataCellExportSwingWorker.execute();
    }
}
