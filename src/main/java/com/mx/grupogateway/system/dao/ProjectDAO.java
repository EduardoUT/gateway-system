/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduar
 */
public class ProjectDAO {

    private final Connection con;

    public ProjectDAO(Connection con) {
        this.con = con;
    }

    /**
     * Guarda un objeto Proyecto en la Base de Datos.
     *
     * @param proyecto
     */
    public void guardar(Project proyecto) {
        String sql = "INSERT INTO PROJECT "
                + "(ID_PROJECT, PROJECT_CODE, PROJECT_NAME, CUSTOMER, "
                + "CATEGORY, PUBLISH_DATE) "
                + "VALUES(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, proyecto.getIdProyecto());
            preparedStatement.setString(2, proyecto.getProjectCode());
            preparedStatement.setString(3, proyecto.getProjectName());
            preparedStatement.setString(4, proyecto.getCustomer());
            preparedStatement.setString(5, proyecto.getCategory());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(proyecto.getPublishDate()));
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(String.format("Fue guardado "
                            + "el proyecto %s", proyecto));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lista con objetos de tipo Proyecto.
     *
     * @return
     */
    public List<Project> listar() {
        List<Project> resultado = new ArrayList<>();
        String sql = "SELECT * FROM PROJECT";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    resultado.add(new Project(
                            resultSet.getLong("ID_PROJECT"),
                            resultSet.getString("PROJECT_CODE"),
                            resultSet.getString("PROJECT_NAME"),
                            resultSet.getString("CUSTOMER"),
                            resultSet.getString("CATEGORY"),
                            resultSet.getTimestamp("PUBLISH_DATE").toLocalDateTime())
                    );
                }
                return resultado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
