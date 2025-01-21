/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee.category;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.crud.CreateEntityDAO;
import com.mx.grupogateway.crud.DeleteEntityDAO;
import com.mx.grupogateway.crud.GetAllDAO;
import com.mx.grupogateway.crud.UpdateEntityDAO;
import com.mx.grupogateway.crud.UpdateEntityRelationship;
import com.mx.grupogateway.employee.Employee;
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
public class EmployeeCategoryImpl extends ConnectionStatus
        implements CreateEntityDAO<EmployeeCategory>, GetAllDAO<EmployeeCategory>,
        UpdateEntityDAO<EmployeeCategory>, UpdateEntityRelationship<EmployeeCategory>,
        DeleteEntityDAO<Employee> {

    private static final Logger logger = LoggerConfig.getLogger();
    private static final String ID_CATEGORIA_EMPLEADO = "ID_CATEGORIA_EMPLEADO";

    public EmployeeCategoryImpl(Connection con) {
        super(con);
    }

    /**
     * Guarda los datos de una nueva categoría de empleado.
     *
     * @param employeeCategory
     */
    @Override
    public void create(EmployeeCategory employeeCategory) {
        String sql = "INSERT INTO EMPLEADO_CATEGORIA "
                + "(" + ID_CATEGORIA_EMPLEADO + ", NOMBRE_CATEGORIA) "
                + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,
                    employeeCategory.getId());
            preparedStatement.setString(2,
                    employeeCategory.getCategoryName());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar categor\u00eda: {0}", e.getMessage());
        }
    }

    /**
     *
     * @return List de tipo EmpleadoCargo de la BD.
     */
    @Override
    public List<EmployeeCategory> getAll() {
        List<EmployeeCategory> employeeCategories = new ArrayList<>();
        String sql = "SELECT " + ID_CATEGORIA_EMPLEADO + ", NOMBRE_CATEGORIA "
                + "FROM CATEGORIA_EMPLEADO";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    employeeCategories.add(new EmployeeCategory(
                            resultSet
                                    .getInt(ID_CATEGORIA_EMPLEADO),
                            resultSet
                                    .getString("NOMBRE_CATEGORIA")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar categor\u00edas: {0}", e.getMessage());
        }
        return employeeCategories;
    }

    /**
     * Realiza la actualización del nombre de una categoría de empleado según el
     * id proporcionado.
     *
     * @param employeeCategory
     */
    @Override
    public void update(EmployeeCategory employeeCategory) {
        String sql = "UPDATE CATEGORIA_EMPLEADO "
                + "SET NOMBRE_CATEGORIA = ? WHERE " + ID_CATEGORIA_EMPLEADO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, employeeCategory.getCategoryName());
            preparedStatement.setInt(2, employeeCategory.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar categor\u00eda: {0}", e.getMessage());
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado asociada a la tabla de
     * usuario a fin de poder eliminarlo.
     *
     * @param employeeCategory
     */
    @Override
    public void updateEntityRelationship(EmployeeCategory employeeCategory) {
        String sql = "UPDATE EMPLEADOS SET " + ID_CATEGORIA_EMPLEADO + " = ? "
                + "WHERE " + ID_CATEGORIA_EMPLEADO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "Sin Categoria");
            preparedStatement.setInt(2, employeeCategory.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar claves foráneas "
                    + "empleado-categoria-empleado: {0}", e.getMessage());
        }
    }

    /**
     * Elimina una categoría de empleado y actualiza todos los empleados
     * asociados.
     *
     * @param employee
     */
    @Override
    public void delete(Employee employee) {
        updateEntityRelationship(employee.getEmployeeCategory());
        String sql = "DELETE FROM CATEGORIA_EMPLEADO "
                + "WHERE " + ID_CATEGORIA_EMPLEADO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, employee.getEmployeeCategory().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar categor\u00eda: {0}", e.getMessage());
        }
    }
}
