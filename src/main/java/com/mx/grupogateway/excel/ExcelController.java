/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.excel;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author eduar
 */
public class ExcelController {

    private final ExcelDataCellImportSwingWorker excelDataCellImportSwingWorker;

    public ExcelController(String excelFilePath, JProgressBar jProgressBar, JLabel jLabel) {
        excelDataCellImportSwingWorker = new ExcelDataCellImportSwingWorker(
                excelFilePath,
                jProgressBar,
                jLabel
        );
    }

    /**
     * Realiza la ejecución en segundo plano de los SwingWorkers encargados de
     * la importación y exportación de los datos del archivo Excel a la Base de
     * Datos.
     */
    public void executeExcelSwingWorkers() {
        excelDataCellImportSwingWorker.execute();
    }
}
