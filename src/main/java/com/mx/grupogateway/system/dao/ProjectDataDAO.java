/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.ProjectData;
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
public class ProjectDataDAO {

    private final Connection con;

    public ProjectDataDAO(Connection con) {
        this.con = con;
    }

    public void guardar(ProjectData projectData) throws SQLException {
        String sql = "INSERT INTO FACTURACION "
                + "(ID, PROJECT_CODE, PROJECT_NAME, CUSTOMER, "
                + "PO_STATUS, PO_NO, PO_LINE_NO, SHIPMENT_NO, "
                + "SITE_CODE, SITE_NAME, ITEM_CODE, ITEM_DESC, "
                + "REQUESTED_QTY, DUE_QTY, BILLED_QTY, "
                + "UNIT_PRICE, LINE_AMOUNT, UNIT, PAYMENT_TERMS, "
                + "CATEGORY, BIDDING_AREA, PUBLISH_DATE) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, projectData.getProjectId());
            preparedStatement.setString(2, projectData.getProjectCode());
            preparedStatement.setString(3, projectData.getProjectName());
            preparedStatement.setString(4, projectData.getCustomer());
            preparedStatement.setString(5, projectData.getPoStatus());
            preparedStatement.setString(6, projectData.getPoNo());
            preparedStatement.setInt(7, projectData.getPoLineNo());
            preparedStatement.setInt(8, projectData.getShipmentNo());
            preparedStatement.setString(9, projectData.getSiteCode());
            preparedStatement.setString(10, projectData.getSiteName());
            preparedStatement.setString(11, projectData.getItemCode());
            preparedStatement.setString(12, projectData.getItemDesc());
            preparedStatement.setInt(13, projectData.getRequestedQty());
            preparedStatement.setDouble(14, projectData.getDueQty());
            preparedStatement.setDouble(15, projectData.getBilledQty());
            preparedStatement.setBigDecimal(16, projectData.getUnitPrice());
            preparedStatement.setBigDecimal(17, projectData.getLineAmount());
            preparedStatement.setString(18, projectData.getUnit());
            preparedStatement.setString(19, projectData.getPaymentTerms());
            preparedStatement.setString(20, projectData.getCategory());
            preparedStatement.setString(21, projectData.getBiddingArea());
            preparedStatement.setDate(22, projectData.getPublishDate());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    System.out.println(String.format("Fue guardado "
                            + "el proyecto %s", projectData));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<ProjectData> listar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ProjectData> resultado = new ArrayList<>();
        String sql = "SELECT FACTURACION.ID, FACTURACION.PROJECT_CODE, "
                + "FACTURACION.PROJECT_NAME, FACTURACION.CUSTOMER, "
                + "FACTURACION.PO_STATUS, FACTURACION.PO_NO, "
                + "FACTURACION.PO_LINE_NO, FACTURACION.SHIPMENT_NO, "
                + "FACTURACION.SITE_CODE, FACTURACION.SITE_NAME, "
                + "FACTURACION.ITEM_CODE, FACTURACION.ITEM_DESC, "
                + "FACTURACION.REQUESTED_QTY, FACTURACION.DUE_QTY, "
                + "FACTURACION.BILLED_QTY, FACTURACION.UNIT_PRICE, "
                + "FACTURACION.LINE_AMOUNT, FACTURACION.UNIT, "
                + "FACTURACION.PAYMENT_TERMS, FACTURACION.CATEGORY, "
                + "FACTURACION.BIDDING_AREA, FACTURACION.PUBLISH_DATE, "
                + "FACTURACION.STATUS_CIERRE, "
                + "ASIGNACIONES.ID_USUARIO, USUARIOS.NOMBRE, USUARIOS.APE_PAT, "
                + "USUARIOS.APE_MAT, ASIGNACIONES.ORDEN_COMPRA_DT, "
                + "ASIGNACIONES.IMPORTE, ASIGNACIONES.TOTAL_PAGAR, "
                + "ASIGNACIONES.STATUS_FACTURACION "
                + "FROM FACTURACION, ASIGNACIONES, USUARIOS "
                + "WHERE ASIGNACIONES.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "&& FACTURACION.ID = ASIGNACIONES.ID";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    ProjectData fila = new ProjectData(
                            resultSet.getInt("ID"),
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
                            resultSet.getString(simpleDateFormat.format("PUBLISH_DATE")),
                            resultSet.getString("STATUS_CIERRE"),
                            new Empleado(
                                    resultSet.getString("NOMBRE"),
                                    resultSet.getString("APE_PAT"),
                                    resultSet.getString("APE_MAT"),
                                    resultSet.getInt("ID_USUARIO")),
                            resultSet.getString("ORDEN_COMPRA_DT"),
                            resultSet.getBigDecimal("IMPORTE"),
                            resultSet.getBigDecimal("TOTAL_PAGAR"),
                            resultSet.getString("STATUS_FACTURACION"),
                    );

                    resultado.add(fila);
                }
            }
            return resultado;
        }
    }
}
