/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee.category;

import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.factory.ConnectionFactory;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmployeeCategoryController {

    private final EmployeeCategoryImpl employeeCategoryImpl;

    public EmployeeCategoryController() {
        this.employeeCategoryImpl = new EmployeeCategoryImpl(
                new ConnectionFactory().realizarConexion()
        );
    }

    /**
     * Guarda objeto de tipo EmployeeCategory.
     *
     * @param employeeCategory
     */
    public void guardar(EmployeeCategory employeeCategory) {
        this.employeeCategoryImpl.create(employeeCategory);
    }

    /**
     *
     * @return List de tipo EmployeeCategory.
     */
    public List<EmployeeCategory> listar() {
        return this.employeeCategoryImpl.getAll();
    }

    /**
     * Actualiza el nombre de una categoría de emplead.
     *
     * @param employeeCategory
     */
    public void actualizar(EmployeeCategory employeeCategory) {
        this.employeeCategoryImpl.update(employeeCategory);
    }

    /**
     * Elimina una categoría de empleado.
     *
     * @param employee
     */
    public void eliminar(Employee employee) {
        this.employeeCategoryImpl.delete(employee);
    }
}
