/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.site;

import static com.mx.grupogateway.GlobalLogger.*;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.crud.CreateEntityDAO;
import com.mx.grupogateway.crud.GetAllById;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduar
 */
public class SiteImpl extends ConnectionStatus implements CreateEntityDAO<Site>,
        GetAllById<Long, Long> {
    
    public SiteImpl(Connection con) {
        super(con);
    }

    /**
     * Guarda los datos del modelo Site.
     *
     * @param site
     */
    @Override
    public void create(Site site) {
        String sql = "INSERT INTO SITE "
                + "(ID_SITE, SITE_CODE, SITE_NAME, BIDDING_AREA, SHIPMENT_NO) "
                + "VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, site.getId());
            preparedStatement.setString(2, site.getSiteCode());
            preparedStatement.setString(3, site.getSiteName());
            preparedStatement.setString(4, site.getBiddigArea());
            preparedStatement.setInt(5, site.getShipmentNo());
            preparedStatement.execute();
        } catch (SQLException e) {
            registerLoggerSevere("Error al guardar Site: {0}", e);
        }
    }

    /**
     * Consulta los identificadores de Site acorde al mismo.
     *
     * @return
     */
    @Override
    public List<Long> getAllById(Long id) {
        List<Long> siteIdentifiers = new ArrayList<>();
        String sql = "SELECT ID_SITE FROM SITE WHERE ID_SITE = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Site siteId = new Site();
                    siteId.setId(resultSet.getLong("ID_SITE"));
                    siteIdentifiers.add(siteId.getId());
                }
            }
        } catch (SQLException e) {
            registerLoggerSevere("Error al consultar Site: {0}", e);
        }
        return siteIdentifiers;
    }
}
