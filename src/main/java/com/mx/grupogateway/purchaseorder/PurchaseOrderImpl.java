/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.purchaseorder;

import com.mx.grupogateway.config.LoggerConfig;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.site.Site;
import com.mx.grupogateway.config.ConnectionStatus;
import com.mx.grupogateway.crud.CreateEntityDAO;
import com.mx.grupogateway.crud.GetAllById;
import com.mx.grupogateway.crud.GetAllDAO;
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
public class PurchaseOrderImpl extends ConnectionStatus
        implements CreateEntityDAO<PurchaseOrder>, GetAllDAO<PurchaseOrder>,
        GetAllById<Long, String> {

    private static final Logger logger = LoggerConfig.getLogger();
    private static final String ID_PROJECT = "ID_PROJECT";

    public PurchaseOrderImpl(Connection con) {
        super(con);
    }

    /**
     * Guarda los datos contenidos en el modelo PurchaseOrder.
     *
     * @param purchaseOrder
     */
    @Override
    public void create(PurchaseOrder purchaseOrder) {
        String sql = "INSERT INTO PURCHASE_HAS_ORDER "
                + "(PO_NO," + ID_PROJECT + ", PO_LINE_NO, DUE_QTY, BILLED_QTY, UNIT, "
                + "UNIT_PRICE) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, purchaseOrder.getPurchaseOrderDetail().getId());
            preparedStatement.setLong(2, purchaseOrder.getProject().getId());
            preparedStatement.setInt(3, purchaseOrder.getPoLineNo());
            preparedStatement.setBigDecimal(4, purchaseOrder.getDueQty());
            preparedStatement.setBigDecimal(5, purchaseOrder.getBilledQty());
            preparedStatement.setString(6, purchaseOrder.getUnit());
            preparedStatement.setBigDecimal(7, purchaseOrder.getUnitPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar PurchasOrder: {0}", e.getMessage());
        }
    }

    /**
     * Retorna un listado de tipo PurchaseOrder de los proyectos cuyo status sea
     * NEW.
     *
     * @return
     */
    @Override
    public List<PurchaseOrder> getAll() {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        String sql = "SELECT PROJECT.ID_PROJECT, PROJECT.PROJECT_CODE, "
                + "PROJECT.PROJECT_NAME, PROJECT.CUSTOMER, "
                + "PURCHASE_ORDER.PO_STATUS, PURCHASE_HAS_ORDER.PO_NO, "
                + "PURCHASE_HAS_ORDER.PO_LINE_NO, SITE.SHIPMENT_NO, "
                + "SITE.SITE_CODE, SITE.SITE_NAME, PURCHASE_ORDER.ITEM_CODE, "
                + "PURCHASE_ORDER.ITEM_DESC, PURCHASE_ORDER.REQUESTED_QTY, "
                + "PURCHASE_HAS_ORDER.DUE_QTY, PURCHASE_HAS_ORDER.BILLED_QTY, "
                + "PURCHASE_HAS_ORDER.UNIT_PRICE, PURCHASE_ORDER.LINE_AMOUNT, "
                + "PURCHASE_HAS_ORDER.UNIT, PURCHASE_ORDER.PAYMENT_TERMS, "
                + "PROJECT.CATEGORY, SITE.BIDDING_AREA, PROJECT.PUBLISH_DATE "
                + "FROM PURCHASE_HAS_ORDER "
                + "INNER JOIN PROJECT ON PROJECT.ID_PROJECT = "
                + "PURCHASE_HAS_ORDER.ID_PROJECT "
                + "INNER JOIN PURCHASE_ORDER ON PURCHASE_HAS_ORDER.PO_NO = "
                + "PURCHASE_ORDER.PO_NO "
                + "INNER JOIN SITE ON PROJECT.ID_SITE = SITE.ID_SITE "
                + "WHERE PURCHASE_ORDER.PO_STATUS = 'NEW'";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Site site = new Site();
                    site.setSiteCode(resultSet.getString("SITE_CODE"));
                    site.setSiteName(resultSet.getString("SITE_NAME"));
                    site.setBiddigArea(resultSet.getString("BIDDING_AREA"));
                    site.setShipmentNo(resultSet.getInt("SHIPMENT_NO"));

                    Project project = new Project();
                    project.setId(resultSet.getLong(ID_PROJECT));
                    project.setSite(site);
                    project.setProjectCode(resultSet.getString("PROJECT_CODE"));
                    project.setProjectName(resultSet.getString("PROJECT_NAME"));
                    project.setCustomer(resultSet.getString("CUSTOMER"));
                    project.setCategory(resultSet.getString("CATEGORY"));
                    project.setPublishDate(resultSet.getTimestamp("PUBLISH_DATE")
                            .toLocalDateTime());

                    PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                    purchaseOrderDetail.setId(resultSet.getString("PO_NO"));
                    purchaseOrderDetail.setPoStatus(resultSet.getString("PO_STATUS"));
                    purchaseOrderDetail.setItemCode(resultSet.getLong("ITEM_CODE"));
                    purchaseOrderDetail.setItemDesc(resultSet.getString("ITEM_DESC"));
                    purchaseOrderDetail.setRequestedQty(resultSet.getBigDecimal("REQUESTED_QTY"));
                    purchaseOrderDetail.setLineAmount(resultSet.getBigDecimal("LINE_AMOUNT"));
                    purchaseOrderDetail.setPaymentTerms(resultSet.getString("PAYMENT_TERMS"));

                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setPurchaseOrderDetail(purchaseOrderDetail);
                    purchaseOrder.setProject(project);
                    purchaseOrder.setPoLineNo(resultSet.getInt("PO_LINE_NO"));
                    purchaseOrder.setDueQty(resultSet.getBigDecimal("DUE_QTY"));
                    purchaseOrder.setBilledQty(resultSet.getBigDecimal("BILLED_QTY"));
                    purchaseOrder.setUnit(resultSet.getString("UNIT"));
                    purchaseOrder.setUnitPrice(resultSet.getBigDecimal("UNIT_PRICE"));

                    purchaseOrders.add(purchaseOrder);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar PurchasOrder compuesta: {0}", e.getMessage());
        }
        return purchaseOrders;
    }

    /**
     * Consulta los identificadores de project acorde al purchaseOrderIdentifier
     *
     * @return
     */
    @Override
    public List<Long> getAllById(String id) {
        List<Long> purchaseOrderProjectIdentifiers = new ArrayList<>();
        String sql = "SELECT ID_PROJECT FROM PURCHASE_HAS_ORDER WHERE PO_NO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrder purchaseOrderProject = new PurchaseOrder();
                    purchaseOrderProject.setProject(new Project(resultSet.getLong(ID_PROJECT)));
                    purchaseOrderProjectIdentifiers.add(purchaseOrderProject.getProject().getId());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar PurchasOrder: {0}", e.getMessage());
        }
        return purchaseOrderProjectIdentifiers;
    }

    /**
     * Consulta los identificadores que conforman la tabla compuesta.
     *
     * @param purchaseOrderIdentifier Identificador del detalle de la orden de
     * compra.
     * @param purchaseOrderProjectId Identificador del proyecto.
     * @return
     */
    public Map<Long, String> getAllPurchaseOrderIdentifiers(
            String purchaseOrderIdentifier, Long purchaseOrderProjectId) {
        Map<Long, String> purchaseOrderIdentifiers = new HashMap<>();
        String sql = "SELECT PO_NO, ID_PROJECT FROM PURCHASE_HAS_ORDER "
                + "WHERE PO_NO = ? AND ID_PROJECT = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, purchaseOrderIdentifier);
            preparedStatement.setLong(2, purchaseOrderProjectId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setPurchaseOrderDetail(
                            new PurchaseOrderDetail(resultSet.getString("PO_NO"))
                    );
                    purchaseOrder.setProject(
                            new Project(resultSet.getLong(ID_PROJECT))
                    );
                    purchaseOrderIdentifiers.put(
                            purchaseOrder.getProject().getId(),
                            purchaseOrder.getPurchaseOrderDetail().getId()
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar PurchasOrder: {0}", e.getMessage());
        }
        return purchaseOrderIdentifiers;
    }
}
