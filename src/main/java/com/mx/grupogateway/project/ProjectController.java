/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.project;

import com.mx.grupogateway.project.ProjectDAO;
import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.project.Project;
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

    /**
     * Lista los identificadores de proyectos existentes de cuerdo al id
     * ingresado.
     *
     * @param projectId
     * @return Lista de identificadores,
     */
    public List<Long> listarProjectIdentifiers(Long projectId) {
        return this.proyectoDAO.listarProjectId(projectId);
    }
}
