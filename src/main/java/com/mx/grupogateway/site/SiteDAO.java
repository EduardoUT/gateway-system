/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.site;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.site.Site;
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
public class SiteDAO extends ConnectionStatus {

    private static final Logger logger = LoggerConfig.getLogger();

    public SiteDAO(Connection con) {
        super(con);
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
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, site.getSiteId());
            preparedStatement.setString(2, site.getSiteCode());
            preparedStatement.setString(3, site.getSiteName());
            preparedStatement.setString(4, site.getBiddigArea());
            preparedStatement.setInt(5, site.getShipmentNo());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar Site: {0}", e.getMessage());
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
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
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
            logger.log(Level.SEVERE, "Error al consultar Site: {0}", e.getMessage());
        }
        return siteIdList;
    }
}
