/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.user;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.employee.category.EmployeeCategory;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.crud.GetAllDAO;
import com.mx.grupogateway.crud.GetByIdDAO;
import com.mx.grupogateway.crud.UpdateEntityRelationship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UserImpl extends ConnectionStatus implements GetAllDAO<User>,
        GetByIdDAO<Optional<User>, Integer>, UpdateEntityRelationship<User> {

    private static final String ID_USER = "ID_USUARIO";
    private static final String PASSWORD_USER = "PASSWORD_USUARIO";
    private static final Logger logger = LoggerConfig.getLogger();

    public UserImpl(Connection con) {
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
    public int create(User user) {
        int userId = -1;
        String sql = "INSERT INTO USUARIOS (NOMBRE_USUARIO, PASSWORD_USUARIO) "
                + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getHash());
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
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO FROM USUARIOS";
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

    public HashMap getEntityByUserName(User user) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String sql = "SELECT PASSWORD_USUARIO, ID_USUARIO FROM USUARIOS WHERE NOMBRE_USUARIO = ?";
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
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar credenciales de usuario: {0}",
                    e.getMessage());
        }
        return hashMap;
    }

    /**
     * Consulta los identificadores de usuario y la categoría a la que pertenece
     * un empleado.
     *
     * @param user
     * @return
     */
    public Optional<Employee> getEmployeeByUser(User user) {
        Employee employee = null;
        EmployeeCategory employeeCategory = new EmployeeCategory();
        String sql = "SELECT USUARIOS.ID_USUARIO, ID_CATEGORIA_EMPLEADO "
                + "FROM EMPLEADOS "
                + "INNER JOIN USUARIOS "
                + "ON EMPLEADOS.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "WHERE USUARIOS.NOMBRE_USUARIO = ? AND USUARIOS.ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
            employee = getEmployeeByUserResultSet(preparedStatement, employee, employeeCategory);
        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Error al consultar el perfil de usuario: {0}", e.getMessage());
        }
        return Optional.ofNullable(employee);
    }

    private Employee getEmployeeByUserResultSet(PreparedStatement preparedStatement,
            Employee employee, EmployeeCategory employeeCategory) {
        try (ResultSet resultSet = preparedStatement.getResultSet()) {
            while (resultSet.next()) {
                employeeCategory.setId(resultSet.getInt("ID_CATEGORIA_EMPLEADO"));
                employee = new Employee(
                        new User(resultSet.getInt(ID_USER)),
                        employeeCategory
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener los resultados de la consulta: {0}", e.getMessage());
        }
        return employee;
    }

    /**
     * Consulta el id_usuario del empleado.
     *
     * @param id
     * @return Como puede o no devolver un valor la BD, se envuelve el resultado
     * de idUsuarioObtenido en un Optional.
     */
    @Override
    public Optional<User> getEntityById(Integer id) {
        User user = new User(id);
        String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO, PASSWORD_USUARIO FROM USUARIOS WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt(ID_USER));
                    user.setUserName(resultSet.getString("NOMBRE_USUARIO"));
                    user.setHash(resultSet.getString(PASSWORD_USER));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar el identificador de usuario: {0}", e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    public int updatePassword(User user) {
        int updateCount = 0;
        String sql = "UPDATE USUARIOS SET PASSWORD_USUARIO = ? WHERE ID_USUARIO  = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getHash());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar password existente: {0}", e.getMessage());
        }
        return updateCount;
    }

    /**
     * Actualiza el id_usuario en la tabla empleado.
     *
     * @param idUsuario
     * @param idEmpleado
     */
    public void actualizarIdUsuarioEnTablaEmpleado(String idUsuario, String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET ID_USUARIO = ? WHERE ID_EMPLEADO = ?";
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
     * @param user
     */
    @Override
    public void updateEntityRelationship(User user) {
        String sql = "UPDATE EMPLEADOS SET ID_USUARIO = ? WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "NULL");
            preparedStatement.setInt(2, user.getId());
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
     * @param user
     * @return
     */
    public int delete(User user) {
        int updateCount = 0;
        updateEntityRelationship(user);
        String sql = "DELETE FROM USUARIOS WHERE ID_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar usuario: {0}", e.getMessage());
        }
        return updateCount;
    }

}
