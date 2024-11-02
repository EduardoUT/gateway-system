/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.PurchaseOrder;
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
public class PurchaseOrderDAO {

    private final Connection con;

    public PurchaseOrderDAO(Connection con) {
        this.con = con;
    }

    public void guardar(PurchaseOrder purchaseOrder) {
        String sql = "INSERT INTO PURCHASE_HAS_ORDER "
                + "(PO_NO, ID_PROJECT, PO_LINE_NO, DUE_QTY, BILLED_QTY, UNIT, "
                + "UNIT_PRICE, SHIPMENT_NO) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, purchaseOrder.getPurchaseOrderDetail().getPoNo());
            preparedStatement.setLong(2, purchaseOrder.getProject().getIdProyecto());
            preparedStatement.setInt(3, purchaseOrder.getPoLineNo());
            preparedStatement.setString(4, purchaseOrder.getDueQty());
            preparedStatement.setString(5, purchaseOrder.getBilledQty());
            preparedStatement.setString(6, purchaseOrder.getUnit());
            preparedStatement.setBigDecimal(7, purchaseOrder.getUnitPrice());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(String.format("Fue guardado Purchase Order %s", purchaseOrder));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al guardar Purchase Order: " + e.getMessage());
        }
    }

}
