/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.EmpleadoCargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmpleadoCargoDAO {

    private final Connection con;

    public EmpleadoCargoDAO(Connection con) {
        this.con = con;
    }

    /**
     *
     * @return List de tipo EmpleadoCargo de la BD.
     */
    public List<EmpleadoCargo> listar() {
        List<EmpleadoCargo> resultado = new ArrayList<>();
        String sql = "SELECT ID_CATEGORIA, NOMBRE_CATEGORIA "
                + "FROM CATEGORIA_EMPLEADO";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try ( ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    EmpleadoCargo fila = new EmpleadoCargo(
                            resultSet.getInt("ID_CATEGORIA"),
                            resultSet.getString("NOMBRE_CATEGORIA")
                    );
                    resultado.add(fila);
                }
                return resultado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
