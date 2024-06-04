/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.swing.JFileChooser;
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
public class Excel extends SwingWorker<Void, Integer> {

    private int numRows = 0;
    private final String rutaArchivoExcel;
    private int optionFileChooser = 0;
    private final JProgressBar jProgressBar;
    private final JLabel jLabel;

    private final LinkedList<Proyecto> datosExcel;

    public Excel(String rutaArchivoExcel, int optionFileChooser,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.rutaArchivoExcel = rutaArchivoExcel;
        this.optionFileChooser = optionFileChooser;
        this.jProgressBar = jProgressBar;
        this.jLabel = jLabel;
        this.datosExcel = new LinkedList<>();
    }

    /**
     * @return the proyectos
     */
    public LinkedList<Proyecto> getDatos() {
        return datosExcel == null ? null : datosExcel;
    }

    public boolean isDataProcessed() {
        return (datosExcel.isEmpty());
    }

    /**
     * @return the numRows
     */
    public int getNumRows() {
        return numRows;
    }

    private void processExcel() {
        if (optionFileChooser == JFileChooser.APPROVE_OPTION) {
            File file = new File(rutaArchivoExcel);
            try (XSSFWorkbook xssfw = new XSSFWorkbook(file);) {
                XSSFSheet hoja = xssfw.getSheetAt(0);
                int numLastRow = hoja.getLastRowNum();
                numRows = numLastRow;
                for (int i = 1; i <= numLastRow; i++) {
                    Row row = hoja.getRow(i);
                    datosExcel.add(
                            new Proyecto(
                                    getCellValueLong(row, 0),
                                    getCellValueString(row, 5),
                                    getCellValueString(row, 4),
                                    getCellValueString(row, 3),
                                    getCellValueString(row, 10),
                                    getCellValueString(row, 11),
                                    getCellValueInteger(row, 12),
                                    getCellValueInteger(row, 13),
                                    getCellValueString(row, 14),
                                    getCellValueString(row, 16),
                                    getCellValueLong(row, 17),
                                    getCellValueString(row, 18),
                                    getCellValueString(row, 19),
                                    getCellValueString(row, 20),
                                    getCellValueString(row, 21),
                                    getCellValueBigDecimal(row, 22),
                                    getCellValueBigDecimal(row, 23),
                                    getCellValueString(row, 24),
                                    getCellValueString(row, 27),
                                    getCellValueString(row, 34),
                                    getCellValueString(row, 37),
                                    //Verificar que tipo de valor por defecto envíar.
                                    getCellValueTimestamp(row, 41)
                            )
                    );
                    int progress = i * 100 / numLastRow;
                    publish(progress);
                }
            } catch (InvalidFormatException | IOException e) {
                throw new RuntimeException("Error al leer archivo: ", e);
            }
        }
    }

    private String getCellValueString(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.ofNullable(row.getCell(numCell))
                .map(Object::toString)
                .orElse("");
        return optionalToString;
    }

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

    private Timestamp getCellValueTimestamp(Row row, int numCell) {
        String optionalToString;
        optionalToString = Optional.ofNullable(row.getCell(numCell).getStringCellValue())
                .map(Object::toString)
                .orElse("NULL");
        return Timestamp.valueOf(optionalToString);
    }

    @Override
    protected Void doInBackground() throws Exception {
        processExcel();
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int latestProgress = chunks.get(chunks.size() - 1);
        jProgressBar.setValue(latestProgress);
        jLabel.setText("Procesando datos de la hoja de Excel.");
    }

    @Override
    protected void done() {
        jProgressBar.setValue(0);
        jLabel.setText("Procesamiento de datos finalizado.");
    }

}
