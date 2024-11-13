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
    private final UsuarioController usuarioController;

    public EmpleadoController() {
        this.empleadoDAO = new EmpleadoDAO(
                new ConnectionFactory().realizarConexion()
        );
        usuarioController = new UsuarioController();
    }

    /**
     * Guarda el Usuario generado, si la operación es éxitosa obtiene el nuevo
     * identificador generado y guarda los datos necesarios para el Empleado.
     *
     * @param empleado Objeto de tipo Empleado.
     * @return Devuelve el id generado, si es -1 el empleado no fue almacenado.
     */
    public int guardar(Empleado empleado) {
        int idEmpleado = -1;
        int idUsuario = this.usuarioController.guardar(empleado.getUsuario());
        if (idUsuario != -1 || empleado.getUsuario().getIdUsuario() != 0) {
            idEmpleado = this.empleadoDAO.guardar(empleado);
            if (idEmpleado == -1 || empleado.getIdEmpleado() == 0) {
                this.usuarioController.eliminar(empleado.getUsuario().getIdUsuario());
            }
        }
        return idEmpleado;
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
     * Recibe los valores registrados en la tablaEmpleado a ser modificados.
     *
     * @param empleadoId
     * @param nombre
     * @param apellidoP
     * @param apellidoM
     * @param idCategoria
     * @return Cantidad de registros actualizados.
     */
    public int actualizar(String empleadoId, String nombre,
            String apellidoP, String apellidoM, String idCategoria) {
        return this.empleadoDAO.actualizar(empleadoId, nombre,
                apellidoP, apellidoM, idCategoria);
    }

    /**
     * Recibe el identificador del empleado de la tabla de registros
     * tablaEmpleado.
     *
     * @param empleadoId
     * @return Código de error.
     */
    public int eliminar(String empleadoId) {
        return this.empleadoDAO.eliminar(empleadoId);
    }
}
