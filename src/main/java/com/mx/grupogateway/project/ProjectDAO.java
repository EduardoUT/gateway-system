/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.project;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.site.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class ProjectDAO extends ConnectionStatus {

    private static final Logger logger = LoggerConfig.getLogger();

    public ProjectDAO(Connection con) {
        super(con);
    }

    /**
     * Guarda un objeto Proyecto en la Base de Datos.
     *
     * @param project
     */
    public void guardar(Project project) {
        String sql = "INSERT INTO PROJECT "
                + "(ID_PROJECT, ID_SITE, PROJECT_CODE, PROJECT_NAME, CUSTOMER, "
                + "CATEGORY, PUBLISH_DATE) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, project.getProjectId());
            preparedStatement.setLong(2, project.getSite().getSiteId());
            preparedStatement.setString(3, project.getProjectCode());
            preparedStatement.setString(4, project.getProjectName());
            preparedStatement.setString(5, project.getCustomer());
            preparedStatement.setString(6, project.getCategory());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(project.getPublishDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar proyecto: {0}", e.getMessage());
        }
    }

    /**
     * Lista con la información SQL en objetos de tipo Project.
     *
     * @return
     */
    public List<Project> listar() {
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
                    site.setSiteId(resultSet.getLong("ID_SITE"));
                    site.setSiteCode(resultSet.getString("SITE_CODE"));
                    site.setSiteName(resultSet.getString("SITE_NAME"));
                    site.setBiddigArea(resultSet.getString("BIDDING_AREA"));
                    site.setShipmentNo(resultSet.getInt("SHIPMENT_NO"));

                    Project project = new Project();
                    project.setProjectId(resultSet.getLong("ID_PROJECT"));
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
            logger.log(Level.SEVERE, "Error al consultar proyecto: {0}", e.getMessage());
        }
        return projects;
    }

    /**
     * Consulta los identificadores de project.
     *
     * @param projectIdentifier
     * @return
     */
    public List<Long> listarProjectId(Long projectIdentifier) {
        List<Long> projectIdList = new ArrayList<>();
        String sql = "SELECT ID_PROJECT FROM PROJECT WHERE ID_PROJECT = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, projectIdentifier);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Project projectId = new Project(resultSet.getLong("ID_PROJECT"));
                    projectIdList.add(projectId.getProjectId());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar identificador de proyecto: {0}",
                    e.getMessage());
        }

        return projectIdList;
    }
}
