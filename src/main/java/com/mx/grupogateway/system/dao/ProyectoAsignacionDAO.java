/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Proyecto;
import com.mx.grupogateway.system.modelo.ProyectoAsignacion;
import java.math.BigDecimal;
import java.sql.Connection;
import static java.sql.JDBCType.BINARY;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduar
 */
public class ProyectoAsignacionDAO {

    private final Connection con;

    public ProyectoAsignacionDAO(Connection con) {
        this.con = con;
    }

    public void guardar(ProyectoAsignacion proyectoAsignacion) {
        String sql = "INSERT INTO EMPLEADOS_HAS_PROJECTS"
                + "(ID_EMPLEADO, ID_PROJECT, FECHA_ASIGNACION, ORDEN_COMPRA_DT,"
                + "IMPORTE, TOTAL_PAGAR, STATUS) VALUES "
                + "(?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, proyectoAsignacion.getEmpleado().getIdEmpleado());
            preparedStatement.setInt(2, proyectoAsignacion.getIdProyecto());
            preparedStatement.setString(4, proyectoAsignacion.getOrdenCompraDt());
            preparedStatement.setBigDecimal(5, proyectoAsignacion.getImporte());
            preparedStatement.setBigDecimal(6, proyectoAsignacion.getTotalPagar());
            preparedStatement.setString(7, proyectoAsignacion.getStatusAsBinary());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    System.out.println(
                            String.format("Fue guardada la siguiente "
                                    + "asignación %s", proyectoAsignacion));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public List<Proyecto> listar() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Proyecto> resultado = new ArrayList<>();
        
        String sql = "SELECT PROYECTO.ID_PROYECTO, PROYECTO.PROJECT_CODE, "
                + "PROYECTO.PROJECT_NAME, PROYECTO.CUSTOMER, "
                + "PROYECTO.PO_STATUS, PROYECTO.PO_NO, "
                + "PROYECTO.PO_LINE_NO, PROYECTO.SHIPMENT_NO, "
                + "PROYECTO.SITE_CODE, PROYECTO.SITE_NAME, "
                + "PROYECTO.ITEM_CODE, PROYECTO.ITEM_DESC, "
                + "PROYECTO.REQUESTED_QTY, PROYECTO.DUE_QTY, "
                + "PROYECTO.BILLED_QTY, PROYECTO.UNIT_PRICE, "
                + "PROYECTO.LINE_AMOUNT, PROYECTO.UNIT, "
                + "PROYECTO.PAYMENT_TERMS, PROYECTO.CATEGORY, "
                + "PROYECTO.BIDDING_AREA, PROYECTO.PUBLISH_DATE, "
                + "ASIGNACIONES.ID_USUARIO, USUARIOS.NOMBRE, USUARIOS.APE_PAT, "
                + "USUARIOS.APE_MAT, ASIGNACIONES.ORDEN_COMPRA_DT, "
                + "ASIGNACIONES.IMPORTE, ASIGNACIONES.TOTAL_PAGAR, "
                + "ASIGNACIONES.STATUS_PROYECTO "
                + "FROM PROYECTO, ASIGNACIONES, USUARIOS "
                + "WHERE ASIGNACIONES.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "&& PROYECTO.ID = ASIGNACIONES.ID";
        
        String sql = "EMPLEADOS_HAS_PROJECTS";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet();) {
                while (resultSet.next()) {
                    Proyecto fila = new Proyecto(
                            resultSet.getInt("ID_PROJECT"),
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
                            resultSet.getString("STATUS_PROYECTO")
                    );

                    resultado.add(fila);
                }
            }
            return resultado;
        }
    }*/
}
