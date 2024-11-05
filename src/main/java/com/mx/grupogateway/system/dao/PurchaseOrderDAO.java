/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * Guarda los datos contenidos en el modelo PurchaseOrder.
     *
     * @param purchaseOrder
     */
    public void guardar(PurchaseOrder purchaseOrder) {
        String sql = "INSERT INTO PURCHASE_HAS_ORDER "
                + "(PO_NO, ID_PROJECT, PO_LINE_NO, DUE_QTY, BILLED_QTY, UNIT, "
                + "UNIT_PRICE) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, purchaseOrder.getPurchaseOrderDetail().getPoNo());
            preparedStatement.setLong(2, purchaseOrder.getProject().getProjectId());
            preparedStatement.setInt(3, purchaseOrder.getPoLineNo());
            preparedStatement.setString(4, purchaseOrder.getDueQty());
            preparedStatement.setBigDecimal(5, purchaseOrder.getBilledQty());
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

    /**
     * Consulta los identificadores que conforman la tabla compuesta.
     *
     * @param purchaseOrderIdentifier
     * @param purchaseOrderProjectId
     * @return
     */
    public Map<Long, String> listarPurchaseOrderIdentifiers(
            String purchaseOrderIdentifier, Long purchaseOrderProjectId) {
        Map<Long, String> purchaseOrderIdentifiers = new HashMap<>();
        String sql = "SELECT PO_NO, ID_PROJECT FROM PURCHASE_HAS_ORDER "
                + "WHERE PO_NO = ? AND ID_PROJECT = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, purchaseOrderIdentifier);
            preparedStatement.setLong(2, purchaseOrderProjectId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setPurchaseOrderDetail(new PurchaseOrderDetail(resultSet.getString("PO_NO")));
                    purchaseOrder.setProject(new Project(resultSet.getLong("ID_PROJECT")));
                    purchaseOrderIdentifiers.put(
                            purchaseOrder.getProject().getProjectId(),
                            purchaseOrder.getPurchaseOrderDetail().getPoNo()
                    );
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(PurchaseOrderDetailDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al consultad los ids de PURCHASE_ORDER: " + e.getMessage());
        }
        return purchaseOrderIdentifiers;
    }

    /**
     * Consulta los identificadores de project acorde al purchaseOrderIdentifier
     *
     * @param purchaseOrder
     * @return
     */
    public List<Long> listarPurchaseOrderProjectIdentifiers(PurchaseOrder purchaseOrder) {
        List<Long> purchaseOrderProjectIdentifiers = new ArrayList<>();
        String sql = "SELECT PROJECT_ID FROM PURCHASE_HAS_ORDER WHERE PO_NO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, purchaseOrder.getPurchaseOrderDetail().getPoNo());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrder purchaseOrderProject = new PurchaseOrder();
                    purchaseOrderProject.setProject(new Project(resultSet.getLong("ID_PROJECT")));
                    purchaseOrderProjectIdentifiers.add(purchaseOrderProject.getProject().getProjectId());
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(PurchaseOrderDetailDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al consultad los ids de PURCHASE_ORDER: " + e.getMessage());
        }
        return purchaseOrderProjectIdentifiers;
    }

}
