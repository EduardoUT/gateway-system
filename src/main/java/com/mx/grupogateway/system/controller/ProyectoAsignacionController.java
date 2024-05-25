/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ProyectoAsignacionDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.ProyectoAsignacion;

/**
 *
 * @author eduar
 */
public class ProyectoAsignacionController {

    private final ProyectoAsignacionDAO proyectoAsignacionDAO;

    public ProyectoAsignacionController() {
        this.proyectoAsignacionDAO = new ProyectoAsignacionDAO(
                new ConnectionFactory().realizarConexion()
        );
    }
    
    public void guardar(ProyectoAsignacion proyectoAsignacion) {
        proyectoAsignacionDAO.guardar(proyectoAsignacion);
    }
}
