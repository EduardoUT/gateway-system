/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ProjectDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Project;
import java.util.List;

/**
 *
 * @author eduar
 */
public class ProjectController {

    private final ProjectDAO proyectoDAO;

    public ProjectController() {
        this.proyectoDAO = new ProjectDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de un nuevo proyecto.
     *
     * @param proyecto
     */
    public void guardar(Project proyecto) {
        this.proyectoDAO.guardar(proyecto);
    }

    /**
     * Lista de objetos de tipo Proyecto.
     *
     * @return
     */
    public List<Project> listar() {
        return this.proyectoDAO.listar();
    }
}
