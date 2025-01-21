/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import com.mx.grupogateway.user.UserController;
import com.mx.grupogateway.factory.ConnectionFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmployeeController {

    private final EmployeeImpl employeeImpl;
    private final UserController userController;

    public EmployeeController() {
        this.employeeImpl = new EmployeeImpl(
                new ConnectionFactory().realizarConexion()
        );
        userController = new UserController();
    }

    /**
     * Guarda el User generado, si la operación es éxitosa obtiene el nuevo
 identificador generado y guarda los datos necesarios para el Employee.
     *
     * @param employee Objeto de tipo Employee.
     * @return Devuelve el id generado, si es -1 el empleado no fue almacenado.
     */
    public int guardar(Employee employee) {
        int employeeId = -1;
        int userId = this.userController.create(employee.getUser());
        if (userId != -1 || employee.getUser().getId() != 0) {
            employeeId = this.employeeImpl.create(employee);
            if (employeeId == -1 || employee.getId() == 0) {
                this.userController.delete(employee.getUser());
            }
        }
        return employeeId;
    }

    /**
     * Lista de empleados a mostrar en el JTable.
     *
     * @return Lista de Object[] con los datos del Employee a mostrar en el
 TableModel.
     */
    public List<Object[]> listar() {
        List<Employee> employees = this.employeeImpl.getAll();
        List<Object[]> dataModelEmployees = new ArrayList<>();
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                dataModelEmployees.add(
                        new Object[]{
                            employee.getId(),
                            employee.getName(),
                            employee.getPaternalSurname(),
                            employee.getMaternalSurname(),
                            employee.getUser().getId(),
                            employee.getEmployeeCategory().getCategoryName()
                        }
                );
            }
            return dataModelEmployees;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Recibe los valores registrados en la tablaEmpleado a ser modificados.
     *
     * @param employee
     * @return Cantidad de registros actualizados.
     */
    public int actualizar(Employee employee) {
        return this.employeeImpl.update(employee);
    }

    /**
     * Recibe el identificador del empleado de la tabla de registros
     * tablaEmpleado.
     *
     * @param id
     * @return Código de error.
     */
    public int eliminar(String id) {
        return this.employeeImpl.delete(id);
    }
}
