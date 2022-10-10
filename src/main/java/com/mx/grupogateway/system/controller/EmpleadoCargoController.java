/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.EmpleadoCargoDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.EmpleadoCargo;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoCargoController {

    private EmpleadoCargoDAO empleadoCargoDAO;

    public EmpleadoCargoController() {
        this.empleadoCargoDAO = new EmpleadoCargoDAO(
                new ConnectionFactory().realizarConexion()
        );
    }
    
    /**
     * 
     * @return List de tipo EmpleadoCargo. 
     */
    public List<EmpleadoCargo> listar() {
        return this.empleadoCargoDAO.listar();
    }
}
