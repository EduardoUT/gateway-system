/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.EmpleadoCategoria;
import com.mx.grupogateway.system.modelo.Usuario;
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
        String sql = "INSERT INTO EMPLEADOS "
                + "(ID_EMPLEADO, NOMBRE, APE_PAT, "
                + "APE_MAT, ID_USUARIO, ID_CATEGORIA_EMPLEADO) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, empleado.getIdEmpleado());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setString(3, empleado.getApellidoPaterno());
            preparedStatement.setString(4, empleado.getApellidoMaterno());
            preparedStatement.setString(5, empleado.getUsuario().getIdUsuario());
            preparedStatement.setString(6, empleado.getEmpleadoCategoria().getCategoriaId());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
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
        String sql = "SELECT ID_EMPLEADO, NOMBRE, APE_PAT, APE_MAT, ID_USUARIO, "
                + "NOMBRE_CATEGORIA "
                + "FROM CATEGORIA_EMPLEADO "
                + "INNER JOIN EMPLEADOS ON "
                + "CATEGORIA_EMPLEADO.ID_CATEGORIA_EMPLEADO = "
                + "EMPLEADOS.ID_CATEGORIA_EMPLEADO; ";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    resultado.add(
                            new Empleado(
                                    resultSet.getString("ID_EMPLEADO"),
                                    resultSet.getString("NOMBRE"),
                                    resultSet.getString("APE_PAT"),
                                    resultSet.getString("APE_MAT"),
                                    new Usuario(resultSet.getString("ID_USUARIO")),
                                    new EmpleadoCategoria(resultSet.getString("NOMBRE_CATEGORIA"))
                            )
                    );
                }
                return resultado;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Realiza la actualización de los registros del empleado acorde a su
     * empleadoId.
     *
     * @param idEmpleado
     * @param nombre
     * @param apellidoP
     * @param apellidoM
     * @param idCategoria
     * @return
     */
    public int modificar(String idEmpleado, String nombre, String apellidoP,
            String apellidoM, String idCategoria) {
        String sql = "UPDATE EMPLEADOS "
                + "SET NOMBRE = ?, APE_PAT = ?, APE_MAT = ?, "
                + "ID_CATEGORIA_EMPLEADO = ? "
                + "WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoP);
            preparedStatement.setString(3, apellidoM);
            preparedStatement.setString(4, idCategoria);
            preparedStatement.setString(5, idEmpleado);
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
     * @param idEmpleado
     * @return
     */
    public int eliminar(String idEmpleado) {
        String sql = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, idEmpleado);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
