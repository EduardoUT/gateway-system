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
import java.sql.Statement;
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
     * Ejecuta dos operaciones:
     *
     * 1. Guarda los valores de los atributos de Usuario en la tabla usuario.
     *
     * 2. Consulta el nuevo id_usuario almacenado.
     *
     * 2. Actualiza el id_usuario obtenido de la consulta.
     *
     *
     * TODO Encriptar contraseña al almacenar.
     *
     * @param usuario
     * @param idEmpleado
     */
    public void guardar(Usuario usuario, String idEmpleado) {
        String idUsuarioGenerado = "";
        String sql = "INSERT INTO USUARIOS (NOMBRE_USUARIO, PASSWORD)"
                + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue guardado el usuaro %s ",
                                    usuario)
                    );
                    idUsuarioGenerado = resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        modificarUsuarioIdEnTablaEmpleado(idUsuarioGenerado, idEmpleado);
    }

    /**
     *
     * @return List de tipo Usuario de la BD.
     */
    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO FROM USUARIOS";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    Usuario fila = new Usuario(
                            resultSet.getString("ID_USUARIO"),
                            resultSet.getString("NOMBRE_USUARIO")
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
     * existe, si devuelve cero el empleado no posee una cuenta y si devueslve
     * un id de usuario entonces ya fue asignada una cuenta de usuario a ese
     * empleado.
     */
    public Optional consultarIdUsuario(Empleado empleado) {
        String sql = "SELECT ID_USUARIO FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, empleado.getIdEmpleado());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    empleado.setUsuarioId(resultSet.getString("ID_USUARIO"));
                }
                return Optional.ofNullable(empleado.getUsuario().getIdUsuario());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado.
     *
     * @param idUsuario
     * @param idEmpleado
     */
    public void modificarUsuarioIdEnTablaEmpleado(String idUsuario, String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET ID_USUARIO = ? WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.setString(2, idEmpleado);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Realiza la eliminación de un usuario en la BD tomando como referencia el
     * ID.
     *
     * @param idUsuario
     * @return
     */
    public int eliminar(String idUsuario) {
        eliminarRelacionEmpleadoUsuario(idUsuario);
        String sql = "DELETE FROM USUARIOS WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, idUsuario);
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
     * @param idUsuario
     */
    private void eliminarRelacionEmpleadoUsuario(String idUsuario) {
        String sql = "UPDATE EMPLEADOS SET ID_USUARIO = ? WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setString(1, "NULL");
            preparedStatement.setString(2, idUsuario);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
