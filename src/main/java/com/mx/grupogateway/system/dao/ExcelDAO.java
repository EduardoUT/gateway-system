/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.dao;

import com.mx.grupogateway.system.modelo.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author eduar
 */
//Renombrar a ExcelDAO y recibir conección por Controller para mantener arquitectura MVC
public class ExcelDAO extends SwingWorker<Void, Integer> {

    private final Connection con;
    private final JProgressBar jProgressBar;
    private final JLabel jLabel;
    private final LinkedList<Proyecto> listaProyectos;

    public ExcelDAO(Connection con, LinkedList<Proyecto> listaProyectos,
            JProgressBar jProgressBar, JLabel jLabel) {
        this.listaProyectos = listaProyectos;
        this.jProgressBar = jProgressBar;
        this.jLabel = jLabel;
        this.con = con;

    }

    @Override
    protected Void doInBackground() throws Exception {
        int progressCounter = 0;
        int listaSize = listaProyectos.size();
        String sql = "INSERT INTO PROJECTS "
                + "(ID_PROJECT, PROJECT_CODE, PROJECT_NAME, CUSTOMER, "
                + "PO_STATUS, PO_NO, PO_LINE_NO, SHIPMENT_NO, "
                + "SITE_CODE, SITE_NAME, ITEM_CODE, ITEM_DESC, "
                + "REQUESTED_QTY, DUE_QTY, BILLED_QTY, "
                + "UNIT_PRICE, LINE_AMOUNT, UNIT, PAYMENT_TERMS, "
                + "CATEGORY, BIDDING_AREA, PUBLISH_DATE) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);) {
            for (Proyecto proyecto : listaProyectos) {
                preparedStatement.setLong(1, proyecto.getIdProyecto());
                preparedStatement.setString(2, proyecto.getProjectCode());
                preparedStatement.setString(3, proyecto.getProjectName());
                preparedStatement.setString(4, proyecto.getCustomer());
                preparedStatement.setString(5, proyecto.getPoStatus());
                preparedStatement.setString(6, proyecto.getPoNo());
                preparedStatement.setInt(7, proyecto.getPoLineNo());
                preparedStatement.setInt(8, proyecto.getShipmentNo());
                preparedStatement.setString(9, proyecto.getSiteCode());
                preparedStatement.setString(10, proyecto.getSiteName());
                preparedStatement.setLong(11, proyecto.getItemCode());
                preparedStatement.setString(12, proyecto.getItemDesc());
                preparedStatement.setString(13, proyecto.getRequestedQty());
                preparedStatement.setString(14, proyecto.getDueQty());
                preparedStatement.setString(15, proyecto.getBilledQty());
                preparedStatement.setBigDecimal(16, proyecto.getUnitPrice());
                preparedStatement.setBigDecimal(17, proyecto.getLineAmount());
                preparedStatement.setString(18, proyecto.getUnit());
                preparedStatement.setString(19, proyecto.getPaymentTerms());
                preparedStatement.setString(20, proyecto.getCategory());
                preparedStatement.setString(21, proyecto.getBiddingArea());
                preparedStatement.setTimestamp(22,
                        Timestamp.valueOf(proyecto.getPublishDate())
                );
                preparedStatement.addBatch();
                progressCounter++;
                int progress = progressCounter * 100 / listaSize;
                publish(progress);
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            //System.err.println("Error en la inserción de datos: " + e.getMessage());
            throw new RuntimeException(e);

        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int latestProgress = chunks.get(chunks.size() - 1);
        jProgressBar.setValue(latestProgress);
        jLabel.setText("Importando a la Base da Datos.");
    }

    @Override
    protected void done() {
        jLabel.setText("Importación de datos finalizada.");
    }
}
