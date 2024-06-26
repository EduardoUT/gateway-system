/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ExcelDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Proyecto;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author eduar
 */
public class ExcelController {

    private final ExcelDAO excelSwingWorker;

    /**
     * Recibe una lista de proyectos, misma que ha sido importada de Excel.
     *
     * @param proyectos Proyectos importados de Excel.
     * @param jProgressBar Barra de progreso que será actualizada através del
     * SwingWorker con la información que está siendo importada a la Base de
     * Datos.
     * @param jLabel Estatus del progreso que será informado a través del
     * SwingWorker.
     */
    public ExcelController(LinkedList<Proyecto> proyectos,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.excelSwingWorker = new ExcelDAO(
                new ConnectionFactory().realizarConexion(),
                proyectos, jProgressBar, jLabel
        );
    }

    /**
     * Invoca la ejecución de un SwingWorker el cuál realiza la importación de
     * la información recopilada de Excel a una BD.
     */
    public void getExecuteSwingWorker() {
        this.excelSwingWorker.execute();
    }
}
