/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.LoggerConfig;
import com.mx.grupogateway.system.dao.ExcelDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.DataImport;
import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import com.mx.grupogateway.system.modelo.Site;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author eduar
 */
public class ExcelController extends SwingWorker<Void, Integer> {

    private static final Logger logger = LoggerConfig.getLogger();
    private int numRows;
    private final ExcelDAO excelDAO;
    private final String excelFilePath;
    private final DataImport dataImport;
    private final JProgressBar jProgressBar;
    private final JLabel jLabel;

    public ExcelController(String excelFilePath,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.excelFilePath = excelFilePath;
        this.dataImport = new DataImport();
        this.jProgressBar = jProgressBar;
        this.jProgressBar.setStringPainted(true);
        this.jLabel = jLabel;
        this.excelDAO = new ExcelDAO(
                dataImport, jProgressBar, jLabel
        );
    }

    /**
     * Realiza la importación y distribución de los datos de la hoja de Excel
     * acorde a las clases modelo, mismas que son contenidas en una clase única
     * con un listado acorde al tipo de Objeto, esta clase se llama dataImport.
     *
     * @see DataImport
     */
    private void importData() {
        File file = new File(excelFilePath);
        try (XSSFWorkbook xssfw = new XSSFWorkbook(file)) {
            XSSFSheet sheet = xssfw.getSheetAt(0);
            int numLastRow = sheet.getLastRowNum();
            numRows = numLastRow;
            for (int i = 1; i <= numLastRow; i++) {
                Row row = sheet.getRow(i);

                Site site = new Site();
                site.setSiteId(getCellValueLong(row, 6));
                site.setSiteCode(getCellValueString(row, 14));
                site.setSiteName(getCellValueString(row, 16));
                site.setBiddigArea(getCellValueString(row, 37));
                site.setShipmentNo(getCellValueInteger(row, 13));

                Project project = new Project();
                project.setProjectId(getCellValueLong(row, 0));
                project.setSite(site);
                project.setProjectCode(getCellValueString(row, 5));
                project.setProjectName(getCellValueString(row, 4));
                project.setCustomer(getCellValueString(row, 3));
                project.setCategory(getCellValueString(row, 34));
                project.setPublishDate(getCellValueTimestamp(row, 41));

                PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                purchaseOrderDetail.setPurchaseOrderIdentifier(getCellValueString(row, 11));
                purchaseOrderDetail.setPoStatus(getCellValueString(row, 10));
                purchaseOrderDetail.setItemCode(getCellValueLong(row, 17));
                purchaseOrderDetail.setItemDesc(getCellValueString(row, 18));
                purchaseOrderDetail.setRequestedQty(getCellValueString(row, 19));
                purchaseOrderDetail.setLineAmount(getCellValueBigDecimal(row, 23));
                purchaseOrderDetail.setPaymentTerms(getCellValueString(row, 27));

                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setPurchaseOrderDetail(purchaseOrderDetail);
                purchaseOrder.setProject(project);
                purchaseOrder.setPoLineNo(getCellValueInteger(row, 12));
                purchaseOrder.setDueQty(getCellValueString(row, 20));
                purchaseOrder.setBilledQty(getCellValueBigDecimal(row, 21));
                purchaseOrder.setUnit(getCellValueString(row, 24));
                purchaseOrder.setUnitPrice(getCellValueBigDecimal(row, 22));

                getDataImport().addSite(site);
                getDataImport().addProject(project);
                getDataImport().addPurchaseOrderDetail(purchaseOrderDetail);
                getDataImport().addPurchaseOrders(purchaseOrder);
                int progress = i * 100 / numLastRow;
                publish(progress);
            }

        } catch (InvalidFormatException e) {
            logger.log(Level.SEVERE, "Error al procesar formato de excel: {0}", e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error de IO: {0}", e.getMessage());
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Error: {0}", e.getMessage());
        }
    }

    /**
     * Invoca el SwingWorker encargado de la exportación de la información
     * almacenada en los listados del DataModel a la Base de Datos.
     *
     * @see ExcelDAO
     */
    public void exportData() {
        if (isDone()) {
            this.excelDAO.execute();
        } else {
            logger.log(Level.SEVERE, "El proceso de importación del archivo de "
                    + "Excel no se completó exitosamente.");
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        importData();
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
    }

    /**
     * Obtiene el objeto Row y el número de celda ideal para celdas con dato de
     * tipo texto.
     *
     * @param row
     * @param numCell
     * @return La representación del dato obtenido en un String.
     */
    private String getCellValueString(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.ofNullable(row.getCell(numCell))
                .map(Object::toString)
                .orElse("");
        return optionalToString;
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para celdas con datos
     * numéicos enteros.
     *
     * @param row
     * @param numCell
     * @return La reprecentación del dato obtenido en un Integer.
     */
    private Integer getCellValueInteger(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.ofNullable(row.getCell(numCell))
                .map(Object::toString)
                .orElse("0");
        if (optionalToString.equals("")) {
            optionalToString = "0";
        }
        Long num = Math.round(Double.parseDouble(optionalToString));
        return num.intValue();
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para campos con números
     * grandes.
     *
     * @param row
     * @param numCell
     * @return La representación del valor obtenido en un Long.
     */
    private Long getCellValueLong(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.ofNullable(row.getCell(numCell))
                .map(Object::toString)
                .orElse("0");
        if (optionalToString.equals("")) {
            optionalToString = "0";
        }
        return BigDecimal.valueOf(Double.parseDouble(optionalToString))
                .longValue();
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para datos de tipo
     * decimal.
     *
     * @param row
     * @param numCell
     * @return La representación del dato obrenido en BigDecimal.
     */
    private BigDecimal getCellValueBigDecimal(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.ofNullable(row.getCell(numCell))
                .map(Object::toString)
                .orElse("0");
        if (optionalToString.equals("")) {
            optionalToString = "0";
        }
        return new BigDecimal(optionalToString);
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para celdas con fechas
     * en estandar Timestamp.
     *
     * @param row
     * @param numCell
     * @return La representación de una fecha en un LocalDateTime.
     */
    private LocalDateTime getCellValueTimestamp(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.
                ofNullable(row.getCell(numCell).getStringCellValue())
                .map(Object::toString)
                .orElse("NULL");
        return Timestamp.valueOf(optionalToString).toLocalDateTime();
    }

    /**
     * @return the numRows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * @return the dataImport
     */
    public DataImport getDataImport() {
        return dataImport;
    }
}
