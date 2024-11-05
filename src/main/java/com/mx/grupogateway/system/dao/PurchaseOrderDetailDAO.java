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
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Guarda los datos del modelo PurchaseOrderDetail.
     *
     * @param purchaseOrderDetail
     */
    public void guardar(PurchaseOrderDetail purchaseOrderDetail) {
        String sql = "INSERT INTO PURCHASE_ORDER "
                + "(PO_NO, PO_STATUS, ITEM_CODE, ITEM_DESC, REQUESTED_QTY, "
                + "LINE_AMOUNT, PAYMENT_TERMS)"
                + "VALUES(?,?,?,?,?,?,?)";
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

    /**
     * Consulta el purchaseOrderIdentifier acorde al mismo.
     *
     * @param purchaseOrderIdentifier
     * @return
     */
    public List<String> listarPurchaseOrderDetailIdentifiers(String purchaseOrderIdentifier) {
        List<String> purchaseOrderList = new ArrayList<>();
        String sql = "SELECT PO_NO FROM PURCHASE_ORDER WHERE PO_NO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, purchaseOrderIdentifier);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrderDetail purchaseOrder = new PurchaseOrderDetail(resultSet.getString("PO_NO"));
                    purchaseOrderList.add(purchaseOrder.getPoNo());
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(PurchaseOrderDetailDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al consultad los ids de PURCHASE_ORDER: " + e.getMessage());
        }
        return purchaseOrderList;
    }
}
