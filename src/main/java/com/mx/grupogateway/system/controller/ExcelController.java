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

    public ExcelController(LinkedList<Proyecto> proyectos, 
            JProgressBar jProgressBar, JLabel jLabel) {
        this.excelSwingWorker = new ExcelDAO(
                new ConnectionFactory().realizarConexion(),
                proyectos, jProgressBar, jLabel
        );
    }

    public void getExecuteSwingWorker() {
        this.excelSwingWorker.execute();
    }
}
