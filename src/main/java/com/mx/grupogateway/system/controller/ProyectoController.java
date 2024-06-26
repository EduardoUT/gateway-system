/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ProyectoDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Proyecto;
import java.util.List;

/**
 *
 * @author eduar
 */
public class ProyectoController {

    private final ProyectoDAO proyectoDAO;

    public ProyectoController() {
        this.proyectoDAO = new ProyectoDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Realiza el guardado de un nuevo proyecto.
     *
     * @param proyecto
     */
    public void guardar(Proyecto proyecto) {
        this.proyectoDAO.guardar(proyecto);
    }

    /**
     * Lista de objetos de tipo Proyecto.
     *
     * @return
     */
    public List<Proyecto> listar() {
        return this.proyectoDAO.listar();
    }
}
