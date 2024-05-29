/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Proyecto;
import com.mx.grupogateway.system.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author eduar
 */
public class ProyectoDAO {

    private final Connection con;

    public ProyectoDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Proyecto proyecto) {
        String sql = "INSERT INTO PROJECTS "
                + "(ID_PROJECT, PROJECT_CODE, PROJECT_NAME, CUSTOMER, "
                + "PO_STATUS, PO_NO, PO_LINE_NO, SHIPMENT_NO, "
                + "SITE_CODE, SITE_NAME, ITEM_CODE, ITEM_DESC, "
                + "REQUESTED_QTY, DUE_QTY, BILLED_QTY, "
                + "UNIT_PRICE, LINE_AMOUNT, UNIT, PAYMENT_TERMS, "
                + "CATEGORY, BIDDING_AREA, PUBLISH_DATE) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setLong(1, proyecto.getIdProyecto());
            preparedStatement.setString(2, proyecto.getProjectCode());
            preparedStatement.setString(3, proyecto.getProjectName());
            preparedStatement.setString(4, proyecto.getCustomer());
            preparedStatement.setString(5, proyecto.getPoStatus());
            preparedStatement.setString(6, proyecto.getPoNo());
            preparedStatement.setInt(7, proyecto.getPoLineNo());
            preparedStatement.setInt(8, proyecto.getShipmentNo());
            preparedStatement.setString(9, proyecto.getSiteCode());
            preparedStatement.setString(10, proyecto.getSiteName());
            preparedStatement.setInt(11, proyecto.getItemCode());
            preparedStatement.setString(12, proyecto.getItemDesc());
            preparedStatement.setDouble(13, proyecto.getRequestedQty());
            preparedStatement.setBigDecimal(14, proyecto.getDueQty());
            preparedStatement.setBigDecimal(15, proyecto.getBilledQty());
            preparedStatement.setBigDecimal(16, proyecto.getUnitPrice());
            preparedStatement.setBigDecimal(17, proyecto.getLineAmount());
            preparedStatement.setString(18, proyecto.getUnit());
            preparedStatement.setString(19, proyecto.getPaymentTerms());
            preparedStatement.setString(20, proyecto.getCategory());
            preparedStatement.setString(21, proyecto.getBiddingArea());
            preparedStatement.setDate(22, proyecto.getPublishDate());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    System.out.println(String.format("Fue guardado "
                            + "el proyecto %s", proyecto));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proyecto> listar() {
        List<Proyecto> resultado = new ArrayList<>();
        String sql = "SELECT * FROM PROJECTS";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    Proyecto fila = new Proyecto(
                            resultSet.getLong("ID_PROJECT"),
                            resultSet.getString("PROJECT_CODE"),
                            resultSet.getString("PROJECT_NAME"),
                            resultSet.getString("CUSTOMER"),
                            resultSet.getString("PO_STATUS"),
                            resultSet.getString("PO_NO"),
                            resultSet.getInt("PO_LINE_NO"),
                            resultSet.getInt("SHIPMENT_NO"),
                            resultSet.getString("SITE_CODE"),
                            resultSet.getString("SITE_NAME"),
                            resultSet.getInt("ITEM_CODE"),
                            resultSet.getString("ITEM_DESC"),
                            resultSet.getDouble("REQUESTED_QTY"),
                            resultSet.getBigDecimal("DUE_QTY"),
                            resultSet.getBigDecimal("BILLED_QTY"),
                            resultSet.getBigDecimal("UNIT_PRICE"),
                            resultSet.getBigDecimal("LINE_AMOUNT"),
                            resultSet.getString("UNIT"),
                            resultSet.getString("PAYMENT_TERMS"),
                            resultSet.getString("CATEGORY"),
                            resultSet.getString("BIDDING_AREA"),
                            resultSet.getDate("PUBLISH_DATE")
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
