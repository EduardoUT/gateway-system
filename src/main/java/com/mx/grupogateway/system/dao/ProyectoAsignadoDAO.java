/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Project;
import com.mx.grupogateway.system.modelo.ProyectoAsignado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author eduar
 */
public class ProyectoAsignadoDAO {

    private final Connection con;

    public ProyectoAsignadoDAO(Connection con) {
        this.con = con;
    }

    /**
     * Cuando se reciba más de un resultado en la lista se ejecutará este
     * método.
     *
     * @param proyectosAsignados Varios objetos de asignaciones.
     */
    public void guardarMultiples(LinkedList<ProyectoAsignado> proyectosAsignados) {
        String sql = "INSERT INTO EMPLEADOS_HAS_PROJECTS "
                + "(ID_EMPLEADO, ID_PROJECT, PO_NO, FECHA_ASIGNACION, IMPORTE, "
                + "TOTAL_PAGAR, STATUS) VALUES "
                + "(?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?) ";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            for (ProyectoAsignado proyectoAsignado : proyectosAsignados) {
                preparedStatement.setString(1,
                        proyectoAsignado.getEmpleado().getIdEmpleado());
                preparedStatement.setLong(2,
                        proyectoAsignado.getIdProyecto());
                preparedStatement.setString(3,
                        proyectoAsignado.getPoNo());
                preparedStatement.setBigDecimal(4,
                        proyectoAsignado.getImporte());
                preparedStatement.setBigDecimal(5,
                        proyectoAsignado.getTotalPagar());
                preparedStatement.setString(6,
                        proyectoAsignado.getStatusAsBinary());
                preparedStatement.execute();
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue guardada la siguiente "
                                    + "asignación %s", proyectosAsignados));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Invocar cuando en la lista sólo exista un solo objeto.
     *
     * @param proyectosAsignados Objeto de asignaciones.
     */
    public void guardar(LinkedList<ProyectoAsignado> proyectosAsignados) {
        ProyectoAsignado proyectoAsignado = proyectosAsignados.getFirst();
        String sql = "INSERT INTO EMPLEADOS_HAS_PROJECTS "
                + "(ID_EMPLEADO, ID_PROJECT, PO_NO, FECHA_ASIGNACION, IMPORTE, "
                + "TOTAL_PAGAR, STATUS) VALUES "
                + "(?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?) ";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,
                    proyectoAsignado.getEmpleado().getIdEmpleado());
            preparedStatement.setLong(2,
                    proyectoAsignado.getIdProyecto());
            preparedStatement.setString(3,
                    proyectoAsignado.getPoNo());
            preparedStatement.setBigDecimal(4,
                    proyectoAsignado.getImporte());
            preparedStatement.setBigDecimal(5,
                    proyectoAsignado.getTotalPagar());
            preparedStatement.setString(6,
                    proyectoAsignado.getStatusAsBinary());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue guardada la siguiente "
                                    + "asignación %s", proyectosAsignados));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Lista con los detalles de la asignación de la Base de Datos.
     *
     * @return LinkedList de objeto de tipo ProyectoAsignado.
     */
    public LinkedList<ProyectoAsignado> listar() {
        LinkedList<ProyectoAsignado> proyectosAsignados = new LinkedList<>();
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
                    proyectosAsignados.add(new ProyectoAsignado(
                                    resultSet.getTimestamp("FECHA_ASIGNACION"),
                                    resultSet.getBigDecimal("IMPORTE"),
                                    resultSet.getBigDecimal("TOTAL_PAGAR"),
                                    resultSet.getString("STATUS"),
                                    new Project(
                                            resultSet.getLong("ID_PROJECT"),
                                            resultSet.getString("CUSTOMER"),
                                            resultSet.getString("PROJECT_NAME"),
                                            resultSet.getString("PO_NO"),
                                            resultSet.getString("PO_STATUS"),
                                            resultSet.getInt("PO_LINE_NO"),
                                            resultSet.getString("SITE_CODE"),
                                            resultSet.getString("SITE_NAME"),
                                            resultSet.getString("ITEM_DESC"),
                                            resultSet.getString("REQUESTED_QTY"),
                                            resultSet.getString("DUE_QTY"),
                                            resultSet.getString("BILLED_QTY"),
                                            resultSet.getBigDecimal("UNIT_PRICE"),
                                            resultSet.getBigDecimal("LINE_AMOUNT"),
                                            resultSet.getString("UNIT"),
                                            resultSet.getString("PAYMENT_TERMS"),
                                            resultSet.getString("CATEGORY"),
                                            resultSet.getTimestamp("PUBLISH_DATE").toLocalDateTime()
                                    ),
                                    new Empleado(
                                            resultSet.getString("ID_EMPLEADO"),
                                            resultSet.getString("NOMBRE"),
                                            resultSet.getString("APE_PAT"),
                                            resultSet.getString("APE_MAT")
                                    )
                            )
                    );
                }
            }
            return proyectosAsignados;
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
