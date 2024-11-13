/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.LoggerConfig;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.EmpleadoCategoria;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.util.SecurityPassword;
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
public class UsuarioDAO extends AbstractDAO {

    private static final String ID_USUARIO = "ID_USUARIO";
    private static final String PASSWORD_USUARIO = "PASSWORD_USUARIO";
    private static final Logger logger = LoggerConfig.getLogger();

    public UsuarioDAO(Connection con) {
        super(con);
    }

    /**
     * Se invoca en EmpleadoDAO, se ejecuta antes de insertar un nuevo empleado
     * en la BD, a fin de poder asegurar la integridad y relación con la tabla
     * empleados.
     *
     * @param usuario
     * @return Identificador del usuario generado por la Base de datos.
     */
    public int guardar(Usuario usuario) {
        int idUsuario = -1;
        String sql = "INSERT INTO USUARIOS (NOMBRE_USUARIO, " + PASSWORD_USUARIO + ")"
                + "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    idUsuario = resultSet.getInt(1);
                    usuario.setIdUsuario(idUsuario);
                }

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar usuario: {0}", e.getMessage());
        }
        return idUsuario;
    }

    /**
     *
     * @return List de tipo Usuario de la BD.
     */
    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        String sql = "SELECT " + ID_USUARIO + ", NOMBRE_USUARIO FROM USUARIOS";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Usuario fila = new Usuario(
                            resultSet.getInt(ID_USUARIO),
                            resultSet.getString("NOMBRE_USUARIO")
                    );
                    resultado.add(fila);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar usuarios: {0}", e.getMessage());
        }
        return resultado;
    }

    public boolean esPerfilValido(Usuario usuario) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String sql = "SELECT " + PASSWORD_USUARIO + ", " + ID_USUARIO + " FROM USUARIOS WHERE NOMBRE_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    hashMap.put(
                            resultSet.getString(PASSWORD_USUARIO),
                            resultSet.getInt(ID_USUARIO)
                    );
                }
                for (Map.Entry<String, Integer> registro : hashMap.entrySet()) {
                    String clave = registro.getKey();
                    if (SecurityPassword.assertData(clave, usuario.getPassword())) {
                        Integer valor = registro.getValue();
                        usuario.setIdUsuario(valor);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar credenciales de usuario: {0}",
                    e.getMessage());
        }
        return (usuario.getIdUsuario() != 0 || usuario.getIdUsuario() != null);
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
        String sql = "SELECT " + PASSWORD_USUARIO + " FROM USUARIOS WHERE " + ID_USUARIO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    passwordNula = resultSet.getString(PASSWORD_USUARIO);
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
     * @param usuario
     * @return
     */
    public boolean esPasswordValida(Usuario usuario) {
        Usuario usuarioPassword = new Usuario();
        String sql = "SELECT " + PASSWORD_USUARIO + " FROM USUARIOS WHERE " + ID_USUARIO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, usuario.getIdUsuario());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    usuarioPassword.setPassword(resultSet.getString(PASSWORD_USUARIO), false);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al validar password: {0}", e.getMessage());
        }
        if (usuarioPassword.getPassword() != null || !usuarioPassword.getPassword().isEmpty()) {
            return SecurityPassword.assertData(
                    usuarioPassword.getPassword(),
                    usuario.getPassword()
            );
        }
        return false;
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
        if (esPerfilValido(usuario)) {
            String sql = "SELECT USUARIOS." + ID_USUARIO + ", ID_CATEGORIA_EMPLEADO "
                    + "FROM EMPLEADOS "
                    + "INNER JOIN USUARIOS "
                    + "ON EMPLEADOS." + ID_USUARIO + " = USUARIOS." + ID_USUARIO + " "
                    + "WHERE USUARIOS.NOMBRE_USUARIO = ? AND USUARIOS." + ID_USUARIO + " = ?";
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getNombreUsuario());
                preparedStatement.setInt(2, usuario.getIdUsuario());
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        empleadoCategoria.setIdCategoria(resultSet.getString("ID_CATEGORIA_EMPLEADO"));
                        empleado = new Empleado(
                                new Usuario(resultSet.getInt(ID_USUARIO)),
                                empleadoCategoria
                        );
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al consultar el perfil de usuario: {0}", e.getMessage());
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
    public Integer consultarIdUsuario(Integer idUsuario) {
        Integer idUsuarioObtenido = -1;
        String sql = "SELECT " + ID_USUARIO + " FROM USUARIOS WHERE " + ID_USUARIO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    idUsuarioObtenido = resultSet.getInt(ID_USUARIO);
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
     * @param usuario
     */
    public void actualizarPasswordNula(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET " + PASSWORD_USUARIO + " = ? WHERE " + ID_USUARIO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getPassword());
            preparedStatement.setInt(2, usuario.getIdUsuario());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar password nula: {0}", e.getMessage());
        }
    }

    public void actualizarPassword(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET " + PASSWORD_USUARIO + " = ? WHERE " + ID_USUARIO + " = ? "
                + "AND NOMBRE_USUARIO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getPassword());
            preparedStatement.setInt(2, usuario.getIdUsuario());
            preparedStatement.setString(3, usuario.getNombreUsuario());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar password existente: {0}", e.getMessage());
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado.
     *
     * @param idUsuario
     * @param idEmpleado
     */
    public void actualizarIdUsuarioEnTablaEmpleado(String idUsuario, String idEmpleado) {
        String sql = "UPDATE EMPLEADOS SET " + ID_USUARIO + " = ? WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.setString(2, idEmpleado);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e.getMessage());
        }
    }

    /**
     * Actualiza el id_usuario en la tabla empleado asociada a la tabla de
     * usuario a fin de poder eliminarlo.
     *
     * @param idUsuario
     */
    private void actualizarRelacionEmpleadoUsuario(Integer idUsuario) {
        String sql = "UPDATE EMPLEADOS SET " + ID_USUARIO + " = ? WHERE " + ID_USUARIO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "NULL");
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e.getMessage());
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
        String sql = "DELETE FROM USUARIOS WHERE " + ID_USUARIO + " = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e.getMessage());
        }
        return updateCount;
    }

}
