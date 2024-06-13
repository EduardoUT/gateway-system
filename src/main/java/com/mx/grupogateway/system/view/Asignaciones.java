/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.grupogateway.system.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.system.controller.EmpleadoController;
import com.mx.grupogateway.system.controller.ProyectoAsignadoController;
import com.mx.grupogateway.system.controller.ProyectoController;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Proyecto;
import com.mx.grupogateway.system.modelo.ProyectoAsignado;
import com.mx.grupogateway.system.view.util.TablaColumnasAutoajustables;
import com.mx.grupogateway.system.view.util.TableDataModelAsignacion;
import com.mx.grupogateway.system.view.util.TableDataModelEmpleado;
import com.mx.grupogateway.system.view.util.TableDataModelProyecto;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mcore
 */
public final class Asignaciones extends javax.swing.JFrame {

    /**
     * Creates new form Asignaciones
     */
    private DefaultTableModel modeloTablaProyectos;
    private DefaultTableModel modeloTablaEmpleados;
    private DefaultTableModel modeloTablaAsignaciones;
    private TableDataModelProyecto tableDataModelProyecto;
    private TableDataModelEmpleado tableDataModelEmpleado;
    private TableDataModelAsignacion tableDataModelAsignacion;
    private final ProyectoController proyectoController;
    private final EmpleadoController empleadoController;
    private final ProyectoAsignadoController proyectosAsignadosController;
    private LinkedList<ProyectoAsignado> filtroProyectosAsignados;
    private List<Proyecto> listaProyectos;
    private int filaTablaProyectos;
    private int filaTablaEmpleados;
    private int filaTablaAsignaciones;

    public Asignaciones() {
        initComponents();
        filaTablaProyectos = tablaProyectos.getSelectedRow();
        filaTablaEmpleados = tablaEmpleados.getSelectedRow();
        filaTablaAsignaciones = tablaAsignaciones.getSelectedRow();
        this.proyectoController = new ProyectoController();
        this.empleadoController = new EmpleadoController();
        this.proyectosAsignadosController = new ProyectoAsignadoController();
        cargarTablaEmpleados();
        cargarTablaProyectos();
        cargarTablaProyectosAsignados();
        //cargarTablaHistorialAsignaciones();
        selectionRowListener();
        filtrarResultados(tablaProyectos, buscadorProyecto, filtroProyecto);
        filtrarResultados(tablaEmpleados, buscadorEmpleado, filtroEmpleado);
        filtrarResultados(tablaAsignaciones, buscadorAsignacion, filtroAsignacion);
    }

    private void cargarTablaEmpleados() {
        modeloTablaEmpleados = (DefaultTableModel) tablaEmpleados.getModel();
        List<Empleado> empleados = this.empleadoController.listar();
        tableDataModelEmpleado = new TableDataModelEmpleado(
                modeloTablaEmpleados, tablaEmpleados, empleados
        );
        tableDataModelEmpleado.cargarTablaEmpleados();
        TablaColumnasAutoajustables.autoajustarColumnas(tablaEmpleados);
    }

    private void cargarTablaProyectos() {
        modeloTablaProyectos = (DefaultTableModel) tablaProyectos.getModel();
        listaProyectos = this.proyectoController.listar();
        tableDataModelProyecto = new TableDataModelProyecto(
                modeloTablaProyectos, tablaProyectos, listaProyectos
        );
        tableDataModelProyecto.cargarTablaProyectos();
        TablaColumnasAutoajustables.autoajustarColumnas(tablaProyectos);
    }

    private void cargarTablaProyectosAsignados() {
        modeloTablaAsignaciones = (DefaultTableModel) tablaAsignaciones.getModel();
        List<ProyectoAsignado> listaProyectosAsignados
                = this.proyectosAsignadosController.listar();
        tableDataModelAsignacion = new TableDataModelAsignacion(
                modeloTablaAsignaciones, tablaAsignaciones, listaProyectosAsignados);
        tableDataModelAsignacion.cargarTablaAsignaciones();
        TablaColumnasAutoajustables.autoajustarColumnas(tablaAsignaciones);
    }

    private void selectionRowListener() {
        tablaProyectos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                filaTablaProyectos = tablaProyectos.getSelectedRow();
            }
        });

        tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                filaTablaEmpleados = tablaEmpleados.getSelectedRow();
            }
        });

        tablaAsignaciones.getSelectionModel().addListSelectionListener(e -> {
            filaTablaAsignaciones = tablaAsignaciones.getSelectedRow();
        });
    }

    private void filtrarResultados(JTable tabla, JTextField campo,
            JComboBox comboBox) {
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tabla.getModel());
        campo.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = campo.getText();
                String seleccion = (String) comboBox.getSelectedItem();
                comboBox.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(
                            RowFilter.regexFilter(
                                    "(?i)" + text,
                                    tabla
                                            .getColumnModel()
                                            .getColumnIndex(
                                                    seleccion
                                            )
                            )
                    );
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = campo.getText();
                String seleccion = (String) comboBox.getSelectedItem();
                comboBox.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(
                            RowFilter.regexFilter(
                                    "(?i)" + text,
                                    tabla
                                            .getColumnModel()
                                            .getColumnIndex(
                                                    seleccion
                                            )
                            )
                    );
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        tabla.setRowSorter(rowSorter);
    }

    private void guardarAsignacion() {
        if (filaTablaProyectos == -1 || filaTablaEmpleados == -1) {
            System.out.println("No se han seleccionado filas");
        } else {
            filtroProyectosAsignados = new LinkedList<>();
            String poNo = tablaProyectos.getValueAt(
                    filaTablaProyectos, 1)
                    .toString();
            String idEmpleado = tablaEmpleados.getValueAt(
                    filaTablaEmpleados, 0)
                    .toString();
            listaProyectos.forEach((proyecto) -> {
                Long idProyecto = proyecto.getIdProyecto();
                if (proyecto.getPoNo().equals(poNo)) {
                    filtroProyectosAsignados.add(
                            new ProyectoAsignado(idProyecto, poNo,
                                    new Empleado(idEmpleado))
                    );
                }
            });
            this.proyectosAsignadosController.guardar(filtroProyectosAsignados);
            filtroProyectosAsignados = null;
        }
    }

    private void actualizarAsignacion() {
        if (filaTablaEmpleados == -1 || filaTablaAsignaciones == -1) {

        } else {
            String idEmpleadoAsignado = tablaAsignaciones.getValueAt(
                    filaTablaAsignaciones, 0).toString();
            String idEmpleado = tablaEmpleados.getValueAt(
                    filaTablaEmpleados, 0)
                    .toString();
            String poNo = tablaAsignaciones.getValueAt(
                    filaTablaAsignaciones, 6)
                    .toString();
            if (idEmpleadoAsignado.equals(idEmpleado)) {
                System.out.println("Seleccione un usuario diferente.");
            } else {
                int filasActualizadas = this.proyectosAsignadosController.actualizar(
                        idEmpleado, poNo, idEmpleadoAsignado
                );
                System.out.println(filasActualizadas);
            }
        }
    }

    /*
    public void cargarTablaHistorialAsignaciones() {
        try {
            cc = ConexionBD.getcon();
            String[] titulos = {"ID Historial", "ID Asignación", "Fecha Asignación", "ID", "ID Usuario", "Orden Compra DT", "Importe", "Total a Pagar", "Status Facturación"};
            String SQL = "SELECT * FROM historial_asignaciones";
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            Tabla_Historial.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            Tabla_Historial.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            Tabla_Historial.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            Tabla_Historial.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            Tabla_Historial.setModel(modeloTabla);
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
    }*/

 /*
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaEmpleados.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaEmpleados.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaEmpleados.setModel(modeloTabla);
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
                    cargarTablaProyectos();
                    ColumnasAutoajustadas(tablaProyectos, tablaEmpleados, tablaAsignaciones, Tabla_Historial, margin);
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
                    ColumnasAutoajustadas(tablaProyectos, tablaEmpleados, tablaAsignaciones, Tabla_Historial, margin);
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
    }*/

 /*
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaAsignaciones.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaAsignaciones.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaAsignaciones.setModel(modeloTabla);
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
            modeloTabla = new DefaultTableModel(null, titulos);
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
                modeloTabla.addRow(fila);
            }
            tablaAsignaciones.setModel(modeloTabla);
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
    }*/
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
        buscadorProyecto = new javax.swing.JTextField();
        filtroProyecto = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        buscadorEmpleado = new javax.swing.JTextField();
        filtroEmpleado = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        botonGuardarAsignacion = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaAsignaciones = new javax.swing.JTable();
        filtroAsignacion = new javax.swing.JComboBox<>();
        buscadorAsignacion = new javax.swing.JTextField();
        botonActualizarAsignacion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        poNoCampo = new javax.swing.JTextField();
        importe = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        statusFacturacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fechaAsignacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombreEmpleado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturación");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1766, 910));

        jTabbedPane1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1651, 95));

        Asignaciones.setMinimumSize(new java.awt.Dimension(1651, 854));
        Asignaciones.setName(""); // NOI18N

        buscadorProyecto.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 15)); // NOI18N
        buscadorProyecto.setToolTipText("Buscar proyecto");
        buscadorProyecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorProyectoKeyTyped(evt);
            }
        });

        filtroProyecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "PO_NO" }));
        filtroProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroProyectoActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Seleccione en la tabla el proyecto que desee asignar");

        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProyectos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProyectos.getTableHeader().setResizingAllowed(false);
        tablaProyectos.getTableHeader().setReorderingAllowed(false);
        tablaProyectos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProyectosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProyectos);

        buscadorEmpleado.setToolTipText("Buscar Empleado");
        buscadorEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorEmpleadoKeyTyped(evt);
            }
        });

        filtroEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID_EMPLEADO", "ID_USUARIO", "NOMBRE", "CARGO" }));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Seleccione en la tabla el empleado que desee asignar.");

        botonGuardarAsignacion.setText("Guardar Asignación");
        botonGuardarAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonGuardarAsignacionMouseClicked(evt);
            }
        });

        jScrollPane4.setAutoscrolls(true);

        tablaAsignaciones.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        tablaAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaAsignaciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAsignaciones.getTableHeader().setResizingAllowed(false);
        tablaAsignaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsignacionesMouseClicked(evt);
            }
        });
        tablaAsignaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaAsignacionesKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tablaAsignaciones);

        filtroAsignacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID_PROYECTO", "ID_EMPLEADO", "PO_NO" }));

        botonActualizarAsignacion.setText("Actualizar Asignación");
        botonActualizarAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarAsignacionMouseClicked(evt);
            }
        });

        jLabel1.setText("Asignaciones");

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 402));

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaEmpleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.getTableHeader().setResizingAllowed(false);
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        tablaEmpleados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaEmpleadosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaEmpleados);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(370, 227));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PO NO:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        poNoCampo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(poNoCampo, gridBagConstraints);

        importe.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(importe, gridBagConstraints);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Importe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        total.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(total, gridBagConstraints);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

        statusFacturacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(statusFacturacion, gridBagConstraints);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Status Facturación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel10, gridBagConstraints);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha Asignacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        fechaAsignacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(fechaAsignacion, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre Empleado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        nombreEmpleado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(nombreEmpleado, gridBagConstraints);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Resumen orden de compra.");

        javax.swing.GroupLayout AsignacionesLayout = new javax.swing.GroupLayout(Asignaciones);
        Asignaciones.setLayout(AsignacionesLayout);
        AsignacionesLayout.setHorizontalGroup(
            AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AsignacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AsignacionesLayout.createSequentialGroup()
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AsignacionesLayout.createSequentialGroup()
                                .addComponent(botonActualizarAsignacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buscadorAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filtroAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AsignacionesLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 1100, Short.MAX_VALUE))
                            .addComponent(jScrollPane4))
                        .addGap(18, 18, 18)
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(AsignacionesLayout.createSequentialGroup()
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addGroup(AsignacionesLayout.createSequentialGroup()
                                .addComponent(buscadorProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filtroProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonGuardarAsignacion)))
                        .addGap(18, 18, 18)
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(AsignacionesLayout.createSequentialGroup()
                                .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AsignacionesLayout.createSequentialGroup()
                                        .addComponent(buscadorEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(filtroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        AsignacionesLayout.setVerticalGroup(
            AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AsignacionesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AsignacionesLayout.createSequentialGroup()
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filtroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buscadorEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonGuardarAsignacion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addGroup(AsignacionesLayout.createSequentialGroup()
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscadorProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtroProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AsignacionesLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonActualizarAsignacion)
                            .addComponent(filtroAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscadorAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AsignacionesLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab("Asignacion de Proyectos", Asignaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProyectosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProyectosKeyReleased
        /* if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = tablaProyectos.getSelectedRow();
            try {
                cc = ConexionBD.getcon();
                String SQL = "SELECT * FROM usuarios WHERE id_usuario='" + tablaProyectos.getValueAt(fila, 0) + "'";
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
        }*/
    }//GEN-LAST:event_tablaProyectosKeyReleased

    private void tablaEmpleadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEmpleadosKeyReleased
        /* if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = tablaEmpleados.getSelectedRow();
            try {
                cc = ConexionBD.getcon();
                String SQL = "SELECT id, PO_NO FROM facturacion WHERE id='" + tablaEmpleados.getValueAt(fila, 0) + "'";
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
        }*/
    }//GEN-LAST:event_tablaEmpleadosKeyReleased

    private void tablaAsignacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAsignacionesKeyReleased
        /*if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = tablaAsignaciones.getSelectedRow();
            try {
                cc = ConexionBD.getcon();
                String SQL = "select asignaciones.id_asignacion, asignaciones.fecha_asignacion, \n"
                        + "asignaciones.id_usuario, usuarios.nombre_usuario, usuarios.nombre, \n"
                        + "usuarios.ape_pat, usuarios.ape_mat, asignaciones.id, facturacion.PO_NO, \n"
                        + "asignaciones.orden_compra_dt, asignaciones.importe, asignaciones.total_pagar, \n"
                        + "asignaciones.status_facturacion \n"
                        + "from asignaciones, facturacion, usuarios \n"
                        + "where id_asignacion='" + tablaAsignaciones.getValueAt(fila, 0) + "' \n"
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
        }*/
    }//GEN-LAST:event_tablaAsignacionesKeyReleased

    private void buscadorProyectoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorProyectoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_buscadorProyectoKeyTyped

    private void buscadorEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorEmpleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorEmpleadoKeyTyped

    private void filtroProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroProyectoActionPerformed

    private void botonGuardarAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarAsignacionMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            guardarAsignacion();
            cargarTablaProyectosAsignados();
        }
    }//GEN-LAST:event_botonGuardarAsignacionMouseClicked

    private void tablaAsignacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignacionesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaAsignacionesMouseClicked

    private void botonActualizarAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarAsignacionMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            actualizarAsignacion();
            cargarTablaProyectosAsignados();
        }
    }//GEN-LAST:event_botonActualizarAsignacionMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Asignaciones().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Asignaciones;
    private javax.swing.JButton botonActualizarAsignacion;
    private javax.swing.JButton botonGuardarAsignacion;
    private javax.swing.JTextField buscadorAsignacion;
    private javax.swing.JTextField buscadorEmpleado;
    private javax.swing.JTextField buscadorProyecto;
    private javax.swing.JTextField fechaAsignacion;
    private javax.swing.JComboBox<String> filtroAsignacion;
    private javax.swing.JComboBox<String> filtroEmpleado;
    private javax.swing.JComboBox<String> filtroProyecto;
    private javax.swing.JTextField importe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nombreEmpleado;
    private javax.swing.JTextField poNoCampo;
    private javax.swing.JTextField statusFacturacion;
    private javax.swing.JTable tablaAsignaciones;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
