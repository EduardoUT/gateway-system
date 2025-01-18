/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee.category;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.config.ConnectionStatus;
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
public class EmployeeCategoryDAO extends ConnectionStatus {

    private static final Logger logger = LoggerConfig.getLogger();
    private static final String ID_CATEGORIA_EMPLEADO = "ID_CATEGORIA_EMPLEADO";

    public EmployeeCategoryDAO(Connection con) {
        super(con);
    }

    /**
     * Guarda los datos de una nueva categoría de empleado.
     *
     * @param employeeCategory
     */
    public void guardar(EmployeeCategory employeeCategory) {
        String sql = "INSERT INTO EMPLEADO_CATEGORIA "
                + "(" + ID_CATEGORIA_EMPLEADO + ", NOMBRE_CATEGORIA) "
                + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,
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
    public List<EmployeeCategory> listar() {
        List<EmployeeCategory> employeeCategories = new ArrayList<>();
        String sql = "SELECT " + ID_CATEGORIA_EMPLEADO + ", NOMBRE_CATEGORIA "
                + "FROM CATEGORIA_EMPLEADO";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    employeeCategories.add(new EmployeeCategory(
                            resultSet
                                    .getString(ID_CATEGORIA_EMPLEADO),
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
     * @param idCategoria
     * @param nombreCategoria
     * @return
     */
    public int actualizar(String idCategoria, String nombreCategoria) {
        int updateCount = 0;
        String sql = "UPDATE CATEGORIA_EMPLEADO "
                + "SET NOMBRE_CATEGORIA = ? WHERE " + ID_CATEGORIA_EMPLEADO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, nombreCategoria);
            preparedStatement.setString(2, idCategoria);
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar categor\u00eda: {0}", e.getMessage());
        }
        return updateCount;
    }

    /**
     * Elimina una categoría de empleado.
     *
     * @param idCategoria
     * @return
     */
    public int eliminar(String idCategoria) {
        int updateCount = 0;
        eliminarRelacionEmpleado(idCategoria);
        String sql = "DELETE FROM CATEGORIA_EMPLEADO "
                + "WHERE " + ID_CATEGORIA_EMPLEADO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, idCategoria);
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar categor\u00eda: {0}", e.getMessage());
        }
        return updateCount;
    }

    /**
     * Actualiza el id_usuario en la tabla empleado asociada a la tabla de
     * usuario a fin de poder eliminarlo.
     *
     * @param idEmpleado
     */
    private void eliminarRelacionEmpleado(String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET " + ID_CATEGORIA_EMPLEADO + " = ? "
                + "WHERE " + ID_CATEGORIA_EMPLEADO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "Sin Categoria");
            preparedStatement.setString(2, idEmpleado);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar claves foráneas "
                    + "empleado-categoria-empleado: {0}", e.getMessage());
        }
    }
}
