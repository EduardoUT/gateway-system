/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class SiteDAO {

    private final Connection con;

    public SiteDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Site site) {
        String sql = "INSERT INTO SITE "
                + "(ID_SITE, ID_PROJECT, SITE_CODE, SITE_NAME) "
                + "VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, site.getSiteId());
            preparedStatement.setLong(2, site.getProject().getIdProyecto());
            preparedStatement.setString(3, site.getSiteCode());
            preparedStatement.setString(4, site.getSiteName());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(String.format("Fue guardado el site %s", site));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al guardar el Site: " + e.getMessage());
        }
    }
}
