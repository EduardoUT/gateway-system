/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.user;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.employee.category.EmployeeCategory;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.user.User;
import com.mx.grupogateway.util.SecurityPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UserDAO extends ConnectionStatus {

    private static final String ID_USER = "ID_USUARIO";
    private static final String PASSWORD_USER = "PASSWORD_USUARIO";
    private static final Logger logger = LoggerConfig.getLogger();

    public UserDAO(Connection con) {
        super(con);
    }

    /**
     * Se invoca en EmpleadoDAO, se ejecuta antes de insertar un nuevo empleado
     * en la BD, a fin de poder asegurar la integridad y relación con la tabla
     * empleados.
     *
     * @param user
     * @return Identificador del usuario generado por la Base de datos.
     */
    public int guardar(User user) {
        int userId = -1;
        String sql = "INSERT INTO USUARIOS (NOMBRE_USUARIO, " + PASSWORD_USER + ")"
                + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt(1);
                    user.setId(userId);
                }

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar usuario: {0}", e.getMessage());
        }
        return userId;
    }

    /**
     *
     * @return List de tipo User de la BD.
     */
    public List<User> listar() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT " + ID_USER + ", NOMBRE_USUARIO FROM USUARIOS";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    User fila = new User(
                            resultSet.getInt(ID_USER),
                            resultSet.getString("NOMBRE_USUARIO")
                    );
                    users.add(fila);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar usuarios: {0}", e.getMessage());
        }
        return users;
    }

    public boolean esPerfilValido(User user) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String sql = "SELECT " + PASSWORD_USER + ", " + ID_USER + " FROM USUARIOS WHERE NOMBRE_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    hashMap.put(
                            resultSet.getString(PASSWORD_USER),
                            resultSet.getInt(ID_USER)
                    );
                }
                for (Map.Entry<String, Integer> registro : hashMap.entrySet()) {
                    String clave = registro.getKey();
                    if (SecurityPassword.assertData(clave, user.getPassword())) {
                        Integer valor = registro.getValue();
                        user.setId(valor);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar credenciales de usuario: {0}",
                    e.getMessage());
        }
        return (user.getId() != 0 || user.getId() != null);
    }

    /**
     * Permite validar que una contraseña contenga el valor NULL en la BD, lo
     * cual índica que es un nuevo usuario que aún no ha registrado su
     * contraseña.
     *
     * @param idUsuario
     * @return
     */
    public boolean esPasswordNula(Integer idUsuario) {
        String passwordNula = "";
        String sql = "SELECT " + PASSWORD_USER + " FROM USUARIOS WHERE " + ID_USER + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    passwordNula = resultSet.getString(PASSWORD_USER);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar password: {0}", e.getMessage());
        }
        return passwordNula.equals("NULL");
    }

    /**
     * Evalúa que la password se encuentre en la BD, comparando la ingresada por
     * el usuario con la encriptada.<br>
     * Si es NULL se omite la consulta SQL.
     *
     * @param user
     * @return
     */
    public boolean esPasswordValida(User user) {
        boolean esValida = false;
        User usuarioPassword = new User();
        String sql = "SELECT " + PASSWORD_USER + " FROM USUARIOS WHERE " + ID_USER + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    usuarioPassword.setPassword(resultSet.getString(PASSWORD_USER), false);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al validar password: {0}", e.getMessage());
        }
        if (usuarioPassword.getPassword() != null || !usuarioPassword.getPassword().isEmpty()) {
            esValida = SecurityPassword.assertData(
                    usuarioPassword.getPassword(),
                    user.getPassword());
        }
        return esValida;
    }

    /**
     * Consulta los identificadores de usuario y la categoría a la que pertenece
     * un empleado.
     *
     * @param user
     * @return
     */
    public Employee consultarPerfilUsuario(User user) {
        Employee employee = null;
        EmployeeCategory employeeCategory = new EmployeeCategory();
        if (esPerfilValido(user)) {
            String sql = "SELECT USUARIOS." + ID_USER + ", ID_CATEGORIA_EMPLEADO "
                    + "FROM EMPLEADOS "
                    + "INNER JOIN USUARIOS "
                    + "ON EMPLEADOS." + ID_USER + " = USUARIOS." + ID_USER + " "
                    + "WHERE USUARIOS.NOMBRE_USUARIO = ? AND USUARIOS." + ID_USER + " = ?";
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setInt(2, user.getId());
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        employeeCategory.setId(resultSet.getString("ID_CATEGORIA_EMPLEADO"));
                        employee = new Employee(
                                new User(resultSet.getInt(ID_USER)),
                                employeeCategory
                        );
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al consultar el perfil de usuario: {0}", e.getMessage());
            }
        }
        return employee;
    }

    /**
     * Consulta el id_usuario del empleado.
     *
     * @param idUsuario
     * @return Como puede o no devolver un valor la BD, se envuelve el resultado
     * de idUsuarioObtenido en un Optional.
     */
    public Integer consultarIdUsuario(Integer idUsuario) {
        Integer idUsuarioObtenido = -1;
        String sql = "SELECT " + ID_USER + " FROM USUARIOS WHERE " + ID_USER + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    idUsuarioObtenido = resultSet.getInt(ID_USER);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar el identificador de usuario: {0}", e.getMessage());
        }
        return idUsuarioObtenido;
    }

    /**
     * Actualiza la password dado el nombre y el id del usuario.
     *
     * @param user
     */
    public void actualizarPasswordNula(User user) {
        String sql = "UPDATE USUARIOS SET " + PASSWORD_USER + " = ? WHERE " + ID_USER + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar password nula: {0}", e.getMessage());
        }
    }

    /**
     * Actualiza la password de un usuario existente acorde al idUsuario.
     *
     * @param user
     * @return Retorna la cantidad de registros afectados.
     */
    public int actualizarPassword(User user) {
        int registrosAfectados = 0;
        String sql = "UPDATE USUARIOS SET " + PASSWORD_USER + " = ? WHERE " + ID_USER + " = ? ";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
            registrosAfectados = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar password existente: {0}", e.getMessage());
        }
        return registrosAfectados;
    }

    /**
     * Actualiza el id_usuario en la tabla empleado.
     *
     * @param idUsuario
     * @param idEmpleado
     */
    public void actualizarIdUsuarioEnTablaEmpleado(String idUsuario, String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET " + ID_USER + " = ? WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.setString(2, idEmpleado);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar clave foranea "
                    + "idUsuario en tabla empleados según idEmpleado: {0}", e.getMessage());
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado asociada a la tabla de
     * usuario a fin de poder eliminarlo.
     *
     * @param idUsuario
     */
    private void actualizarRelacionEmpleadoUsuario(Integer idUsuario) {
        String sql = "UPDATE EMPLEADOS SET " + ID_USER + " = ? WHERE " + ID_USER + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "NULL");
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar clave foranea "
                    + "idUsuario en tabla empleados: {0}", e.getMessage());
        }
    }

    /**
     * Realiza la eliminación de un usuario en la BD tomando como referencia el
     * ID.
     *
     * @param idUsuario
     * @return
     */
    public int eliminar(Integer idUsuario) {
        int updateCount = 0;
        actualizarRelacionEmpleadoUsuario(idUsuario);
        String sql = "DELETE FROM USUARIOS WHERE " + ID_USER + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar usuario: {0}", e.getMessage());
        }
        return updateCount;
    }

}
