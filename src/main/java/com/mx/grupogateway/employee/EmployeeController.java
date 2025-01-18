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

    private final EmployeeDAO employeeDAO;
    private final UserController userController;

    public EmployeeController() {
        this.employeeDAO = new EmployeeDAO(
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
        int userId = this.userController.guardar(employee.getUser());
        if (userId != -1 || employee.getUser().getId() != 0) {
            employeeId = this.employeeDAO.guardar(employee);
            if (employeeId == -1 || employee.getId() == 0) {
                this.userController.eliminar(employee.getUser().getId());
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
        List<Employee> employees = this.employeeDAO.listar();
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
     * @param empleadoId
     * @param nombre
     * @param apellidoP
     * @param apellidoM
     * @param idCategoria
     * @return Cantidad de registros actualizados.
     */
    public int actualizar(String empleadoId, String nombre,
            String apellidoP, String apellidoM, String idCategoria) {
        return this.employeeDAO.actualizar(empleadoId, nombre,
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
        return this.employeeDAO.eliminar(empleadoId);
    }
}
