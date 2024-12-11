/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.grupogateway.system.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.system.controller.PurchaseOrderController;
import com.mx.grupogateway.system.modelo.PurchaseOrder;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.controller.TableDataModelUtil;
import com.mx.grupogateway.system.util.IconoVentana;
import com.mx.grupogateway.system.util.MargenTabla;
import com.mx.grupogateway.system.util.AccionesTabla;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author edu
 */
public final class Facturacion extends javax.swing.JFrame {

    private PurchaseOrderController purchaseOrderController;
    private Usuario usuario;

    public Facturacion() {
        initComponents();
        iniciarProcesos();
    }

    private void iniciarProcesos() {
        cargarIconoVentana();
        this.purchaseOrderController = new PurchaseOrderController();
        cargarTablaProyectos();
        AccionesTabla.filtrarResultados(tablaProyectos, buscadorProyecto, filtroProyecto);
    }

    private void cargarIconoVentana() {
        this.setIconImage(IconoVentana.getIconoVentana());
    }

    protected void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    protected void cargarTablaProyectos() {
        tablaProyectos.setModel(new Asignaciones().tablaProyectos.getModel());
        DefaultTableModel modeloTablaProyectos;
        modeloTablaProyectos = (DefaultTableModel) tablaProyectos.getModel();
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderController.listar();
        TableDataModelUtil.loadTableDataModelPurchaseOrders(
                modeloTablaProyectos,
                tablaProyectos,
                purchaseOrders
        );
        MargenTabla.ajustarColumnas(tablaProyectos);
    }

    private Object obtenerValorTabla(int fila, int columna) {
        return tablaProyectos.getValueAt(fila, columna);
    }

    private Date obtenerFechaTabla(int fila, int columna) {
        String campoFecha = obtenerValorTabla(fila, columna)
                .toString().substring(0, 10);
        return Date.valueOf(campoFecha);
    }

    private void llenarCamposFacturacion() {
        int fila = tablaProyectos.getSelectedRow();
        id.setText(obtenerValorTabla(fila, 0).toString());
        pjcode.setText(obtenerValorTabla(fila, 1).toString());
        pjname.setText(obtenerValorTabla(fila, 2).toString());
        customer.setText(obtenerValorTabla(fila, 3).toString());
        postatus.setText(obtenerValorTabla(fila, 4).toString());
        pon.setText(obtenerValorTabla(fila, 5).toString());
        shipment.setText(obtenerValorTabla(fila, 6).toString());
        poline.setText(obtenerValorTabla(fila, 7).toString());
        sitecode.setText(obtenerValorTabla(fila, 8).toString());
        sitename.setText(obtenerValorTabla(fila, 9).toString());
        itemcode.setText(obtenerValorTabla(fila, 10).toString());
        itemdsc.setText(obtenerValorTabla(fila, 11).toString());
        requestedqty.setText(obtenerValorTabla(fila, 12).toString());
        dueqty.setText(obtenerValorTabla(fila, 13).toString());
        billedqty.setText(obtenerValorTabla(fila, 14).toString());
        unitprice.setText(obtenerValorTabla(fila, 15).toString());
        amount.setText(obtenerValorTabla(fila, 16).toString());
        unit.setText(obtenerValorTabla(fila, 17).toString());
        payment.setText(obtenerValorTabla(fila, 18).toString());
        category.setText(obtenerValorTabla(fila, 19).toString());
        bidding.setText(obtenerValorTabla(fila, 20).toString());
        pdate.setDate(obtenerFechaTabla(fila, 21));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buscadorProyecto = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaProyectos = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        id = new javax.swing.JTextField();
        pjcode = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        pjname = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        customer = new javax.swing.JTextArea();
        postatus = new javax.swing.JTextField();
        pon = new javax.swing.JTextField();
        shipment = new javax.swing.JTextField();
        poline = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sitecode = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        sitename = new javax.swing.JTextArea();
        itemcode = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        itemdsc = new javax.swing.JTextArea();
        requestedqty = new javax.swing.JTextField();
        dueqty = new javax.swing.JTextField();
        billedqty = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        unitprice = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        unit = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        payment = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        category = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        bidding = new javax.swing.JTextArea();
        pdate = new com.toedter.calendar.JDateChooser();
        filtroProyecto = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Facturación");
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(getIconImage());
        setLocationByPlatform(true);
        setResizable(false);

        buscadorProyecto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        buscadorProyecto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton5.setText("Cerrar Sesión");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tablaProyectos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaProyectos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tablaProyectos.setForeground(new java.awt.Color(255, 255, 255));
        tablaProyectos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProyectos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaProyectos.setGridColor(new java.awt.Color(0, 0, 0));
        tablaProyectos.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tablaProyectos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tablaProyectos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaProyectos.getTableHeader().setResizingAllowed(false);
        tablaProyectos.getTableHeader().setReorderingAllowed(false);
        tablaProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProyectosMouseClicked(evt);
            }
        });
        tablaProyectos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProyectosKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tablaProyectos);
        tablaProyectos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(316, 338));
        jPanel2.setMinimumSize(new java.awt.Dimension(316, 338));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        id.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        id.setToolTipText("ID");
        id.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Id Proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        id.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(id, gridBagConstraints);

        pjcode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pjcode.setToolTipText("Project Code");
        pjcode.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Código de Proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        pjcode.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(pjcode, gridBagConstraints);

        jScrollPane3.setBackground(new Color(0, 0, 0, 0));

        pjname.setColumns(20);
        pjname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pjname.setLineWrap(true);
        pjname.setRows(3);
        pjname.setToolTipText("Project Name");
        pjname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nombre Proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        pjname.setEnabled(false);
        jScrollPane3.setViewportView(pjname);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jScrollPane1.setBackground(new Color(0, 0, 0, 0));

        customer.setColumns(20);
        customer.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        customer.setLineWrap(true);
        customer.setRows(3);
        customer.setToolTipText("Customer");
        customer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        customer.setEnabled(false);
        jScrollPane1.setViewportView(customer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        postatus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        postatus.setToolTipText("PO Status");
        postatus.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "PO Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        postatus.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(postatus, gridBagConstraints);

        pon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pon.setToolTipText("PO NO");
        pon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "PO NO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        pon.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(pon, gridBagConstraints);

        shipment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        shipment.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Shipment NO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        shipment.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(shipment, gridBagConstraints);

        poline.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        poline.setToolTipText("PO Line NO");
        poline.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "PO Line NO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        poline.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(poline, gridBagConstraints);

        jTabbedPane1.addTab("Información Proyecto", jPanel2);

        jPanel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(317, 338));
        jPanel3.setMinimumSize(new java.awt.Dimension(317, 338));
        jPanel3.setPreferredSize(new java.awt.Dimension(317, 338));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        sitecode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sitecode.setLineWrap(true);
        sitecode.setRows(3);
        sitecode.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Site Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        sitecode.setEnabled(false);
        jScrollPane2.setViewportView(sitecode);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(jScrollPane2, gridBagConstraints);

        sitename.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sitename.setLineWrap(true);
        sitename.setRows(3);
        sitename.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Site Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        sitename.setEnabled(false);
        jScrollPane4.setViewportView(sitename);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(jScrollPane4, gridBagConstraints);

        itemcode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemcode.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        itemcode.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(itemcode, gridBagConstraints);

        itemdsc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemdsc.setLineWrap(true);
        itemdsc.setRows(3);
        itemdsc.setText("\n");
        itemdsc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Desc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        itemdsc.setEnabled(false);
        jScrollPane5.setViewportView(itemdsc);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(jScrollPane5, gridBagConstraints);

        requestedqty.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        requestedqty.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Requested Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        requestedqty.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(requestedqty, gridBagConstraints);

        dueqty.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dueqty.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Due Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        dueqty.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(dueqty, gridBagConstraints);

        billedqty.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        billedqty.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Billed Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        billedqty.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(billedqty, gridBagConstraints);

        jTabbedPane1.addTab("Detalle Proyecto", jPanel3);

        jPanel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(326, 336));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        unitprice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        unitprice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unit Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        unitprice.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(unitprice, gridBagConstraints);

        amount.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        amount.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Line Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        amount.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(amount, gridBagConstraints);

        unit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        unit.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Unit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        unit.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(unit, gridBagConstraints);

        payment.setColumns(20);
        payment.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        payment.setLineWrap(true);
        payment.setRows(3);
        payment.setText("\n");
        payment.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Terms", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        payment.setEnabled(false);
        jScrollPane6.setViewportView(payment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(jScrollPane6, gridBagConstraints);

        category.setColumns(20);
        category.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        category.setLineWrap(true);
        category.setRows(3);
        category.setText("\n");
        category.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        category.setEnabled(false);
        jScrollPane7.setViewportView(category);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(jScrollPane7, gridBagConstraints);

        bidding.setColumns(20);
        bidding.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        bidding.setLineWrap(true);
        bidding.setRows(3);
        bidding.setText("\n");
        bidding.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BiddingArea", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        bidding.setEnabled(false);
        jScrollPane8.setViewportView(bidding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(jScrollPane8, gridBagConstraints);

        pdate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Publish Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        pdate.setDateFormatString("yyyy-MM-dd");
        pdate.setEnabled(false);
        pdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pdate.setMaxSelectableDate(new java.util.Date(253370790095000L));
        pdate.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(pdate, gridBagConstraints);

        jTabbedPane1.addTab("Información Financiera", jPanel4);

        filtroProyecto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        filtroProyecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Proyecto", "Po No" }));

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem1.setText("Gestionar Asignaciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Archivo");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem2.setText("Importar archivo Excel");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Perfil");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem3.setText("Actualizar Contraseña");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem4.setText("Cerrar Sesión");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buscadorProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filtroProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jButton5))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscadorProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(filtroProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        evt.getID();
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        evt.getID();
        ImportarExcel importarExcel = new ImportarExcel();
        importarExcel.setUsuario(usuario);
        this.dispose();
        importarExcel.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tablaProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            llenarCamposFacturacion();
        }
    }//GEN-LAST:event_tablaProyectosMouseClicked

    private void tablaProyectosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProyectosKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP)
                || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            llenarCamposFacturacion();
        }
    }//GEN-LAST:event_tablaProyectosKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        evt.getID();
        this.setVisible(false);
        Asignaciones asignaciones = new Asignaciones();
        asignaciones.setJFrame(this);
        asignaciones.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        evt.getID();
        ActualizarPassword actualizarPassword = new ActualizarPassword();
        actualizarPassword.setUsuario(usuario);
        actualizarPassword.setJFrame(this);
        actualizarPassword.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        evt.getID();
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Facturacion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JTextArea bidding;
    private javax.swing.JTextField billedqty;
    private javax.swing.JTextField buscadorProyecto;
    private javax.swing.JTextArea category;
    private javax.swing.JTextArea customer;
    private javax.swing.JTextField dueqty;
    private javax.swing.JComboBox<String> filtroProyecto;
    private javax.swing.JTextField id;
    private javax.swing.JTextField itemcode;
    private javax.swing.JTextArea itemdsc;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea payment;
    private com.toedter.calendar.JDateChooser pdate;
    private javax.swing.JTextField pjcode;
    private javax.swing.JTextArea pjname;
    private javax.swing.JTextField poline;
    private javax.swing.JTextField pon;
    private javax.swing.JTextField postatus;
    private javax.swing.JTextField requestedqty;
    private javax.swing.JTextField shipment;
    private javax.swing.JTextArea sitecode;
    private javax.swing.JTextArea sitename;
    private javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField unit;
    private javax.swing.JTextField unitprice;
    // End of variables declaration//GEN-END:variables
}
