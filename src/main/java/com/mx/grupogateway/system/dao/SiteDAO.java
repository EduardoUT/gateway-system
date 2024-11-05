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
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Guarda los datos del modelo Site.
     *
     * @param site
     */
    public void guardar(Site site) {
        String sql = "INSERT INTO SITE "
                + "(ID_SITE, SITE_CODE, SITE_NAME, BIDDING_AREA, SHIPMENT_NO) "
                + "VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, site.getSiteId());
            preparedStatement.setString(2, site.getSiteCode());
            preparedStatement.setString(3, site.getSiteName());
            preparedStatement.setString(4, site.getBiddigArea());
            preparedStatement.setInt(5, site.getShipmentNo());
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

    /**
     * Consulta los identificadores de Site acorde al mismo.
     *
     * @param idSite
     * @return
     */
    public List<Long> listarSiteIdentifiers(Long idSite) {
        List<Long> siteIdList = new ArrayList<>();
        String sql = "SELECT ID_SITE FROM SITE WHERE ID_SITE = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setLong(1, idSite);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Site siteId = new Site();
                    siteId.setSiteId(resultSet.getLong("ID_SITE"));
                    siteIdList.add(siteId.getSiteId());
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al consultar los ids de SITE: " + e.getMessage());
        }
        return siteIdList;
    }
}
