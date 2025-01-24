/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana de explorador de archivos Excel.
 *
 * @author eduar
 */
public class ExcelFileChooser extends JFileChooser {

    private static final String ARCHIVO_DE_TIPO = "Archivos Excel (.xlsx)";
    private static final String EXTENSION_ARCHIVO = "xlsx";
    private String filePath;
    private int optionValueSelected = 0;

    public ExcelFileChooser() {
        setFileFilter(new FileNameExtensionFilter(
                ARCHIVO_DE_TIPO,
                EXTENSION_ARCHIVO
        ));
        optionValueSelected = showOpenDialog(null);
        validarRutaArchivo(getSelectedFile());
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @return the optionValueSelected
     */
    public int getOptionValueSelected() {
        return optionValueSelected;
    }

    private void validarRutaArchivo(File archivo) {
        if (archivo == null) {
            filePath = "";
        } else {
            filePath = archivo.getAbsolutePath();
        }
    }

    public boolean isAnyFileSelected() {
        return !filePath.isEmpty();
    }
}
