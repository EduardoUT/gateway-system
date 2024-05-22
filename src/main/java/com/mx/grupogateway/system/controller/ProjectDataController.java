/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ProjectDataDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;

/**
 *
 * @author eduar
 */
public class ProjectDataController {

    private final ProjectDataDAO projectDataDAO;

    public ProjectDataController() {
        this.projectDataDAO = new ProjectDataDAO(
                new ConnectionFactory().realizarConexion()
        );
    }
}
