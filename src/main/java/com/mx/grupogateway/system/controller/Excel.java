/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.modelo.Project;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private final LinkedList<Project> datosExcel;

    /**
     * Constructor para crear un proceso en segundo plano con SwingWorker.
     *
     * @param rutaArchivoExcel Ruta del archivo Excel a importar.
     * @param optionFileChooser Opción seleccionada por el usuario a través de
     * en un JFileChooser.
     * @param jProgressBar Barra de progreso donde se actualizará acorde a la
     * importación de los datos.
     * @param jLabel Etiqueta donde se informa el status de la importación.
     */
    public Excel(String rutaArchivoExcel, int optionFileChooser,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.rutaArchivoExcel = rutaArchivoExcel;
        this.optionFileChooser = optionFileChooser;
        this.jProgressBar = jProgressBar;
        this.jLabel = jLabel;
        this.datosExcel = new LinkedList<>();
    }

    /**
     * @return Devuelve el estado de la lista, en caso de ser una lista vacía o
     * al no ser proporcionada una ruta.
     */
    public LinkedList<Project> getDatos() {
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

    /**
     * Proceso encargado de procesar e importar la información de un archivo
     * Excel a una lista enlazada de tipo Proyecto.
     */
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
                            new Project(
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
     * Ejecuta el proceso de importación de datos de un archivo Excel.
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Void doInBackground() throws Exception {
        processExcel();
        return null;
    }

    /**
     * Actualiza la barra de progreso acorde a la información que está siendo
     * importada y informa el estatus del progreso en la etiqueta JLabel.
     *
     * @param chunks
     */
    @Override
    protected void process(List<Integer> chunks) {
        int latestProgress = chunks.get(chunks.size() - 1);
        jProgressBar.setValue(latestProgress);
        jLabel.setText("Procesando datos de la hoja de Excel.");
    }

    /**
     * Reestablece la barra de progreso e informa la finalización en la etiqueta
     * JLabel.
     */
    @Override
    protected void done() {
        jProgressBar.setValue(0);
        jLabel.setText("Procesamiento de datos finalizado.");
    }
}
