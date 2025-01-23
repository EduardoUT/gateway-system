/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.project;

import com.mx.grupogateway.factory.ConnectionFactory;
import java.util.List;

/**
 *
 * @author eduar
 */
public class ProjectController {

    private final ProjectImpl projectImpl;

    public ProjectController() {
        projectImpl = new ProjectImpl(new ConnectionFactory().realizarConexion());
    }

    /**
     * Realiza el guardado de un nuevo proyecto.
     *
     * @param project
     */
    public void create(Project project) {
        if (isProjectNotStoredInDatabase(project)) {
            projectImpl.create(project);
        }
    }

    /**
     * Lista de objetos de tipo Project.
     *
     * @return
     */
    public List<Project> getAll() {
        return projectImpl.getAll();
    }

    /**
     * Lista los identificadores de proyectos existentes de cuerdo al id
     * ingresado.
     *
     * @param projectId
     * @return Lista de identificadores,
     */
    public List<Long> getAllById(Long projectId) {
        return projectImpl.getAllById(projectId);
    }

    /**
     * Realiza la consulta de la existencia del identificador de Project en la
     * Base de Datos con el fin de evitar duplicidad antes de ser guardado.
     *
     * @param project
     * @return
     */
    private boolean isProjectNotStoredInDatabase(Project project) {
        List<Long> projectIdentifiers = projectImpl.getAllById(project.getId());
        return projectIdentifiers.isEmpty();
    }
}
