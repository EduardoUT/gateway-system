/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.PurchaseOrderAssignment;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduar
 */
public class PurchaseOrderAssignmentDAO {

    private final Connection con;

    public PurchaseOrderAssignmentDAO(Connection con) {
        this.con = con;
    }

    /**
     * Cuando se reciba más de un resultado en la lista se ejecutará este
     * método.
     *
     * @param purchaseOrderAssignment Varios objetos de asignaciones.
     * @param purchaseOrderProjectIdentifiers
     */
    public void guardar(PurchaseOrderAssignment purchaseOrderAssignment, List<Long> purchaseOrderProjectIdentifiers) {
        String sql = "INSERT INTO EMPLEADOS_HAS_PROJECTS "
                + "(ID_EMPLEADO, ID_PROJECT, FECHA_ASIGNACION, IMPORTE, "
                + "TOTAL_PAGAR, STATUS) VALUES "
                + "(?, ?, ?, CURRENT_TIMESTAMP, ?, ?) ";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Long purchaseOrderProjectIdentifier : purchaseOrderProjectIdentifiers) {
                preparedStatement.setString(1, purchaseOrderAssignment.getEmpleado().getIdEmpleado());
                preparedStatement.setLong(2, purchaseOrderProjectIdentifier);
                preparedStatement.setBigDecimal(3, purchaseOrderAssignment.getImporte());
                preparedStatement.setBigDecimal(4, purchaseOrderAssignment.getTotalPagar());
                preparedStatement.setString(5, purchaseOrderAssignment.getStatusAsBinary());
                preparedStatement.execute();
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue guardada la siguiente "
                                    + "asignación %s", purchaseOrderAssignment));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PurchaseOrderAssignmentDAO.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al guardar asignación: " + e.getMessage());
        }
    }

    /**
     * Lista con los detalles de la asignación de la Base de Datos.
     *
     * @return LinkedList de objeto de tipo ProyectoAsignado.
     */
    public List<PurchaseOrderAssignment> listar() {
        LinkedList<PurchaseOrderAssignment> purchaseOrderAssignments = new LinkedList<>();
        String sql = "SELECT EMPLEADOS_HAS_PROJECTS.ID_EMPLEADO, NOMBRE, APE_PAT, "
                + "APE_MAT, FECHA_ASIGNACION, EMPLEADOS_HAS_PROJECTS.ID_PROJECT, "
                + "EMPLEADOS_HAS_PROJECTS.PO_NO, IMPORTE, TOTAL_PAGAR, STATUS, "
                + "CUSTOMER, PROJECT_NAME, PO_STATUS, PO_LINE_NO, SITE_CODE, "
                + "SITE_NAME, ITEM_DESC, REQUESTED_QTY, DUE_QTY, BILLED_QTY, "
                + "UNIT_PRICE, LINE_AMOUNT, UNIT, PAYMENT_TERMS, CATEGORY, "
                + "PUBLISH_DATE "
                + "FROM EMPLEADOS_HAS_PROJECTS "
                + "INNER JOIN PROJECTS "
                + "ON EMPLEADOS_HAS_PROJECTS.ID_PROJECT = PROJECTS.ID_PROJECT "
                + "INNER JOIN EMPLEADOS "
                + "ON EMPLEADOS_HAS_PROJECTS.ID_EMPLEADO = EMPLEADOS.ID_EMPLEADO";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    PurchaseOrderAssignment purchaseOrderAssignment = new PurchaseOrderAssignment();

                    purchaseOrderAssignment.setEmpleado(
                            new Empleado(
                                    resultSet.getString("ID_EMPLEADO"),
                                    resultSet.getString("NOMBRE"),
                                    resultSet.getString("APE_PAT"),
                                    resultSet.getString("APE_MAT")
                            ));
                    
                     
                    
                    PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                    purchaseOrderDetail.setPoNo(resultSet.getString("PO_NO"));
                    purchaseOrderDetail.setPoStatus(resultSet.getString("PO_STATUS"));
                    purchaseOrderDetail.setItemDesc(resultSet.getString("ITEM_DESC"));
                    purchaseOrderDetail.setRequestedQty(resultSet.getString("REQUESTED_QTY"));
                    purchaseOrderDetail.setLineAmount(resultSet.getBigDecimal("LINE_aMOUNT"));
                    purchaseOrderDetail.setPaymentTerms(resultSet.getString("PAYMENT_TERMS"));
                    
                    /*
                    PurchaseOrder purchaseOrder = new PurchaseOrder(
                            purchaseOrderDetail, 
                            project, 
                            Integer.SIZE, 
                            sql, 
                            sql, 
                            sql, 
                            BigDecimal.ONE);
                    
                    purchaseOrderAssignment.setPurchaseOrder();

                    purchaseOrderAssignments.add(purchaseOrderAssignment);
                    */
                }
            }
            return purchaseOrderAssignments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza la asignación de un proyecto o más con la misma clave "PO NO" y
     * cuyo usuario actual este asignado.
     *
     * @param idEmpleado Clave del usuario de la nueva asignación.
     * @param poNo Clave de línea del proyecto, esta puede ser una o varias.
     * @param idEmpleadoAsignado Clave del usuario que esta asignado actualmente
     * al proyecto.
     * @return Cantidad de registros afectados.
     */
    public int actualizar(String idEmpleado, String poNo,
            String idEmpleadoAsignado) {
        String sql = "UPDATE EMPLEADOS_HAS_PROJECTS "
                + "SET ID_EMPLEADO = ? "
                + "WHERE PO_NO = ? AND ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, idEmpleado);
            preparedStatement.setString(2, poNo);
            preparedStatement.setString(3, idEmpleadoAsignado);
            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            return updateCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
