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
public class ExcelFileChooser {

    private static final String ARCHIVO_DE_TIPO = "Archivos Excel (.xlsx)";
    private static final String EXTENSION_ARCHIVO = "xlsx";
    private String rutaArchivo;
    private int optionValueSelected = 0;
    private final JFileChooser jFileChooser;
    private final FileNameExtensionFilter fileNameExtensionFilter;

    public ExcelFileChooser(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser;
        this.fileNameExtensionFilter = new FileNameExtensionFilter(
                ARCHIVO_DE_TIPO,
                EXTENSION_ARCHIVO
        );
        this.jFileChooser.setFileFilter(fileNameExtensionFilter);
        optionValueSelected = this.jFileChooser
                .showOpenDialog(this.jFileChooser);
        validarRutaArchivo(this.jFileChooser.getSelectedFile());
    }

    /**
     * @return the rutaArchivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * @return the optionValueSelected
     */
    public int getOptionValueSelected() {
        return optionValueSelected;
    }

    private void validarRutaArchivo(File archivo) {
        if (archivo == null) {
            this.rutaArchivo = "";
        } else {
            this.rutaArchivo = archivo.getAbsolutePath();
        }
    }
    
    public boolean isAnyFileSelected() {
        return !rutaArchivo.isEmpty();
    }
}
