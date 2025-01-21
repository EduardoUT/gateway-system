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
        this.projectImpl = new ProjectImpl(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de un nuevo proyecto.
     *
     * @param project
     */
    public void guardar(Project project) {
        this.projectImpl.create(project);
    }

    /**
     * Lista de objetos de tipo Proyecto.
     *
     * @return
     */
    public List<Project> listar() {
        return this.projectImpl.getAll();
    }

    /**
     * Lista los identificadores de proyectos existentes de cuerdo al id
     * ingresado.
     *
     * @param projectId
     * @return Lista de identificadores,
     */
    public List<Long> listarProjectIdentifiers(Long projectId) {
        return this.projectImpl.getAllById(projectId);
    }
}
