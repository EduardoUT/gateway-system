/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.project;

import static com.mx.grupogateway.GlobalLogger.*;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.crud.CreateEntityDAO;
import com.mx.grupogateway.crud.GetAllById;
import com.mx.grupogateway.crud.GetAllDAO;
import com.mx.grupogateway.site.Site;
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
public class ProjectImpl extends ConnectionStatus
        implements CreateEntityDAO<Project>, GetAllDAO<Project>, GetAllById<Long, Long> {

    public ProjectImpl(Connection con) {
        super(con);
    }

    /**
     * Guarda un objeto Proyecto en la Base de Datos.
     *
     * @param project
     */
    @Override
    public void create(Project project) {
        String sql = "INSERT INTO PROJECT "
                + "(ID_PROJECT, ID_SITE, PROJECT_CODE, PROJECT_NAME, CUSTOMER, "
                + "CATEGORY, PUBLISH_DATE) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, project.getId());
            preparedStatement.setLong(2, project.getSite().getId());
            preparedStatement.setString(3, project.getProjectCode());
            preparedStatement.setString(4, project.getProjectName());
            preparedStatement.setString(5, project.getCustomer());
            preparedStatement.setString(6, project.getCategory());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(project.getPublishDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            registerLoggerSevere("Error al guardar proyecto: {0}", e);
        }
    }

    /**
     * Lista con la información SQL en objetos de tipo Project.
     *
     * @return
     */
    @Override
    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT PROJECT.ID_PROJECT, PROJECT.PROJECT_CODE, "
                + "PROJECT.PROJECT_NAME, PROJECT.CUSTOMER, "
                + "PROJECT.CATEGORY, PROJECT.PUBLISH_DATE "
                + "SITE.SITE_ID, SITE.SITE_CODE, SITE.SITE_NAME, "
                + "SITE.BIDDING_AREA SITE.SHIPMENT_NO "
                + "FROM PROJECT "
                + "INNER JOIN SITE ON PROJECT.ID_SITE = SITE.ID_SITE";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Site site = new Site();
                    site.setId(resultSet.getLong("ID_SITE"));
                    site.setSiteCode(resultSet.getString("SITE_CODE"));
                    site.setSiteName(resultSet.getString("SITE_NAME"));
                    site.setBiddigArea(resultSet.getString("BIDDING_AREA"));
                    site.setShipmentNo(resultSet.getInt("SHIPMENT_NO"));

                    Project project = new Project();
                    project.setId(resultSet.getLong("ID_PROJECT"));
                    project.setSite(site);
                    project.setProjectCode(resultSet.getString("PROJECT_CODE"));
                    project.setProjectName(resultSet.getString("PROJECT_NAME"));
                    project.setCustomer(resultSet.getString("CUSTOMER"));
                    project.setCategory(resultSet.getString("CATEGORY"));
                    project.setPublishDate(resultSet.getTimestamp("PUBLISH_DATE")
                            .toLocalDateTime());
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            registerLoggerSevere("Error al consultar proyecto: {0}", e);
        }
        return projects;
    }

    /**
     * Consulta los identificadores de project.
     *
     * @param id
     * @return
     */
    @Override
    public List<Long> getAllById(Long id) {
        List<Long> projectIdentifiers = new ArrayList<>();
        String sql = "SELECT ID_PROJECT FROM PROJECT WHERE ID_PROJECT = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Project projectId = new Project(resultSet.getLong("ID_PROJECT"));
                    projectIdentifiers.add(projectId.getId());
                }
            }
        } catch (SQLException e) {
            registerLoggerSevere("Error al consultar identificador de proyecto: {0}", e);
        }
        return projectIdentifiers;
    }
}
