/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanasAdministradores;

import Conexion.ConexionBD;
import Login.Login;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author mcore
 */
public final class Asignaciones extends javax.swing.JFrame {

    /**
     * Creates new form Asignaciones
     */
    private Statement sent = null;
    private Connection cc = null;
    private ResultSet rs = null;
    private PreparedStatement p = null;
    private DefaultTableModel model1;
    private int xMouse;
    private int yMouse;

    public Asignaciones() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        LlenarTablaUsuarios();
        LlenarTablaProyectos();
        LlenarTablaAsignaciones();
        LlenarTablaHistorialAsignaciones();
        tablaEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }
    /*
    Código para Autoajustar las Columnas acorde al resultado del dato más largo de una tabla SQL
    en un JTable
     */
    int vColIndex = 1;
    int margin = 2;

    public void ColumnasAutoajustadas(JTable Tabla_Usuarios, JTable Tabla_Proyectos, JTable Tabla_Asignaciones, JTable Tabla_Historial, int margin) {
        for (int c = 0; c < Tabla_Usuarios.getColumnCount(); c++) {
            packColumnTablaUsuarios(Tabla_Usuarios, c, 2);
        }
        for (int c = 0; c < Tabla_Proyectos.getColumnCount(); c++) {
            packColumnTablaProyectos(Tabla_Proyectos, c, 2);
        }
        for (int c = 0; c < Tabla_Asignaciones.getColumnCount(); c++) {
            packColumnTablaAsignaciones(Tabla_Asignaciones, c, 2);
        }
        for (int c = 0; c < Tabla_Historial.getColumnCount(); c++) {
            packColumnTablaHistorial(Tabla_Asignaciones, c, 2);
        }
    }

    public void packColumnTablaUsuarios(JTable Tabla_Usuarios, int vColIndex, int margin) {
        //model1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) Tabla_Usuarios.getColumnModel();

        TableColumn col = colModel.getColumn(vColIndex);
        int width;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = Tabla_Usuarios.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(Tabla_Usuarios, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < Tabla_Usuarios.getRowCount(); r++) {
            renderer = Tabla_Usuarios.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(Tabla_Usuarios, Tabla_Usuarios.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    public void packColumnTablaProyectos(JTable Tabla_Proyectos, int vColIndex, int margin) {
        //model1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) Tabla_Proyectos.getColumnModel();

        TableColumn col = colModel.getColumn(vColIndex);
        int width;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = Tabla_Proyectos.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(Tabla_Proyectos, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < Tabla_Proyectos.getRowCount(); r++) {
            renderer = Tabla_Proyectos.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(Tabla_Proyectos, Tabla_Proyectos.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    public void packColumnTablaAsignaciones(JTable Tabla_Asignaciones, int vColIndex, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) Tabla_Asignaciones.getColumnModel();

        TableColumn col = colModel.getColumn(vColIndex);
        int width;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = Tabla_Asignaciones.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(Tabla_Asignaciones, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < Tabla_Asignaciones.getRowCount(); r++) {
            renderer = Tabla_Asignaciones.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(Tabla_Asignaciones, Tabla_Asignaciones.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    public void packColumnTablaHistorial(JTable Tabla_Historial, int vColIndex, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) Tabla_Historial.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = Tabla_Historial.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(Tabla_Historial, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < Tabla_Historial.getRowCount(); r++) {
            renderer = Tabla_Historial.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(Tabla_Historial, Tabla_Historial.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    public boolean DatosLlenosAsignacion() {

        return !id_user.getText().equals("") && !nombre.getText().equals("")
                && !ape_p.getText().equals("") && !ape_m.getText().equals("") && !id_line.getText().equals("");
    }

    public void LimpiarDatosUser() {
        id_asignacion.setText("");
        datestamp.setCalendar(null);
        id_user.setText("");
        name_user.setText("");
        nombre.setText("");
        ape_p.setText("");
        ape_m.setText("");
    }

    public void LimpiarDatosProyecto() {
        id_line.setText("");
        po_no.setText("");
        importe.setText("");
        total.setText("");
        status_fact.setText("");
    }

    public void LlenarTablaUsuarios() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Usuario", "Nombre Usuario", "Nombre", "Apellido Paterno", "Apellido Materno"};
            String SQL = "SELECT * FROM usuarios WHERE cat_usuario='Administrador Facturacion' or cat_usuario='Operador Facturacion'";
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[5];
            while (rs.next()) {
                fila[0] = rs.getString("id_usuario");
                fila[1] = rs.getString("nombre_usuario");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("ape_pat");
                fila[4] = rs.getString("ape_mat");
                model1.addRow(fila);
            }
            tablaEmpleados.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void LlenarTablaHistorialAsignaciones() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Historial", "ID Asignación", "Fecha Asignación", "ID", "ID Usuario", "Orden Compra DT", "Importe", "Total a Pagar", "Status Facturación"};
            String SQL = "SELECT * FROM historial_asignaciones";
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[9];
            while (rs.next()) {
                fila[0] = rs.getString("id_historial");
                fila[1] = rs.getString("id_asignacion");
                fila[2] = rs.getString("fecha_asignacion");
                fila[3] = rs.getString("id");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("orden_compra_dt");
                fila[6] = rs.getString("importe");
                fila[7] = rs.getString("total_pagar");
                fila[8] = rs.getString("status_facturacion");
                model1.addRow(fila);
            }
            Tabla_Historial.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarFechaAsignacionHistorial() {
        try {
            cc = ConexionBD.getcon();
            String Fecha_Asignacion = ((JTextField) Fecha_AS.getDateEditor().getUiComponent()).getText();
            String[] titulos = {"ID Historial", "ID Asignación", "Fecha Asignación", "ID", "ID Usuario", "Orden Compra DT", "Importe", "Total a Pagar", "Status Facturación"};
            String SQL = "SELECT * FROM historial_asignaciones WHERE fecha_asignacion like '%" + Fecha_Asignacion + "%'";
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[9];
            while (rs.next()) {
                fila[0] = rs.getString("id_historial");
                fila[1] = rs.getString("id_asignacion");
                fila[2] = rs.getString("fecha_asignacion");
                fila[3] = rs.getString("id");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("orden_compra_dt");
                fila[6] = rs.getString("importe");
                fila[7] = rs.getString("total_pagar");
                fila[8] = rs.getString("status_facturacion");
                model1.addRow(fila);
            }
            Tabla_Historial.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarTablaHistorialIDAsignacion() {
        try {
            cc = ConexionBD.getcon();
            String ID_Asignacion = String.valueOf(id_AS.getText());
            String[] titulos = {"ID Historial", "ID Asignación", "Fecha Asignación", "ID", "ID Usuario", "Orden Compra DT", "Importe", "Total a Pagar", "Status Facturación"};
            String SQL = "SELECT * FROM historial_asignaciones WHERE id_asignacion=" + ID_Asignacion + "";
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[9];
            while (rs.next()) {
                fila[0] = rs.getString("id_historial");
                fila[1] = rs.getString("id_asignacion");
                fila[2] = rs.getString("fecha_asignacion");
                fila[3] = rs.getString("id");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("orden_compra_dt");
                fila[6] = rs.getString("importe");
                fila[7] = rs.getString("total_pagar");
                fila[8] = rs.getString("status_facturacion");
                model1.addRow(fila);
            }
            Tabla_Historial.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarIDProyectoHistorial() {
        try {
            cc = ConexionBD.getcon();
            String ID_Proyecto = String.valueOf(ID_PR.getText());
            String[] titulos = {"ID Historial", "ID Asignación", "Fecha Asignación", "ID", "ID Usuario", "Orden Compra DT", "Importe", "Total a Pagar", "Status Facturación"};
            String SQL = "SELECT * FROM historial_asignaciones WHERE id=" + ID_Proyecto + "";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[9];
            while (rs.next()) {
                fila[0] = rs.getString("id_historial");
                fila[1] = rs.getString("id_asignacion");
                fila[2] = rs.getString("fecha_asignacion");
                fila[3] = rs.getString("id");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("orden_compra_dt");
                fila[6] = rs.getString("importe");
                fila[7] = rs.getString("total_pagar");
                fila[8] = rs.getString("status_facturacion");
                model1.addRow(fila);
            }
            Tabla_Historial.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarIDUsuarioHistorial() {
        try {
            cc = ConexionBD.getcon();
            String ID_Usuario = String.valueOf(ID_US.getText());
            String[] titulos = {"ID Historial", "ID Asignación", "Fecha Asignación", "ID", "ID Usuario", "Orden Compra DT", "Importe", "Total a Pagar", "Status Facturación"};
            String SQL = "SELECT * FROM historial_asignaciones WHERE id_usuario=" + ID_Usuario + "";
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[9];
            while (rs.next()) {
                fila[0] = rs.getString("id_historial");
                fila[1] = rs.getString("id_asignacion");
                fila[2] = rs.getString("fecha_asignacion");
                fila[3] = rs.getString("id");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("orden_compra_dt");
                fila[6] = rs.getString("importe");
                fila[7] = rs.getString("total_pagar");
                fila[8] = rs.getString("status_facturacion");
                model1.addRow(fila);
            }
            Tabla_Historial.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void LlenarTablaProyectos() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID", "PO NO", "Publish Date", "Project Code", "Project Name", "Customer", "PO Status", "PO Line NO",
                "Shipment NO", "Site Code", "Site Name", "Item Code",
                "Item Description", "Requested Qty", "Due Qty", "Billed Quantity",
                "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category",
                "Bidding Area"};
            String SQL = "select id,PO_NO,publish_date,project_code,project_name,customer,PO_status,PO_Line_NO,"
                    + "shipment_NO,site_code,site_name,item_code,item_desc,\n"
                    + "requested_qty,due_qty,billed_qty,unit_price,line_amount,unit,payment_terms,category,bidding_area from facturacion \n"
                    + "where PO_status like '%NEW%'";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[22];
            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("PO_NO");
                fila[2] = rs.getString("publish_date");
                fila[3] = rs.getString("project_code");
                fila[4] = rs.getString("project_name");
                fila[5] = rs.getString("customer");
                fila[6] = rs.getString("PO_status");
                fila[7] = rs.getString("PO_Line_NO");
                fila[8] = rs.getString("shipment_NO");
                fila[9] = rs.getString("site_code");
                fila[10] = rs.getString("site_name");
                fila[11] = rs.getString("item_code");
                fila[12] = rs.getString("item_desc");
                fila[13] = rs.getString("requested_qty");
                fila[14] = rs.getString("due_qty");
                fila[15] = rs.getString("billed_qty");
                fila[16] = rs.getString("unit_price");
                fila[17] = rs.getString("line_amount");
                fila[18] = rs.getString("unit");
                fila[19] = rs.getString("payment_terms");
                fila[20] = rs.getString("category");
                fila[21] = rs.getString("bidding_area");
                model1.addRow(fila);
            }
            tablaProyectos.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarTablaProyectosPO() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID", "PO NO", "Publish Date", "Project Code", "Project Name", "Customer", "PO Status", "PO Line NO",
                "Shipment NO", "Site Code", "Site Name", "Item Code",
                "Item Description", "Requested Qty", "Due Qty", "Billed Quantity",
                "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category",
                "Bidding Area"};
            String SQL = "select id,PO_NO,publish_date,project_code,project_name,customer,PO_status,PO_Line_NO, \n"
                    + "shipment_NO,site_code,site_name,item_code,item_desc, \n"
                    + "requested_qty,due_qty,billed_qty,unit_price,line_amount,unit,payment_terms,category, \n"
                    + "bidding_area from facturacion \n"
                    + "where PO_NO='" + jTextField1.getText() + "'";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[22];
            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("PO_NO");
                fila[2] = rs.getString("publish_date");
                fila[3] = rs.getString("project_code");
                fila[4] = rs.getString("project_name");
                fila[5] = rs.getString("customer");
                fila[6] = rs.getString("PO_status");
                fila[7] = rs.getString("PO_Line_NO");
                fila[8] = rs.getString("shipment_NO");
                fila[9] = rs.getString("site_code");
                fila[10] = rs.getString("site_name");
                fila[11] = rs.getString("item_code");
                fila[12] = rs.getString("item_desc");
                fila[13] = rs.getString("requested_qty");
                fila[14] = rs.getString("due_qty");
                fila[15] = rs.getString("billed_qty");
                fila[16] = rs.getString("unit_price");
                fila[17] = rs.getString("line_amount");
                fila[18] = rs.getString("unit");
                fila[19] = rs.getString("payment_terms");
                fila[20] = rs.getString("category");
                fila[21] = rs.getString("bidding_area");
                model1.addRow(fila);
            }
            tablaProyectos.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarTablaProyectosPOPublishDate() {
        try {
            cc = ConexionBD.getcon();
            String fecha = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
            String[] titulos = {"ID", "PO NO", "Publish Date", "Project Code", "Project Name", "Customer", "PO Status", "PO Line NO",
                "Shipment NO", "Site Code", "Site Name", "Item Code",
                "Item Description", "Requested Qty", "Due Qty", "Billed Quantity",
                "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category",
                "Bidding Area"};
            String SQL = "select id,PO_NO,publish_date,project_code,project_name,customer,PO_status,PO_Line_NO, \n"
                    + "shipment_NO,site_code,site_name,item_code,item_desc, \n"
                    + "requested_qty,due_qty,billed_qty,unit_price,line_amount,unit,payment_terms,category, \n"
                    + "bidding_area from facturacion \n"
                    + "where PO_NO='" + jTextField1.getText() + "' && publish_date like '%" + fecha + "%'";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[22];
            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("PO_NO");
                fila[2] = rs.getString("publish_date");
                fila[3] = rs.getString("project_code");
                fila[4] = rs.getString("project_name");
                fila[5] = rs.getString("customer");
                fila[6] = rs.getString("PO_status");
                fila[7] = rs.getString("PO_Line_NO");
                fila[8] = rs.getString("shipment_NO");
                fila[9] = rs.getString("site_code");
                fila[10] = rs.getString("site_name");
                fila[11] = rs.getString("item_code");
                fila[12] = rs.getString("item_desc");
                fila[13] = rs.getString("requested_qty");
                fila[14] = rs.getString("due_qty");
                fila[15] = rs.getString("billed_qty");
                fila[16] = rs.getString("unit_price");
                fila[17] = rs.getString("line_amount");
                fila[18] = rs.getString("unit");
                fila[19] = rs.getString("payment_terms");
                fila[20] = rs.getString("category");
                fila[21] = rs.getString("bidding_area");
                model1.addRow(fila);
            }
            tablaProyectos.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarTablaProyectosID() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID", "PO NO", "Publish Date", "Project Code", "Project Name", "Customer", "PO Status", "PO Line NO",
                "Shipment NO", "Site Code", "Site Name", "Item Code",
                "Item Description", "Requested Qty", "Due Qty", "Billed Quantity",
                "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category",
                "Bidding Area"};
            String SQL = "select id,PO_NO,publish_date,project_code,project_name,customer,PO_status,PO_Line_NO, \n"
                    + "shipment_NO,site_code,site_name,item_code,item_desc, \n"
                    + "requested_qty,due_qty,billed_qty,unit_price,line_amount,unit,payment_terms,category, \n"
                    + "bidding_area from facturacion \n"
                    + "where id=" + jTextField2.getText() + "";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[22];
            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("PO_NO");
                fila[2] = rs.getString("publish_date");
                fila[3] = rs.getString("project_code");
                fila[4] = rs.getString("project_name");
                fila[5] = rs.getString("customer");
                fila[6] = rs.getString("PO_status");
                fila[7] = rs.getString("PO_Line_NO");
                fila[8] = rs.getString("shipment_NO");
                fila[9] = rs.getString("site_code");
                fila[10] = rs.getString("site_name");
                fila[11] = rs.getString("item_code");
                fila[12] = rs.getString("item_desc");
                fila[13] = rs.getString("requested_qty");
                fila[14] = rs.getString("due_qty");
                fila[15] = rs.getString("billed_qty");
                fila[16] = rs.getString("unit_price");
                fila[17] = rs.getString("line_amount");
                fila[18] = rs.getString("unit");
                fila[19] = rs.getString("payment_terms");
                fila[20] = rs.getString("category");
                fila[21] = rs.getString("bidding_area");
                model1.addRow(fila);
            }
            tablaProyectos.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void ActualizarStatus() {
        try {
            cc = ConexionBD.getcon();
            if (DatosLlenosAsignacion()) {
                String NewStatus = "ASIGNED";
                String campo = id_line.getText();
                String SQL = "update facturacion set PO_status=? where id=?";
                //p.setString(2, ((JTextField) datestamp.getDateEditor().getUiComponent()).getText());
                p = cc.prepareStatement(SQL);
                //p.setString(2, ((JTextField) datestamp.getDateEditor().getUiComponent()).getText());
                p.setString(1, NewStatus);
                p.setString(2, campo);
                int n = p.executeUpdate();
                if (n > 0) {
                    LlenarTablaProyectos();
                    ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
                    JOptionPane.showMessageDialog(null, "Datos Actualizados Correctamente");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Llena todos los campos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización de los datos de Asignaciones: " + e.getMessage());
        } finally {
            if (p != null) {
                try {
                    p.close();
                    p = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void ActualizarAsignacion() {
        try {
            cc = ConexionBD.getcon();
            if (DatosLlenosAsignacion()) {
                String campo = id_line.getText();
                String SQL = "update asignaciones set fecha_asignacion=now(),id=?,id_usuario=? where id=?";
                //p.setString(2, ((JTextField) datestamp.getDateEditor().getUiComponent()).getText());
                p = cc.prepareStatement(SQL);
                //p.setString(2, ((JTextField) datestamp.getDateEditor().getUiComponent()).getText());
                p.setString(1, id_line.getText());
                p.setString(2, id_user.getText());
                p.setString(3, campo);
                int n = p.executeUpdate();
                if (n > 0) {
                    LlenarTablaAsignaciones();
                    ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
                    JOptionPane.showMessageDialog(null, "Datos Actualizados Correctamente");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Llena todos los campos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización de los datos de Asignaciones: " + e.getMessage());
        } finally {
            if (p != null) {
                try {
                    p.close();
                    p = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void LlenarTablaAsignaciones() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Asignación", "Fecha de Asignación", "ID", "PO NO", "ID Usuario",
                "Nombre Usuario", "Nombre", "Apellido Paterno", "Apellido Materno",
                "Orden Compra DT", "Importe", "Total Pagar", "Status Facturación", "Project Code",
                "Project Name", "Customer", "Shipment NO", "Site Code", "Site Name", "Item Code", "Item Desc",
                "Requested Qty", "Due Qty", "Billed Qty", "Unit Price", "Line Amount", "Unit", "Payment Terms",
                "Category", "Bidding Area", "Publish Date"};
            String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, asignaciones.id, \n"
                    + "facturacion.PO_NO, asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                    + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.orden_compra_dt, asignaciones.importe, \n"
                    + "asignaciones.total_pagar, asignaciones.status_facturacion, facturacion.project_code, \n"
                    + "facturacion.project_name, facturacion.customer, facturacion.shipment_NO, \n"
                    + "facturacion.site_code, facturacion.site_name, facturacion.item_code, facturacion.item_desc, \n"
                    + "facturacion.requested_qty, facturacion.due_qty, facturacion.billed_qty, \n"
                    + "facturacion.unit_price, facturacion.line_amount, facturacion.unit, facturacion.payment_terms, \n"
                    + "facturacion.category, facturacion.bidding_area, facturacion.publish_date \n"
                    + "from asignaciones, usuarios, facturacion \n"
                    + "where usuarios.cat_usuario like '%Facturacion%' && \n"
                    + "asignaciones.id_usuario = usuarios.id_usuario && facturacion.id = asignaciones.id \n"
                    + "order by id_asignacion asc;";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[31];
            while (rs.next()) {
                fila[0] = rs.getString("id_asignacion");
                fila[1] = rs.getString("fecha_asignacion");
                fila[2] = rs.getString("id");
                fila[3] = rs.getString("PO_NO");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("nombre_usuario");
                fila[6] = rs.getString("nombre");
                fila[7] = rs.getString("ape_pat");
                fila[8] = rs.getString("ape_mat");
                fila[9] = rs.getString("orden_compra_dt");
                fila[10] = rs.getString("importe");
                fila[11] = rs.getString("total_pagar");
                fila[12] = rs.getString("status_facturacion");
                fila[13] = rs.getString("project_code");
                fila[14] = rs.getString("project_name");
                fila[15] = rs.getString("customer");
                fila[16] = rs.getString("shipment_NO");
                fila[17] = rs.getString("site_code");
                fila[18] = rs.getString("site_name");
                fila[19] = rs.getString("item_code");
                fila[20] = rs.getString("item_desc");
                fila[21] = rs.getString("requested_qty");
                fila[22] = rs.getString("due_qty");
                fila[23] = rs.getString("billed_qty");
                fila[24] = rs.getString("unit_price");
                fila[25] = rs.getString("line_amount");
                fila[26] = rs.getString("unit");
                fila[27] = rs.getString("payment_terms");
                fila[28] = rs.getString("category");
                fila[29] = rs.getString("bidding_area");
                fila[30] = rs.getString("publish_date");
                model1.addRow(fila);
            }
            Tabla_Asignaciones.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarIDAsignacion() {
        String idasignacion = search_idasign.getText();
        String fechaasignacion = ((JTextField) search_dt.getDateEditor().getUiComponent()).getText();
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Asignación", "Fecha de Asignación", "ID", "PO NO", "ID Usuario",
                "Nombre Usuario", "Nombre", "Apellido Paterno", "Apellido Materno",
                "Orden Compra DT", "Importe", "Total Pagar", "Status Facturación", "Project Code",
                "Project Name", "Customer", "Shipment NO", "Site Code", "Site Name", "Item Code", "Item Desc",
                "Requested Qty", "Due Qty", "Billed Qty", "Unit Price", "Line Amount", "Unit", "Payment Terms",
                "Category", "Bidding Area", "Publish Date"};
            String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, asignaciones.id, \n"
                    + "facturacion.PO_NO, asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                    + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.orden_compra_dt, asignaciones.importe, \n"
                    + "asignaciones.total_pagar, asignaciones.status_facturacion, facturacion.project_code, \n"
                    + "facturacion.project_name, facturacion.customer, facturacion.shipment_NO, \n"
                    + "facturacion.site_code, facturacion.site_name, facturacion.item_code, facturacion.item_desc, \n"
                    + "facturacion.requested_qty, facturacion.due_qty, facturacion.billed_qty, \n"
                    + "facturacion.unit_price, facturacion.line_amount, facturacion.unit, facturacion.payment_terms, \n"
                    + "facturacion.category, facturacion.bidding_area, facturacion.publish_date \n"
                    + "from asignaciones, usuarios, facturacion \n"
                    + "where id_asignacion=" + idasignacion + " && fecha_asignacion like '%" + fechaasignacion + "%' && \n"
                    + "usuarios.cat_usuario = 'Administrador Facturacion' && \n"
                    + "asignaciones.id_usuario = usuarios.id_usuario && facturacion.id = asignaciones.id \n"
                    + "order by id_asignacion asc;";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[31];
            while (rs.next()) {
                fila[0] = rs.getString("id_asignacion");
                fila[1] = rs.getString("fecha_asignacion");
                fila[2] = rs.getString("id");
                fila[3] = rs.getString("PO_NO");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("nombre_usuario");
                fila[6] = rs.getString("nombre");
                fila[7] = rs.getString("ape_pat");
                fila[8] = rs.getString("ape_mat");
                fila[9] = rs.getString("orden_compra_dt");
                fila[10] = rs.getString("importe");
                fila[11] = rs.getString("total_pagar");
                fila[12] = rs.getString("status_facturacion");
                fila[13] = rs.getString("project_code");
                fila[14] = rs.getString("project_name");
                fila[15] = rs.getString("customer");
                fila[16] = rs.getString("shipment_NO");
                fila[17] = rs.getString("site_code");
                fila[18] = rs.getString("site_name");
                fila[19] = rs.getString("item_code");
                fila[20] = rs.getString("item_desc");
                fila[21] = rs.getString("requested_qty");
                fila[22] = rs.getString("due_qty");
                fila[23] = rs.getString("billed_qty");
                fila[24] = rs.getString("unit_price");
                fila[25] = rs.getString("line_amount");
                fila[26] = rs.getString("unit");
                fila[27] = rs.getString("payment_terms");
                fila[28] = rs.getString("category");
                fila[29] = rs.getString("bidding_area");
                fila[30] = rs.getString("publish_date");
                model1.addRow(fila);
            }
            Tabla_Asignaciones.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarIDProyecto() {
        String id_proyecto = search_id.getText();
        String fechaasignacion = ((JTextField) search_dt.getDateEditor().getUiComponent()).getText();
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Asignación", "Fecha de Asignación", "ID", "PO NO", "ID Usuario",
                "Nombre Usuario", "Nombre", "Apellido Paterno", "Apellido Materno",
                "Orden Compra DT", "Importe", "Total Pagar", "Status Facturación", "Project Code",
                "Project Name", "Customer", "Shipment NO", "Site Code", "Site Name", "Item Code", "Item Desc",
                "Requested Qty", "Due Qty", "Billed Qty", "Unit Price", "Line Amount", "Unit", "Payment Terms",
                "Category", "Bidding Area", "Publish Date"};
            String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, asignaciones.id, \n"
                    + "facturacion.PO_NO, asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                    + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.orden_compra_dt, asignaciones.importe, \n"
                    + "asignaciones.total_pagar, asignaciones.status_facturacion, facturacion.project_code, \n"
                    + "facturacion.project_name, facturacion.customer, facturacion.shipment_NO, \n"
                    + "facturacion.site_code, facturacion.site_name, facturacion.item_code, facturacion.item_desc, \n"
                    + "facturacion.requested_qty, facturacion.due_qty, facturacion.billed_qty, \n"
                    + "facturacion.unit_price, facturacion.line_amount, facturacion.unit, facturacion.payment_terms, \n"
                    + "facturacion.category, facturacion.bidding_area, facturacion.publish_date \n"
                    + "from asignaciones, usuarios, facturacion \n"
                    + "where fecha_asignacion like '%" + fechaasignacion + "%' && \n"
                    + "usuarios.cat_usuario = 'Administrador Facturacion' && \n"
                    + "asignaciones.id_usuario = usuarios.id_usuario && facturacion.id =" + id_proyecto + " && \n"
                    + "asignaciones.id=" + id_proyecto + " \n"
                    + "order by id_asignacion asc;";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[31];
            while (rs.next()) {
                fila[0] = rs.getString("id_asignacion");
                fila[1] = rs.getString("fecha_asignacion");
                fila[2] = rs.getString("id");
                fila[3] = rs.getString("PO_NO");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("nombre_usuario");
                fila[6] = rs.getString("nombre");
                fila[7] = rs.getString("ape_pat");
                fila[8] = rs.getString("ape_mat");
                fila[9] = rs.getString("orden_compra_dt");
                fila[10] = rs.getString("importe");
                fila[11] = rs.getString("total_pagar");
                fila[12] = rs.getString("status_facturacion");
                fila[13] = rs.getString("project_code");
                fila[14] = rs.getString("project_name");
                fila[15] = rs.getString("customer");
                fila[16] = rs.getString("shipment_NO");
                fila[17] = rs.getString("site_code");
                fila[18] = rs.getString("site_name");
                fila[19] = rs.getString("item_code");
                fila[20] = rs.getString("item_desc");
                fila[21] = rs.getString("requested_qty");
                fila[22] = rs.getString("due_qty");
                fila[23] = rs.getString("billed_qty");
                fila[24] = rs.getString("unit_price");
                fila[25] = rs.getString("line_amount");
                fila[26] = rs.getString("unit");
                fila[27] = rs.getString("payment_terms");
                fila[28] = rs.getString("category");
                fila[29] = rs.getString("bidding_area");
                fila[30] = rs.getString("publish_date");
                model1.addRow(fila);
            }
            Tabla_Asignaciones.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void BuscarPO() {
        String buscarpo = search_PO.getText();
        String fechapublicacion = ((JTextField) search_dt.getDateEditor().getUiComponent()).getText();
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Asignación", "Fecha de Asignación", "ID", "PO NO", "ID Usuario",
                "Nombre Usuario", "Nombre", "Apellido Paterno", "Apellido Materno",
                "Orden Compra DT", "Importe", "Total Pagar", "Status Facturación", "Project Code",
                "Project Name", "Customer", "Shipment NO", "Site Code", "Site Name", "Item Code", "Item Desc",
                "Requested Qty", "Due Qty", "Billed Qty", "Unit Price", "Line Amount", "Unit", "Payment Terms",
                "Category", "Bidding Area", "Publish Date"};
            String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, asignaciones.id, \n"
                    + "facturacion.PO_NO, asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                    + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.orden_compra_dt, asignaciones.importe, \n"
                    + "asignaciones.total_pagar, asignaciones.status_facturacion, facturacion.project_code, \n"
                    + "facturacion.project_name, facturacion.customer, facturacion.shipment_NO, \n"
                    + "facturacion.site_code, facturacion.site_name, facturacion.item_code, facturacion.item_desc, \n"
                    + "facturacion.requested_qty, facturacion.due_qty, facturacion.billed_qty, \n"
                    + "facturacion.unit_price, facturacion.line_amount, facturacion.unit, facturacion.payment_terms, \n"
                    + "facturacion.category, facturacion.bidding_area, facturacion.publish_date \n"
                    + "from asignaciones, usuarios, facturacion \n"
                    + "where PO_NO='" + buscarpo + "' && publish_date like '%" + fechapublicacion + "%' && \n"
                    + "usuarios.cat_usuario = 'Administrador Facturacion' && \n"
                    + "asignaciones.id_usuario = usuarios.id_usuario && facturacion.id = asignaciones.id \n"
                    + "order by id_asignacion asc;";
            //tabla1.setModel(model);
            model1 = new DefaultTableModel(null, titulos);
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            String[] fila = new String[31];
            while (rs.next()) {
                fila[0] = rs.getString("id_asignacion");
                fila[1] = rs.getString("fecha_asignacion");
                fila[2] = rs.getString("id");
                fila[3] = rs.getString("PO_NO");
                fila[4] = rs.getString("id_usuario");
                fila[5] = rs.getString("nombre_usuario");
                fila[6] = rs.getString("nombre");
                fila[7] = rs.getString("ape_pat");
                fila[8] = rs.getString("ape_mat");
                fila[9] = rs.getString("orden_compra_dt");
                fila[10] = rs.getString("importe");
                fila[11] = rs.getString("total_pagar");
                fila[12] = rs.getString("status_facturacion");
                fila[13] = rs.getString("project_code");
                fila[14] = rs.getString("project_name");
                fila[15] = rs.getString("customer");
                fila[16] = rs.getString("shipment_NO");
                fila[17] = rs.getString("site_code");
                fila[18] = rs.getString("site_name");
                fila[19] = rs.getString("item_code");
                fila[20] = rs.getString("item_desc");
                fila[21] = rs.getString("requested_qty");
                fila[22] = rs.getString("due_qty");
                fila[23] = rs.getString("billed_qty");
                fila[24] = rs.getString("unit_price");
                fila[25] = rs.getString("line_amount");
                fila[26] = rs.getString("unit");
                fila[27] = rs.getString("payment_terms");
                fila[28] = rs.getString("category");
                fila[29] = rs.getString("bidding_area");
                fila[30] = rs.getString("publish_date");
                model1.addRow(fila);
            }
            Tabla_Asignaciones.setModel(model1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Llenar Tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Asignaciones = new javax.swing.JPanel();
        jPanel4 = new FondoPanelTitulo();
        jLabel39 = new javax.swing.JLabel();
        Nombre_Usuario_Facturas = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        ID_Usuario_Facturas = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buscadorEmpleado = new javax.swing.JTextField();
        botonBuscarEmpleado = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mostrarTodosProyectos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        buscadorProyecto = new javax.swing.JTextField();
        botonBuscarProyecto = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        filtroEmpleado = new javax.swing.JComboBox<>();
        filtroProyecto = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla_Asignaciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new FondoPanelesCentrales();
        jLabel3 = new javax.swing.JLabel();
        po_no = new javax.swing.JTextField();
        importe = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        status_fact = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        orden_dt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        id_line = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        search_idasign = new javax.swing.JTextField();
        filtroBusquedaAsignacion = new javax.swing.JComboBox<>();
        search_dt = new com.toedter.calendar.JDateChooser();
        jButton10 = new javax.swing.JButton();
        Historial = new javax.swing.JPanel();
        jPanel5 = new FondoPanelTitulo();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_Historial = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        id_AS = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        Fecha_AS = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        ID_US = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        ID_PR = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jTabbedPane1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setPreferredSize(new java.awt.Dimension(1329, 60));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Bienvenido (a):");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        Nombre_Usuario_Facturas.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        Nombre_Usuario_Facturas.setForeground(new java.awt.Color(255, 255, 255));
        Nombre_Usuario_Facturas.setText("Nombre");
        jPanel4.add(Nombre_Usuario_Facturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel40.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("ID:");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        ID_Usuario_Facturas.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        ID_Usuario_Facturas.setForeground(new java.awt.Color(255, 255, 255));
        ID_Usuario_Facturas.setText("ID");
        jPanel4.add(ID_Usuario_Facturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jLabel11.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Asignación de Proyectos");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 60));

        buscadorEmpleado.setBackground(new Color(0, 0, 0, 0));
        buscadorEmpleado.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        buscadorEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorEmpleadoKeyTyped(evt);
            }
        });

        botonBuscarEmpleado.setText("Buscar");
        botonBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarEmpleadoActionPerformed(evt);
            }
        });

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaEmpleados.getTableHeader().setResizingAllowed(false);
        tablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEmpleadosMousePressed(evt);
            }
        });
        tablaEmpleados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaEmpleadosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEmpleados);

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Seleccione en la tabla el empleado que desee asignar");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Seleccione en la tabla el proyecto que desee asignar.");

        mostrarTodosProyectos.setText("Mostrar Todo");
        mostrarTodosProyectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarTodosProyectosActionPerformed(evt);
            }
        });

        jScrollPane2.setAutoscrolls(true);

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaProyectos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProyectos.getTableHeader().setResizingAllowed(false);
        tablaProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProyectosMousePressed(evt);
            }
        });
        tablaProyectos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProyectosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProyectos);

        buscadorProyecto.setBackground(new Color(0, 0, 0, 0));
        buscadorProyecto.setToolTipText("Buscar Empleado");
        buscadorProyecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorProyectoKeyTyped(evt);
            }
        });

        botonBuscarProyecto.setText("Buscar");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        filtroEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Empleado", "Nombre", "Cargo" }));
        filtroEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroEmpleadoActionPerformed(evt);
            }
        });

        filtroProyecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Proyecto", "PO", "Item Code" }));

        jButton2.setText("Mostrar todo");

        jScrollPane4.setAutoscrolls(true);

        Tabla_Asignaciones.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        Tabla_Asignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Tabla_Asignaciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Tabla_Asignaciones.getTableHeader().setResizingAllowed(false);
        Tabla_Asignaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tabla_AsignacionesMousePressed(evt);
            }
        });
        Tabla_Asignaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Tabla_AsignacionesKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(Tabla_Asignaciones);

        jLabel1.setText("Asignaciones");

        jButton3.setText("Guardar Asignación");

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(370, 227));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No.Orden Compra (PO_NO):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 20, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        po_no.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 102;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 265);
        jPanel3.add(po_no, gridBagConstraints);

        importe.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 102;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 265);
        jPanel3.add(importe, gridBagConstraints);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Importe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 149, 0, 0);
        jPanel3.add(jLabel8, gridBagConstraints);

        total.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 102;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 265);
        jPanel3.add(total, gridBagConstraints);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 18, 0, 0);
        jPanel3.add(jLabel9, gridBagConstraints);

        status_fact.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 102;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 6, 265);
        jPanel3.add(status_fact, gridBagConstraints);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Status Facturación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 82, 0, 0);
        jPanel3.add(jLabel10, gridBagConstraints);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Orden De Compra DT:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 58, 0, 0);
        jPanel3.add(jLabel6, gridBagConstraints);

        orden_dt.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 102;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 265);
        jPanel3.add(orden_dt, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Linea:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(56, 148, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        id_line.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 102;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(52, 12, 0, 265);
        jPanel3.add(id_line, gridBagConstraints);

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("(Line Amount)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 56, 0, 0);
        jPanel3.add(jLabel41, gridBagConstraints);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Resumen orden de compra.");

        jButton4.setText("Actualizar");

        jButton11.setText("Eliminar");

        filtroBusquedaAsignacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Proyecto", "Id Empleado", "Id PO", "Po No", "Fecha Asignación", "Fecha Publicación", " " }));

        search_dt.setToolTipText("2020-03-07 00:00:00");
        search_dt.setDateFormatString("yyyy-MM-dd");
        search_dt.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N

        jButton10.setText("Buscar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buscadorEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filtroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonBuscarEmpleado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(search_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(search_idasign, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(filtroBusquedaAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton10)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buscadorProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filtroProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonBuscarProyecto))
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                        .addComponent(mostrarTodosProyectos))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscadorEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filtroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonBuscarEmpleado))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filtroProyecto)
                            .addComponent(botonBuscarProyecto))
                        .addComponent(buscadorProyecto, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(mostrarTodosProyectos))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton11)
                            .addComponent(jButton10)
                            .addComponent(search_idasign)
                            .addComponent(filtroBusquedaAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout AsignacionesLayout = new javax.swing.GroupLayout(Asignaciones);
        Asignaciones.setLayout(AsignacionesLayout);
        AsignacionesLayout.setHorizontalGroup(
            AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1651, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AsignacionesLayout.setVerticalGroup(
            AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AsignacionesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Asignaciones", Asignaciones);

        Historial.setBackground(new java.awt.Color(0, 0, 0));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setPreferredSize(new java.awt.Dimension(1329, 60));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Historial de Asignaciones");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 60));

        Tabla_Historial.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        Tabla_Historial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla_Historial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tabla_HistorialMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(Tabla_Historial);

        jLabel19.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Panel de Búsqueda de Asignación de Proyectos");

        jLabel20.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ID Asignación:");

        id_AS.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        id_AS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_ASActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Fecha Asignación:");

        Fecha_AS.setDateFormatString("yyyy-MM-dd");
        Fecha_AS.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N

        jButton7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ID Usuario:");

        ID_US.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        ID_US.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_USActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("ID Proyecto:");

        ID_PR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        ID_PR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_PRActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jButton9.setText("Buscar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jButton5.setText("Mostrar Todo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HistorialLayout = new javax.swing.GroupLayout(Historial);
        Historial.setLayout(HistorialLayout);
        HistorialLayout.setHorizontalGroup(
            HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistorialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1639, Short.MAX_VALUE)
                    .addGroup(HistorialLayout.createSequentialGroup()
                        .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(HistorialLayout.createSequentialGroup()
                                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(id_AS)
                                    .addComponent(Fecha_AS, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(ID_US)
                                    .addComponent(ID_PR))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7)
                                    .addComponent(jButton8)
                                    .addComponent(jButton9)
                                    .addComponent(jButton6)))
                            .addComponent(jButton5))
                        .addGap(0, 1255, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        HistorialLayout.setVerticalGroup(
            HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistorialLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(id_AS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6))
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HistorialLayout.createSequentialGroup()
                        .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Fecha_AS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(ID_US, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)))
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(ID_PR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(306, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Historial de Asignaciones", Historial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaEmpleadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosMousePressed
        int fila = tablaEmpleados.getSelectedRow();
        try {
            cc = ConexionBD.getcon();
            String SQL = "SELECT * FROM usuarios WHERE id_usuario='" + tablaEmpleados.getValueAt(fila, 0) + "'";
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            rs.next();
            id_user.setText(rs.getString("id_usuario"));
            name_user.setText(rs.getString("nombre_usuario"));
            nombre.setText(rs.getString("nombre"));
            ape_p.setText(rs.getString("ape_pat"));
            ape_m.setText(rs.getString("ape_mat"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la selección de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tablaEmpleadosMousePressed

    private void tablaEmpleadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEmpleadosKeyReleased
        if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = tablaEmpleados.getSelectedRow();
            try {
                cc = ConexionBD.getcon();
                String SQL = "SELECT * FROM usuarios WHERE id_usuario='" + tablaEmpleados.getValueAt(fila, 0) + "'";
                sent = cc.createStatement();
                rs = sent.executeQuery(SQL);
                rs.next();
                id_user.setText(rs.getString("id_usuario"));
                name_user.setText(rs.getString("nombre_usuario"));
                nombre.setText(rs.getString("nombre"));
                ape_p.setText(rs.getString("ape_pat"));
                ape_m.setText(rs.getString("ape_mat"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en la selección de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                        rs = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sent != null) {
                    try {
                        sent.close();
                        sent = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (cc != null) {
                    try {
                        cc.close();
                        cc = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaEmpleadosKeyReleased

    private void tablaProyectosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectosMousePressed
        int fila = tablaProyectos.getSelectedRow();
        try {
            cc = ConexionBD.getcon();
            String SQL = "SELECT id, PO_NO, line_amount FROM facturacion WHERE id='" + tablaProyectos.getValueAt(fila, 0) + "'";
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            rs.next();
            id_line.setText(rs.getString("id"));
            po_no.setText(rs.getString("PO_NO"));
            total.setText(rs.getString("line_amount"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la selección de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tablaProyectosMousePressed

    private void tablaProyectosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProyectosKeyReleased
        if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = tablaProyectos.getSelectedRow();
            try {
                cc = ConexionBD.getcon();
                String SQL = "SELECT id, PO_NO FROM facturacion WHERE id='" + tablaProyectos.getValueAt(fila, 0) + "'";
                sent = cc.createStatement();
                rs = sent.executeQuery(SQL);
                rs.next();
                id_line.setText(rs.getString("id"));
                po_no.setText(rs.getString("PO_NO"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en la selección de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                        rs = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sent != null) {
                    try {
                        sent.close();
                        sent = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (cc != null) {
                    try {
                        cc.close();
                        cc = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaProyectosKeyReleased

    private void Tabla_AsignacionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_AsignacionesMousePressed
        int fila = Tabla_Asignaciones.getSelectedRow();
        try {
            cc = ConexionBD.getcon();
            String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, \n"
                    + "asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                    + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.id, facturacion.PO_NO, \n"
                    + "asignaciones.orden_compra_dt, asignaciones.importe, asignaciones.total_pagar, \n"
                    + "asignaciones.status_facturacion \n"
                    + "from asignaciones, facturacion, usuarios \n"
                    + "where id_asignacion='" + Tabla_Asignaciones.getValueAt(fila, 0) + "' \n"
                    + "&& asignaciones.id_usuario = usuarios.id_usuario && \n"
                    + "asignaciones.id = facturacion.id;";
            sent = cc.createStatement();
            rs = sent.executeQuery(SQL);
            rs.next();
            id_asignacion.setText(rs.getString("id_asignacion"));
            datestamp.setDate(rs.getTimestamp("fecha_asignacion"));
            id_user.setText(rs.getString("id_usuario"));
            name_user.setText(rs.getString("nombre_usuario"));
            nombre.setText(rs.getString("nombre"));
            ape_p.setText(rs.getString("ape_pat"));
            ape_m.setText(rs.getString("ape_mat"));
            id_line.setText(rs.getString("id"));
            po_no.setText(rs.getString("PO_NO"));
            orden_dt.setText(rs.getString("orden_compra_dt"));
            importe.setText(rs.getString("importe"));
            total.setText(rs.getString("total_pagar"));
            status_fact.setText(rs.getString("status_facturacion"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la selección de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sent != null) {
                try {
                    sent.close();
                    sent = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cc != null) {
                try {
                    cc.close();
                    cc = null;
                } catch (SQLException ex) {
                    Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_Tabla_AsignacionesMousePressed

    private void Tabla_AsignacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla_AsignacionesKeyReleased
        if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = Tabla_Asignaciones.getSelectedRow();
            try {
                cc = ConexionBD.getcon();
                String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, \n"
                        + "asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                        + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.id, facturacion.PO_NO, \n"
                        + "asignaciones.orden_compra_dt, asignaciones.importe, asignaciones.total_pagar, \n"
                        + "asignaciones.status_facturacion \n"
                        + "from asignaciones, facturacion, usuarios \n"
                        + "where id_asignacion='" + Tabla_Asignaciones.getValueAt(fila, 0) + "' \n"
                        + "&& asignaciones.id_usuario = usuarios.id_usuario && \n"
                        + "asignaciones.id = facturacion.id;";
                sent = cc.createStatement();
                rs = sent.executeQuery(SQL);
                rs.next();
                id_asignacion.setText(rs.getString("id_asignacion"));
                datestamp.setDate(rs.getTimestamp("fecha_asignacion"));
                id_user.setText(rs.getString("id_usuario"));
                name_user.setText(rs.getString("nombre_usuario"));
                nombre.setText(rs.getString("nombre"));
                ape_p.setText(rs.getString("ape_pat"));
                ape_m.setText(rs.getString("ape_mat"));
                id_line.setText(rs.getString("id"));
                po_no.setText(rs.getString("PO_NO"));
                orden_dt.setText(rs.getString("orden_compra_dt"));
                importe.setText(rs.getString("importe"));
                total.setText(rs.getString("total_pagar"));
                status_fact.setText(rs.getString("status_facturacion"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en la selección de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                        rs = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sent != null) {
                    try {
                        sent.close();
                        sent = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (cc != null) {
                    try {
                        cc.close();
                        cc = null;
                    } catch (SQLException ex) {
                        Logger.getLogger(Asignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_Tabla_AsignacionesKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        BuscarIDAsignacion();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void id_ASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_ASActionPerformed
        jButton6.doClick();
    }//GEN-LAST:event_id_ASActionPerformed

    private void ID_PRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_PRActionPerformed
        jButton9.doClick();
    }//GEN-LAST:event_ID_PRActionPerformed

    private void Tabla_HistorialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_HistorialMousePressed

    }//GEN-LAST:event_Tabla_HistorialMousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        BuscarTablaHistorialIDAsignacion();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        BuscarFechaAsignacionHistorial();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        BuscarIDUsuarioHistorial();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        BuscarIDProyecto();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void ID_USActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_USActionPerformed
        jButton8.doClick();
    }//GEN-LAST:event_ID_USActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        LlenarTablaHistorialAsignaciones();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void mostrarTodosProyectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarTodosProyectosActionPerformed
        LlenarTablaProyectos();
        ColumnasAutoajustadas(tablaEmpleados, tablaProyectos, Tabla_Asignaciones, Tabla_Historial, margin);
    }//GEN-LAST:event_mostrarTodosProyectosActionPerformed

    private void buscadorEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorEmpleadoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_buscadorEmpleadoKeyTyped

    private void botonBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBuscarEmpleadoActionPerformed

    private void buscadorProyectoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorProyectoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorProyectoKeyTyped

    private void filtroEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroEmpleadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asignaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asignaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Asignaciones;
    private com.toedter.calendar.JDateChooser Fecha_AS;
    private javax.swing.JPanel Historial;
    private javax.swing.JTextField ID_PR;
    private javax.swing.JTextField ID_US;
    public static javax.swing.JLabel ID_Usuario_Facturas;
    public javax.swing.JLabel Nombre_Usuario_Facturas;
    private javax.swing.JTable Tabla_Asignaciones;
    private javax.swing.JTable Tabla_Historial;
    private javax.swing.JButton botonBuscarEmpleado;
    private javax.swing.JButton botonBuscarProyecto;
    private javax.swing.JTextField buscadorEmpleado;
    private javax.swing.JTextField buscadorProyecto;
    private javax.swing.JComboBox<String> filtroBusquedaAsignacion;
    private javax.swing.JComboBox<String> filtroEmpleado;
    private javax.swing.JComboBox<String> filtroProyecto;
    private javax.swing.JTextField id_AS;
    private javax.swing.JTextField id_line;
    private javax.swing.JTextField importe;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton mostrarTodosProyectos;
    private javax.swing.JTextField orden_dt;
    private javax.swing.JTextField po_no;
    private com.toedter.calendar.JDateChooser search_dt;
    private javax.swing.JTextField search_idasign;
    private javax.swing.JTextField status_fact;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
class FondoPanelTitulo extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Imagenes/Clear Sky.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    class FondoPanelPrincipal extends JLabel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Imagenes/Background2.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    class FondoPanelesCentrales extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Imagenes/Clear Sky.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
