/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.EmpleadoDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Empleado;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoController {

    private final EmpleadoDAO empleadoDAO;

    public EmpleadoController() {
        this.empleadoDAO = new EmpleadoDAO(
                new ConnectionFactory().realizarConexion()
        );

    }

    /**
     *
     * @return Lista de tipo Empleado.
     */
    public List<Empleado> listar() {
        return this.empleadoDAO.listar();
    }
}
