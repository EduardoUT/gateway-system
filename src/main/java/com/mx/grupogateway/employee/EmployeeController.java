/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import com.mx.grupogateway.crud.DataModelForJTable;
import com.mx.grupogateway.user.UserController;
import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmployeeController implements DataModelForJTable {

    private final EmployeeImpl employeeImpl;
    private final UserController userController;
    private final EmployeeService employeeService;

    public EmployeeController() {
        employeeImpl = new EmployeeImpl(new ConnectionFactory().realizarConexion());
        userController = new UserController();
        employeeService = new EmployeeService();
    }

    /**
     * Guarda el User generado, si la operación es éxitosa obtiene el nuevo
     * identificador generado y guarda los datos necesarios para el Employee.
     *
     * @param employee Objeto de tipo Employee.
     * @return Devuelve el id generado, si es -1 el empleado no fue almacenado.
     */
    public int create(Employee employee) {
        int employeeId = -1;
        int userId = userController.create(employee.getUser());
        if (userId != -1 || employee.getUser().getId() != 0) {
            employeeId = employeeImpl.create(employee);
            if (employeeId == -1 || employee.getId() == 0) {
                userController.delete(employee.getUser());
            }
        }
        return employeeId;
    }

    /**
     * Recibe un List de tipo Employee para construir el modelo de filas de un
     * JTable.
     *
     * @return Lista de Object[] con los datos del Employee a mostrar en el
     * JTable.
     */
    @Override
    public List<Object[]> getDataModelForJTable() {
        List<Employee> employees = employeeImpl.getAll();
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
     * Obtiene los identificadores de Categoría de empleado e identificador de
     * usuario.
     *
     * @param user
     * @return Optional de tipo Employee.
     */
    public Optional<Employee> getEmployeeIdentifiersByUserAutenticated(User user) {
        return employeeService.getEmployeeIdentifiersByUserAutenticated(user);
    }

    /**
     * Recibe los valores registrados en la tablaEmpleado a ser modificados.
     *
     * @param employee
     * @return Cantidad de registros actualizados.
     */
    public int update(Employee employee) {
        return employeeImpl.update(employee);
    }

    /**
     * Recibe el identificador del empleado de la tabla de registros
     * tablaEmpleado.
     *
     * @param employee
     * @return Código de error.
     */
    public int delete(Employee employee) {
        return employeeImpl.delete(employee);
    }
}
