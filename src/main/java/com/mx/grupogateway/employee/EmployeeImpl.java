/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import com.mx.grupogateway.employee.category.EmployeeCategory;
import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.crud.GetAllDAO;
import com.mx.grupogateway.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmployeeImpl extends ConnectionStatus implements GetAllDAO<Employee> {

    private static final Logger logger = LoggerConfig.getLogger();

    public EmployeeImpl(Connection con) {
        super(con);
    }

    /**
     * Realiza el guardado en la BD del empleado.
     *
     * @param employee
     * @return Devuelve el identificador si la inserción se ejecuto
     * correctamente, caso contrario devuelve -1.
     */
    public int create(Employee employee) {
        int employeeId = -1;
        String sql = "INSERT INTO EMPLEADOS "
                + "(NOMBRE, APE_PAT, "
                + "APE_MAT, ID_USUARIO, ID_CATEGORIA_EMPLEADO) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPaternalSurname());
            preparedStatement.setString(3, employee.getMaternalSurname());
            preparedStatement.setInt(4, employee.getUser().getId());
            preparedStatement.setInt(5, employee.getEmployeeCategory().getId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    employeeId = resultSet.getInt(1);
                    employee.setId(employeeId);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar empleado: {0}", e.getMessage());
        }
        return employeeId;
    }

    /**
     * Devuelve un listado con todos los valores de la BD correspondientes a los
     * atributos del objeto de tipo Employee.
     *
     * @return Lista de tipo Employee de la BD.
     */
    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT ID_EMPLEADO, NOMBRE, APE_PAT, APE_MAT, ID_USUARIO, "
                + "NOMBRE_CATEGORIA, EMPLEADOS.ID_CATEGORIA_EMPLEADO "
                + "FROM CATEGORIA_EMPLEADO "
                + "INNER JOIN EMPLEADOS ON "
                + "CATEGORIA_EMPLEADO.ID_CATEGORIA_EMPLEADO = "
                + "EMPLEADOS.ID_CATEGORIA_EMPLEADO "
                + "WHERE NOMBRE_CATEGORIA = 'Administrador Facturación' "
                + "OR NOMBRE_CATEGORIA = 'Operador Facturación'";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    employees.add(new Employee(
                            resultSet.getInt("ID_EMPLEADO"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APE_PAT"),
                            resultSet.getString("APE_MAT"),
                            new User(resultSet.getInt("ID_USUARIO")),
                            new EmployeeCategory(
                                    resultSet.getInt("ID_CATEGORIA_EMPLEADO"),
                                    resultSet.getString("NOMBRE_CATEGORIA")
                            )
                    )
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar empleado: {0}", e.getMessage());
        }
        return employees;
    }

    /**
     * Realiza la actualización de los registros del empleado acorde a su
     * empleadoId.
     *
     * @param employee
     * @return Cantidad de registros afectados.
     */
    public int update(Employee employee) {
        int updateCount = 0;
        String sql = "UPDATE EMPLEADOS "
                + "SET NOMBRE = ?, APE_PAT = ?, APE_MAT = ?, "
                + "ID_CATEGORIA_EMPLEADO = ? "
                + "WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPaternalSurname());
            preparedStatement.setString(3, employee.getMaternalSurname());
            preparedStatement.setInt(4, employee.getEmployeeCategory().getId());
            preparedStatement.setInt(5, employee.getId());
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar datos de empleado: {0}", e.getMessage());
        }
        return updateCount;
    }

    /**
     * Realiza la eliminación del registro en la BD, acorde al empleadoId.
     *
     * @param idEmpleado
     * @return Código de error
     */
    public int delete(String idEmpleado) {
        int registrosAfectados = 0;
        String sql = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, idEmpleado);
            preparedStatement.execute();
            registrosAfectados = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar empleado: {0}", e.getMessage());
        }
        return registrosAfectados;
    }
}
