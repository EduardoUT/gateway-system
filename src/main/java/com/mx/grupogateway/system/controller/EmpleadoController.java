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
     * Recibe los valores registrados en la tablaEmpleado a ser modificados.
     *
     * @param empleadoId
     * @param nombre
     * @param apellidoP
     * @param apellidoM
     * @param cargoId
     * @return 
     */
    public int modificar(Integer empleadoId, String nombre,
            String apellidoP, String apellidoM, Integer cargoId) {
        return this.empleadoDAO.modificar(empleadoId, nombre,
                apellidoP, apellidoM, cargoId);

    }

    /**
     * Listando una lista de empleados.
     *
     * @return Lista de tipo Empleado.
     */
    public List<Empleado> listar() {
        return this.empleadoDAO.listar();
    }

    /**
     * Pasando por parámetros el objeto empleado a ser guardado y asignando el
     * id de la categoría a la que pertenece.
     *
     * @param empleado Objeto de tipo Empleado.
     * @param cargoId El id de la categoría correspondiente.
     */
    public void guardar(Empleado empleado, Integer cargoId) {
        empleado.setCargoId(cargoId);
        empleadoDAO.guardar(empleado);
    }
}
