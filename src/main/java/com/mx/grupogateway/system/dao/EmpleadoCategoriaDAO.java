/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.EmpleadoCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoCategoriaDAO {

    private final Connection con;

    public EmpleadoCategoriaDAO(Connection con) {
        this.con = con;
    }

    /**
     * Guarda los datos de una nueva categoría de empleado.
     *
     * @param empleadoCategoria
     */
    public void guardar(EmpleadoCategoria empleadoCategoria) {
        String sql = "INSERT INTO EMPLEADO_CATEGORIA "
                + "(ID_CATEGORIA_EMPLEADO, NOMBRE_CATEGORIA) "
                + "VALUES (?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,
                    empleadoCategoria.getidCategoria());
            preparedStatement.setString(2,
                    empleadoCategoria.getNombreCategoria());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue a la categoría %s",
                                    empleadoCategoria));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return List de tipo EmpleadoCargo de la BD.
     */
    public List<EmpleadoCategoria> listar() {
        List<EmpleadoCategoria> empleadoCategorias = new ArrayList<>();
        String sql = "SELECT ID_CATEGORIA_EMPLEADO, NOMBRE_CATEGORIA "
                + "FROM CATEGORIA_EMPLEADO";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    empleadoCategorias.add(new EmpleadoCategoria(
                            resultSet
                                    .getString("ID_CATEGORIA_EMPLEADO"),
                            resultSet
                                    .getString("NOMBRE_CATEGORIA")
                    ));
                }
                return empleadoCategorias;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String sql = "UPDATE CATEGORIA_EMPLEADO "
                + "SET NOMBRE_CATEGORIA = ?"
                + "WHERE ID_CATEGORIA_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreCategoria);
            preparedStatement.setString(2, idCategoria);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina una categoría de empleado.
     *
     * @param idCategoria
     * @return
     */
    public int eliminar(String idCategoria) {
        eliminarRelacionEmpleado(idCategoria);
        String sql = "DELETE FROM CATEGORIA_EMPLEADO "
                + "WHERE ID_CATEGORIA_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, idCategoria);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado asociada a la tabla de
     * usuario a fin de poder eliminarlo.
     *
     * @param idEmpleado
     */
    private void eliminarRelacionEmpleado(String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET ID_CATEGORIA_EMPLEADO = ? "
                + "WHERE ID_CATEGORIA_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, "Sin Categoria");
            preparedStatement.setString(2, idEmpleado);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
