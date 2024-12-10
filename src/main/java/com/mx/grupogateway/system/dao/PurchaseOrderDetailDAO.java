/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.LoggerConfig;
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
public class PurchaseOrderDetailDAO extends AbstractDAO {

    private static final Logger logger = LoggerConfig.getLogger();

    public PurchaseOrderDetailDAO(Connection con) {
        super(con);
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
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, purchaseOrderDetail.getPurchaseOrderDetailIdentifier());
            preparedStatement.setString(2, purchaseOrderDetail.getPoStatus());
            preparedStatement.setLong(3, purchaseOrderDetail.getItemCode());
            preparedStatement.setString(4, purchaseOrderDetail.getItemDesc());
            preparedStatement.setBigDecimal(5, purchaseOrderDetail.getRequestedQty());
            preparedStatement.setBigDecimal(6, purchaseOrderDetail.getLineAmount());
            preparedStatement.setString(7, purchaseOrderDetail.getPaymentTerms());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar PurchaseOrder: {0}", e.getMessage());
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
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, purchaseOrderIdentifier);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrderDetail purchaseOrder = new PurchaseOrderDetail(resultSet.getString("PO_NO"));
                    purchaseOrderList.add(purchaseOrder.getPurchaseOrderDetailIdentifier());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar PurchaseOrder: {0}", e.getMessage());
        }
        return purchaseOrderList;
    }

    /**
     * Actualiza el PO_STATUS correspondiente al purchaseOrderIdentifier
     * proporcionado.
     *
     * @param purchaseOrderDetail
     */
    public void actualizarPurchaseOrderDetailStatus(
            PurchaseOrderDetail purchaseOrderDetail) {
        String sql = "UPDATE PURCHASE_ORDER SET PO_STATUS = ? WHERE PO_NO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, purchaseOrderDetail.getPoStatus());
            preparedStatement.setString(2, purchaseOrderDetail.getPurchaseOrderDetailIdentifier());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar PurchaseOrder: {0}", e.getMessage());
        }
    }
}
