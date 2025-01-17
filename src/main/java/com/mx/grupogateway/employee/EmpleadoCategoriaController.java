/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import com.mx.grupogateway.factory.ConnectionFactory;
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

    /**
     * Guarda objeto de tipo EmpleadoCategoria.
     *
     * @param empleadoCategoria
     */
    public void guardar(EmpleadoCategoria empleadoCategoria) {
        this.empleadoCategoriaDAO.guardar(empleadoCategoria);
    }

    /**
     *
     * @return List de tipo EmpleadoCategoria.
     */
    public List<EmpleadoCategoria> listar() {
        return this.empleadoCategoriaDAO.listar();
    }

    /**
     * Actualiza el nombre de una categoría de empleado acorde al id.
     *
     * @param idCategoria
     * @param nombreCategoria
     * @return
     */
    public int actualizar(String idCategoria, String nombreCategoria) {
        return this.empleadoCategoriaDAO.actualizar(idCategoria, nombreCategoria);
    }

    /**
     * Elimina una categoría de empleado.
     *
     * @param idCategoria
     * @return
     */
    public int eliminar(String idCategoria) {
        return this.empleadoCategoriaDAO.eliminar(idCategoria);
    }
}
