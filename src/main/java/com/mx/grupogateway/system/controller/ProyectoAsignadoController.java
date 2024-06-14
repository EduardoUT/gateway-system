/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.ProyectoAsignadoDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.ProyectoAsignado;
import java.util.LinkedList;

/**
 *
 * @author eduar
 */
public class ProyectoAsignadoController {

    private final ProyectoAsignadoDAO proyectoAsignacionDAO;

    public ProyectoAsignadoController() {
        this.proyectoAsignacionDAO = new ProyectoAsignadoDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    public void guardar(LinkedList<ProyectoAsignado> proyectosAsignados) {
        if (proyectosAsignados.size() == 1) {
            proyectoAsignacionDAO.guardar(proyectosAsignados);
        } else {
            proyectoAsignacionDAO.guardarMultiples(proyectosAsignados);
        }
    }

    public LinkedList listar() {
        return this.proyectoAsignacionDAO.listar();
    }

    public int actualizar(String idEmpleado, String poNo,
            String idEmpleadoAsignado) {
        return this.proyectoAsignacionDAO.actualizar(
                idEmpleado, poNo, idEmpleadoAsignado
        );
    }
}
