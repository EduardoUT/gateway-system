/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee.category;

import com.mx.grupogateway.factory.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmployeeCategoryController {

    private final EmployeeCategoryDAO employeeCategoryDAO;

    public EmployeeCategoryController() {
        this.employeeCategoryDAO = new EmployeeCategoryDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Guarda objeto de tipo EmployeeCategory.
     *
     * @param employeeCategory
     */
    public void guardar(EmployeeCategory employeeCategory) {
        this.employeeCategoryDAO.guardar(employeeCategory);
    }

    /**
     *
     * @return List de tipo EmployeeCategory.
     */
    public List<EmployeeCategory> listar() {
        return this.employeeCategoryDAO.listar();
    }

    /**
     * Actualiza el nombre de una categoría de empleado acorde al id.
     *
     * @param idCategoria
     * @param nombreCategoria
     * @return
     */
    public int actualizar(String idCategoria, String nombreCategoria) {
        return this.employeeCategoryDAO.actualizar(idCategoria, nombreCategoria);
    }

    /**
     * Elimina una categoría de empleado.
     *
     * @param idCategoria
     * @return
     */
    public int eliminar(String idCategoria) {
        return this.employeeCategoryDAO.eliminar(idCategoria);
    }
}
