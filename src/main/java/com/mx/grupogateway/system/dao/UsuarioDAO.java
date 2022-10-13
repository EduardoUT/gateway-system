/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UsuarioDAO {

    private final Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    /**
     *
     * @return List de tipo Usuario de la BD.
     */
    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        String sql = "SELECT ID_USUARIO, NOMBRE, CLAVE_SEGURIDAD FROM USUARIO";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try ( ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    Usuario fila = new Usuario(
                            resultSet.getInt("ID_USUARIO"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("CLAVE_SEGURIDAD")
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
     * Consulta el id_usuario del empleado.
     *
     * @param empleado
     * @return Como puede o no devolver un valor la BD, se envuelve el resultado
     * de getUsuarioId() en un Optional, si no devuelve nada el empleado no
     * existe, si devuelve cero el empleado no posee una cuenta y si devueslve un
     * id de usuario entonces ya fue asignada una cuenta de usuario a ese
     * empleado.
     */
    public Optional consultarIdUsuario(Empleado empleado) {
        String sql = "SELECT ID_USUARIO FROM EMPLEADO WHERE ID_EMPLEADO = ?";
        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setInt(1, empleado.getIdEmpleado());
            preparedStatement.execute();
            try ( ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    empleado.setUsuarioId(resultSet.getInt("ID_USUARIO"));
                }
                return Optional.ofNullable(empleado.getUsuarioId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Realiza la eliminación de un usuario en la BD tomando como referencia el
     * ID.
     *
     * @param usuarioId
     * @return
     */
    public int eliminar(Integer usuarioId) {
        eliminarAsociacionEmpleadoUsuario(usuarioId);
        String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setInt(1, usuarioId);
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
     * @param usuarioId
     */
    private void eliminarAsociacionEmpleadoUsuario(Integer usuarioId) {
        String sql = "UPDATE EMPLEADO SET ID_USUARIO = ? WHERE ID_USUARIO = ?";
        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setNull(1, usuarioId);
            preparedStatement.setInt(2, usuarioId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
