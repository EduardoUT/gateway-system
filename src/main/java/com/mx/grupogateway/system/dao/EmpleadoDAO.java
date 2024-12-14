/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.LoggerConfig;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoDAO extends AbstractDAO {

    private static final Logger logger = LoggerConfig.getLogger();

    public EmpleadoDAO(Connection con) {
        super(con);
    }

    /**
     * Realiza el guardado en la BD del empleado.
     *
     * @param empleado
     * @return Devuelve el identificador si la inserción se ejecuto
     * correctamente, caso contrario devuelve -1.
     */
    public int guardar(Empleado empleado) {
        int idEmpleado = -1;
        String sql = "INSERT INTO EMPLEADOS "
                + "(NOMBRE, APE_PAT, "
                + "APE_MAT, ID_USUARIO, ID_CATEGORIA_EMPLEADO) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellidoPaterno());
            preparedStatement.setString(3, empleado.getApellidoMaterno());
            preparedStatement.setInt(4, empleado.getUsuario().getIdUsuario());
            preparedStatement.setString(5, empleado.getEmpleadoCategoria().getidCategoria());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    idEmpleado = resultSet.getInt(1);
                    empleado.setIdEmpleado(idEmpleado);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar empleado: {0}", e.getMessage());
        }
        return idEmpleado;
    }

    /**
     * Devuelve un listado con todos los valores de la BD correspondientes a los
     * atributos del objeto de tipo Empleado.
     *
     * @return Lista de tipo Empleado de la BD.
     */
    public List<Empleado> listar() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT ID_EMPLEADO, NOMBRE, APE_PAT, APE_MAT, ID_USUARIO, "
                + "NOMBRE_CATEGORIA, EMPLEADOS.ID_CATEGORIA_EMPLEADO "
                + "FROM CATEGORIA_EMPLEADO "
                + "INNER JOIN EMPLEADOS ON "
                + "CATEGORIA_EMPLEADO.ID_CATEGORIA_EMPLEADO = "
                + "EMPLEADOS.ID_CATEGORIA_EMPLEADO "
                + "WHERE NOMBRE_CATEGORIA = 'Administrador Facturación' "
                + "OR NOMBRE_CATEGORIA = 'Operador Facturación'";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    empleados.add(
                            new Empleado(
                                    resultSet.getInt("ID_EMPLEADO"),
                                    resultSet.getString("NOMBRE"),
                                    resultSet.getString("APE_PAT"),
                                    resultSet.getString("APE_MAT"),
                                    new Usuario(resultSet.getInt("ID_USUARIO")),
                                    new EmpleadoCategoria(
                                            resultSet.getString("ID_CATEGORIA_EMPLEADO"),
                                            resultSet.getString("NOMBRE_CATEGORIA")
                                    )
                            )
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar empleado: {0}", e.getMessage());
        }
        return empleados;
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
     * @return Cantidad de registros afectados.
     */
    public int actualizar(String idEmpleado, String nombre, String apellidoP,
            String apellidoM, String idCategoria) {
        int updateCount = 0;
        String sql = "UPDATE EMPLEADOS "
                + "SET NOMBRE = ?, APE_PAT = ?, APE_MAT = ?, "
                + "ID_CATEGORIA_EMPLEADO = ? "
                + "WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidoP);
            preparedStatement.setString(3, apellidoM);
            preparedStatement.setString(4, idCategoria);
            preparedStatement.setString(5, idEmpleado);
            preparedStatement.execute();
            updateCount = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar datos de empleado: {0}", e.getMessage());
        }
        return updateCount;
    }

    /**
     * Realiza la eliminación del registro en la BD, acorde al empleadoId.
     *
     * @param idEmpleado
     * @return Código de error
     */
    public int eliminar(String idEmpleado) {
        int registrosAfectados = 0;
        String sql = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, idEmpleado);
            preparedStatement.execute();
            registrosAfectados = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar empleado: {0}", e.getMessage());
        }
        return registrosAfectados;
    }
}
