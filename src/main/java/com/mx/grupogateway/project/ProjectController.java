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

    private final ProjectDAO projectDAO;

    public ProjectController() {
        this.projectDAO = new ProjectDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de un nuevo proyecto.
     *
     * @param project
     */
    public void guardar(Project project) {
        this.projectDAO.guardar(project);
    }

    /**
     * Lista de objetos de tipo Proyecto.
     *
     * @return
     */
    public List<Project> listar() {
        return this.projectDAO.listar();
    }

    /**
     * Lista los identificadores de proyectos existentes de cuerdo al id
     * ingresado.
     *
     * @param projectId
     * @return Lista de identificadores,
     */
    public List<Long> listarProjectIdentifiers(Long projectId) {
        return this.projectDAO.listarProjectId(projectId);
    }
}
