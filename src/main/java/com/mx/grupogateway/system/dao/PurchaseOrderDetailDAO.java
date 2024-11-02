/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
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
public class PurchaseOrderDetailDAO {

    private final Connection con;

    public PurchaseOrderDetailDAO(Connection con) {
        this.con = con;
    }

    public void guardar(PurchaseOrderDetail purchaseOrderDetail) {
        String sql = "INSERT INTO PURCHASE_ORDER "
                + "(PO_NO, PO_STATUS, ITEM_CODE, ITEM_DESC, REQUESTED_QTY, "
                + "LINE_AMOUNT, PAYMENT_TERMS, BIDDING_AREA)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, purchaseOrderDetail.getPoNo());
            preparedStatement.setString(2, purchaseOrderDetail.getPoStatus());
            preparedStatement.setLong(3, purchaseOrderDetail.getItemCode());
            preparedStatement.setString(4, purchaseOrderDetail.getItemDesc());
            preparedStatement.setString(5, purchaseOrderDetail.getRequestedQty());
            preparedStatement.setBigDecimal(6, purchaseOrderDetail.getLineAmount());
            preparedStatement.setString(7, purchaseOrderDetail.getPaymentTerms());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(String.format("Fue guardado "
                            + "Purchase Order Detail %s", purchaseOrderDetail));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al guardar Purchase Order Detail: " + e.getMessage());
        }
    }
}
