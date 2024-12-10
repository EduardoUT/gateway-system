/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.LoggerConfig;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.PurchaseOrderAssignment;
import com.mx.grupogateway.system.modelo.PurchaseOrderDetail;
import com.mx.grupogateway.system.modelo.Site;
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
public class PurchaseOrderAssignmentDAO extends AbstractDAO {

    private static final Logger logger = LoggerConfig.getLogger();

    public PurchaseOrderAssignmentDAO(Connection con) {
        super(con);
    }

    /**
     * Cuando se reciba más de un resultado en la lista se ejecutará este
     * método.
     *
     * @param purchaseOrderAssignment Varios objetos de asignaciones.
     * @param purchaseOrderProjectIdentifiers
     */
    public void guardar(PurchaseOrderAssignment purchaseOrderAssignment,
            List<Long> purchaseOrderProjectIdentifiers) {
        String sql = "INSERT INTO EMPLEADOS_HAS_PROJECTS "
                + "(ID_EMPLEADO, ID_PROJECT, FECHA_ASIGNACION, IMPORTE, "
                + "TOTAL_PAGAR, STATUS_PAYMENT) VALUES "
                + "(?, ?, CURRENT_TIMESTAMP, ?, ?, ?) ";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Long purchaseOrderProjectIdentifier : purchaseOrderProjectIdentifiers) {
                preparedStatement.setInt(1, purchaseOrderAssignment.getEmpleado().getIdEmpleado());
                preparedStatement.setLong(2, purchaseOrderProjectIdentifier);
                preparedStatement.setBigDecimal(3, purchaseOrderAssignment.getImporte());
                preparedStatement.setBigDecimal(4, purchaseOrderAssignment.getTotalPagar());
                preparedStatement.setString(5, purchaseOrderAssignment.getStatusAsBinary());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al guardar purchaseOrderAssignment: {0}", e.getMessage());
        }
    }

    /**
     * Lista con los detalles de la asignación de la Base de Datos.
     *
     * @return LinkedList de objeto de tipo ProyectoAsignado.
     */
    public List<PurchaseOrderAssignment> listar() {
        LinkedList<PurchaseOrderAssignment> purchaseOrderAssignments = new LinkedList<>();
        String sql = "SELECT EMPLEADOS.ID_EMPLEADO, EMPLEADOS.NOMBRE, "
                + "EMPLEADOS.APE_PAT, EMPLEADOS.APE_MAT, "
                + "EMPLEADOS_HAS_PROJECTS.FECHA_ASIGNACION, "
                + "EMPLEADOS_HAS_PROJECTS.ID_PROJECT, PURCHASE_HAS_ORDER.PO_NO, "
                + "EMPLEADOS_HAS_PROJECTS.IMPORTE, "
                + "EMPLEADOS_HAS_PROJECTS.TOTAL_PAGAR, "
                + "EMPLEADOS_HAS_PROJECTS.STATUS_PAYMENT, PROJECT.CUSTOMER, "
                + "PROJECT.PROJECT_NAME, PURCHASE_ORDER.PO_STATUS, "
                + "PURCHASE_HAS_ORDER.PO_LINE_NO, SITE.SITE_CODE, SITE.SITE_NAME, "
                + "PURCHASE_ORDER.ITEM_DESC, PURCHASE_ORDER.REQUESTED_QTY, "
                + "PURCHASE_HAS_ORDER.DUE_QTY, PURCHASE_HAS_ORDER.BILLED_QTY, "
                + "PURCHASE_HAS_ORDER.UNIT_PRICE, PURCHASE_ORDER.LINE_AMOUNT, "
                + "PURCHASE_HAS_ORDER.UNIT, PURCHASE_ORDER.PAYMENT_TERMS, "
                + "PROJECT.CATEGORY, PROJECT.PUBLISH_DATE "
                + "FROM EMPLEADOS_HAS_PROJECTS "
                + "INNER JOIN EMPLEADOS ON "
                + "EMPLEADOS_HAS_PROJECTS.ID_EMPLEADO = EMPLEADOS.ID_EMPLEADO "
                + "INNER JOIN PROJECT ON "
                + "EMPLEADOS_HAS_PROJECTS.ID_PROJECT = PROJECT.ID_PROJECT "
                + "INNER JOIN SITE ON PROJECT.ID_SITE = SITE.ID_SITE "
                + "INNER JOIN PURCHASE_HAS_ORDER ON "
                + "PURCHASE_HAS_ORDER.ID_PROJECT = PROJECT.ID_PROJECT "
                + "INNER JOIN PURCHASE_ORDER ON "
                + "PURCHASE_HAS_ORDER.PO_NO = PURCHASE_ORDER.PO_NO";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Site site = new Site();
                    site.setSiteCode(resultSet.getString("SITE_CODE"));
                    site.setSiteName(resultSet.getString("SITE_NAME"));
                    Project project = new Project();
                    project.setProjectId(resultSet.getLong("ID_PROJECT"));
                    project.setSite(site);
                    project.setProjectName(resultSet.getString("PROJECT_NAME"));
                    project.setCustomer(resultSet.getString("CUSTOMER"));
                    project.setCategory(resultSet.getString("CATEGORY"));
                    project.setPublishDate(resultSet.getTimestamp("PUBLISH_DATE")
                            .toLocalDateTime());
                    PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
                    purchaseOrderDetail.setPurchaseOrderDetailIdentifier(resultSet.getString("PO_NO"));
                    purchaseOrderDetail.setPoStatus(resultSet.getString("PO_STATUS"));
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
                    Empleado empleado = new Empleado(
                            resultSet.getInt("ID_EMPLEADO"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APE_PAT"),
                            resultSet.getString("APE_MAT")
                    );
                    PurchaseOrderAssignment purchaseOrderAssignment = new PurchaseOrderAssignment();
                    purchaseOrderAssignment.setEmpleado(empleado);
                    purchaseOrderAssignment.setPurchaseOrder(purchaseOrder);
                    purchaseOrderAssignment.setFechaAsignacion(
                            resultSet.getTimestamp("FECHA_ASIGNACION")
                    );
                    purchaseOrderAssignment.setImporte(resultSet.getBigDecimal("IMPORTE"));
                    purchaseOrderAssignment.setTotalPagar(resultSet.getBigDecimal("TOTAL_PAGAR"));
                    purchaseOrderAssignment.setStatus(resultSet.getBoolean("STATUS_PAYMENT"));
                    purchaseOrderAssignments.add(purchaseOrderAssignment);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar purchaseOrderAssignment: {0}", e.getMessage());
        }
        return purchaseOrderAssignments;
    }

    /**
     * Actualiza la asignación de un proyecto o más con la misma clave "PO NO" y
     * cuyo usuario actual este asignado.
     *
     * @param empleadoNuevoId Clave del usuario de la nueva asignación.
     * @param purchaseOrderAssignment Clave de línea del proyecto, esta puede
     * ser una o varias.
     * @param empleadoActualId Clave del usuario que esta asignado actualmente
     * al proyecto.
     * @param purchaseOrderProjectIdentifiers Listado de proyectos que
     * coincidieron con PO_NO actual asignada.
     */
    public void actualizar(Integer empleadoActualId, Integer empleadoNuevoId,
            PurchaseOrderAssignment purchaseOrderAssignment, List<Long> purchaseOrderProjectIdentifiers) {
        String sql = "UPDATE EMPLEADOS_HAS_PROJECTS "
                + "SET ID_EMPLEADO = ? "
                + "WHERE ID_PROJECT = ? AND ID_EMPLEADO = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            for (Long purchaseOrderProjectIdentifier : purchaseOrderProjectIdentifiers) {
                preparedStatement.setInt(1, empleadoNuevoId);
                preparedStatement.setLong(2, purchaseOrderProjectIdentifier);
                preparedStatement.setInt(3, empleadoActualId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar purchaseOrderAssignment: {0}", e.getMessage());
        }
    }
}
