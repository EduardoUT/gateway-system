/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ExcelDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Proyecto;
import java.util.LinkedList;
import javax.swing.JProgressBar;

/**
 *
 * @author eduar
 */
public class ExcelController {

    private final ExcelDAO excelSwingWorker;

    public ExcelController(JProgressBar jProgressBar,
            LinkedList<Proyecto> proyectos) {
        this.excelSwingWorker = new ExcelDAO(
                new ConnectionFactory().realizarConexion(),
                jProgressBar,
                proyectos
        );
    }

    public void getExecuteSwingWorker() {
        this.excelSwingWorker.execute();
    }
}
