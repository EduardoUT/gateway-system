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
    
    public void guardar(Proyecto proyecto) {
        this.proyectoDAO.guardar(proyecto);
    }
    
    public List<Proyecto> listar() {
        return this.proyectoDAO.listar();
    }
}
