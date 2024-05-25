/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.EmpleadoCategoriaDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.EmpleadoCategoria;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoCategoriaController {

    private final EmpleadoCategoriaDAO empleadoCategoriaDAO;

    public EmpleadoCategoriaController() {
        this.empleadoCategoriaDAO = new EmpleadoCategoriaDAO(
                new ConnectionFactory().realizarConexion()
        );
    }
    
    public void guardar(EmpleadoCategoria empleadoCategoria) {
        this.empleadoCategoriaDAO.guardar(empleadoCategoria);
    }
    
    /**
     * 
     * @return List de tipo EmpleadoCargo. 
     */
    public List<EmpleadoCategoria> listar() {
        return this.empleadoCategoriaDAO.listar();
    }
    
    public int modificar(String idCategoria, String nombreCategoria) {
        return this.empleadoCategoriaDAO.modificar(idCategoria, nombreCategoria);
    }
    
    public int eliminar(String idCategoria) {
        return this.empleadoCategoriaDAO.eliminar(idCategoria);
    }
}
