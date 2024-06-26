/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.EmpleadoCategoria;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.security.SecurityPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * Se invoca en EmpleadoDAO, se ejecuta antes de insertar un nuevo empleado
     * en la BD, a fin de poder asegurar la integridad y relación con la tabla
     * empleados.
     *
     * @param usuario
     * @param idEmpleado
     */
    public void guardar(Usuario usuario, String idEmpleado) {
        String sql = "INSERT INTO USUARIOS (ID_USUARIO, NOMBRE_USUARIO, PASSWORD)"
                + "VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getIdUsuario());
            preparedStatement.setString(2, usuario.getNombreUsuario());
            preparedStatement.setString(3, usuario.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return List de tipo Usuario de la BD.
     */
    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO FROM USUARIOS";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
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
     * Permite validar que una contraseña contenga el valor NULL en la BD, lo
     * cual índica que es un nuevo usuario que aún no ha registrado su
     * contraseña.
     *
     * @param idUsuario
     * @return
     */
    public boolean esPasswordNula(String idUsuario) {
        String passwordNula = "";
        String sql = "SELECT PASSWORD FROM USUARIOS WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    passwordNula = resultSet.getString("PASSWORD");
                }
                return passwordNula.equals("NULL");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Evalúa que la password se encuentre en la BD, comparando la ingresada por
     * el usuario con la encriptada.<br>
     * Si es NULL se omite la consulta SQL.
     *
     * @param usuario
     * @return
     */
    public boolean esPasswordValida(Usuario usuario) {
        if (usuario.getPassword().equals("NULL")) {
            return false;
        }
        if (usuario.getIdUsuario() == null && !usuario.getNombreUsuario().isEmpty()) {
            HashMap<String, String> registros;
            registros = new HashMap<>();
            String sql = "SELECT PASSWORD, ID_USUARIO FROM USUARIOS WHERE NOMBRE_USUARIO = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getNombreUsuario());
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        registros.put(
                                resultSet.getString("PASSWORD"),
                                resultSet.getString("ID_USUARIO")
                        );
                    }
                    for (Map.Entry<String, String> registro : registros.entrySet()) {
                        String clave = registro.getKey();
                        if (SecurityPassword.assertData(clave, usuario.getPassword())) {
                            String valor = registro.getValue();
                            usuario.setIdUsuario(valor);
                            break;
                        }
                    }
                    return (usuario.getIdUsuario() != null);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            Usuario usuarioPassword = new Usuario();
            String sql = "SELECT PASSWORD FROM USUARIOS WHERE ID_USUARIO = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getIdUsuario());
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        usuarioPassword.setPassword(resultSet.getString("PASSWORD"));
                    }
                    return SecurityPassword.assertData(
                            usuarioPassword.getPassword(),
                            usuario.getPassword());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Consulta los identificadores de usuario y la categoría a la que pertenece
     * un empleado.
     *
     * @param usuario
     * @return
     */
    public Empleado consultarPerfilUsuario(Usuario usuario) {
        Empleado empleado = null;
        EmpleadoCategoria empleadoCategoria = new EmpleadoCategoria();
        if (esPasswordValida(usuario)) {
            String sql = "SELECT USUARIOS.ID_USUARIO, ID_CATEGORIA_EMPLEADO "
                    + "FROM EMPLEADOS "
                    + "INNER JOIN USUARIOS "
                    + "ON EMPLEADOS.ID_USUARIO = USUARIOS.ID_USUARIO "
                    + "WHERE USUARIOS.NOMBRE_USUARIO = ? AND USUARIOS.ID_USUARIO = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getNombreUsuario());
                preparedStatement.setString(2, usuario.getIdUsuario());
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        empleadoCategoria.setIdCategoria(resultSet.getString("ID_CATEGORIA_EMPLEADO"));
                        empleado = new Empleado(
                                new Usuario(resultSet.getString("ID_USUARIO")),
                                empleadoCategoria
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return empleado;
    }

    /**
     * Consulta el id_usuario del empleado.
     *
     * @param idUsuario
     * @return Como puede o no devolver un valor la BD, se envuelve el resultado
     * de idUsuarioObtenido en un Optional.
     */
    public Optional consultarIdUsuario(String idUsuario) {
        String idUsuarioObtenido = null;
        String sql = "SELECT ID_USUARIO FROM USUARIOS WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    idUsuarioObtenido = resultSet.getString("ID_USUARIO");
                }
                return Optional.ofNullable(idUsuarioObtenido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza la password dado el nombre y el id del usuario.
     *
     * @param usuario
     */
    public void actualizarPassword(Usuario usuario) {
        if (usuario.getNombreUsuario() == null) {
            String sql = "UPDATE USUARIOS SET PASSWORD = ? WHERE ID_USUARIO = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getPassword());
                preparedStatement.setString(2, usuario.getIdUsuario());
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            String sql = "UPDATE USUARIOS SET PASSWORD = ? WHERE ID_USUARIO = ? "
                    + "AND NOMBRE_USUARIO = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getPassword());
                preparedStatement.setString(2, usuario.getIdUsuario());
                preparedStatement.setString(3, usuario.getNombreUsuario());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado.
     *
     * @param idUsuario
     * @param idEmpleado
     */
    public void actualizarIdUsuarioEnTablaEmpleado(String idUsuario, String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET ID_USUARIO = ? WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.setString(2, idEmpleado);
            preparedStatement.execute();
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
    private void actualizarRelacionEmpleadoUsuario(String idUsuario) {
        String sql = "UPDATE EMPLEADOS SET ID_USUARIO = ? WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, "NULL");
            preparedStatement.setString(2, idUsuario);
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
        actualizarRelacionEmpleadoUsuario(idUsuario);
        String sql = "DELETE FROM USUARIOS WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
