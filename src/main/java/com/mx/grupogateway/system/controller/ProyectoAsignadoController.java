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

    /**
     * Guarda una lista enlazada de tipo ProyectoAsignado, evalúa di esta
     * contiene uno o más asignaciones.
     *
     * @param proyectosAsignados
     */
    public void guardar(LinkedList<ProyectoAsignado> proyectosAsignados) {
        if (proyectosAsignados.size() == 1) {
            proyectoAsignacionDAO.guardar(proyectosAsignados);
        } else {
            proyectoAsignacionDAO.guardarMultiples(proyectosAsignados);
        }
    }

    /**
     * Lista de asignaciones, con el empleado asociado al proyecto.
     *
     * @return
     */
    public LinkedList<ProyectoAsignado> listar() {
        return this.proyectoAsignacionDAO.listar();
    }

    /**
     * Actualiza la asignación de un proyectoa otro empleado.
     *
     * @param idEmpleado
     * @param poNo
     * @param idEmpleadoAsignado
     * @return
     */
    public int actualizar(String idEmpleado, String poNo,
            String idEmpleadoAsignado) {
        return this.proyectoAsignacionDAO.actualizar(
                idEmpleado, poNo, idEmpleadoAsignado
        );
    }
}
