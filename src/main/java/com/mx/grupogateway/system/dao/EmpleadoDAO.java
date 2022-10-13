/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
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
public class EmpleadoDAO {

    private final Connection con;

    public EmpleadoDAO(Connection con) {
        this.con = con;
    }

    /**
     * Realiza el guardado en la BD del empleado.
     *
     * @param empleado
     */
    public void guardar(Empleado empleado) {
        String sql = "INSERT INTO EMPLEADO "
                + "(ID_EMPLEADO, NOMBRE, APELLIDO_P, "
                + "APELLIDO_M, ID_CATEGORIA, ID_USUARIO) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, empleado.getIdEmpleado());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setString(3, empleado.getApellidoPaterno());
            preparedStatement.setString(4, empleado.getApellidoMaterno());
            preparedStatement.setInt(5, empleado.getCargoId());
            preparedStatement.setNull(6, empleado.getUsuarioId());
            preparedStatement.execute();

            try ( ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue guardado el empleado %s",
                                    empleado));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Devuelve un listado con todos los valores de la BD correspondientes a los
     * atributos del objeto de tipo Empleado.
     *
     * @return Lista de tipo Empleado de la BD.
     */
    public List<Empleado> listar() {
        List<Empleado> resultado = new ArrayList<>();
        String sql = "SELECT ID_EMPLEADO, NOMBRE, APELLIDO_P, "
                + "APELLIDO_M, ID_CATEGORIA, ID_USUARIO FROM EMPLEADO";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try ( ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    Empleado fila = new Empleado(
                            resultSet.getString("ID_EMPLEADO"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO_P"),
                            resultSet.getString("APELLIDO_M"),
                            resultSet.getInt("ID_CATEGORIA"),
                            resultSet.getInt("ID_USUARIO")
                    );
                    resultado.add(fila);
                }
                return resultado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Realiza la actualización de los registros del empleado acorde a su
     * empleadoId.
     *
     * @param empleadoId
     * @param nombre
     * @param apellidoP
     * @param apellidoM
     * @param cargoId
     * @return
     */
    public int modificar(String empleadoId, String nombre, String apellidoP, String apellidoM, Integer cargoId) {
        String sql = "UPDATE EMPLEADO "
                + "SET NOMBRE = ?, APELLIDO_P = ?, APELLIDO_M = ?, "
                + "ID_CATEGORIA = ? "
                + "WHERE ID_EMPLEADO = ?";
        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoP);
            preparedStatement.setString(3, apellidoM);
            preparedStatement.setInt(4, cargoId);
            preparedStatement.setString(5, empleadoId);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Realiza la eliminación del registro en la BD, acorde al empleadoId.
     *
     * @param empleadoId
     * @return
     */
    public int eliminar(String empleadoId) {
        String sql = "DELETE FROM EMPLEADO WHERE ID_EMPLEADO = ?";
        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, empleadoId);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
