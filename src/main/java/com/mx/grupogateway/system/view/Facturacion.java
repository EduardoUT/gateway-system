/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.grupogateway.system.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.system.controller.ProyectoController;
import com.mx.grupogateway.system.modelo.Proyecto;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.List;

/**
 *
 * @author edu
 */
public final class Facturacion extends javax.swing.JFrame {

    /**
     * Creates new form formulario
     */
    private DefaultTableModel modeloTablaProyectos;
    private final ProyectoController proyectoController;
    private ImportarExcel importarExcel;

    public Facturacion() {
        initComponents();
        this.proyectoController = new ProyectoController();

        cargarTablaProyectos();

        //LlenarTabla();
        //setBackground(new Color(0, 0, 0, 0));
        ColumnasAutoajustadas(tablaFacturacion, margin);
    }

    /*
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/Logo.png"));
        return retValue;

    }*/
    private void cargarTablaProyectos() {

        modeloTablaProyectos = (DefaultTableModel) tablaFacturacion.getModel();

        String[] titulos = {"ID", "Project Code", "Project Name", "Customer", "PO Status", "PO NO", "PO Line NO",
            "Shipment NO", "Site Code", "Site Name", "Item Code",
            "Item Description", "Requested Qty", "Due Qty", "Billed Quantity",
            "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category",
            "Bidding Area", "Publish Date", "Status Cierre", "ID_Usuario", "Nombre", "Apellido Paterno", "Apellido Materno",
            "Orden de Compra DT", "Importe", "Total", "Status Facturación"};

        for (String titulo : titulos) {
            modeloTablaProyectos.addColumn(titulo);
        }

        List<Proyecto> listaProyecto = this.proyectoController.listar();
        listaProyecto.forEach((proyecto) -> {
            modeloTablaProyectos.addRow(
                    new Object[]{
                        proyecto.getIdProyecto(),
                        proyecto.getProjectCode(),
                        proyecto.getProjectName(),
                        proyecto.getCustomer(),
                        proyecto.getPoStatus(),
                        proyecto.getPoNo(),
                        proyecto.getPoLineNo(),
                        proyecto.getShipmentNo(),
                        proyecto.getSiteCode(),
                        proyecto.getSiteName(),
                        proyecto.getItemCode(),
                        proyecto.getItemDesc(),
                        proyecto.getRequestedQty(),
                        proyecto.getDueQty(),
                        proyecto.getBilledQty(),
                        proyecto.getUnitPrice(),
                        proyecto.getLineAmount(),
                        proyecto.getUnit(),
                        proyecto.getPaymentTerms(),
                        proyecto.getCategory(),
                        proyecto.getBiddingArea(),
                        proyecto.getPublishDate()
                    }
            );
        });
    }

    int vColIndex = 1;
    int margin = 2;

    public void ColumnasAutoajustadas(JTable Tabla_Facturacion, int margin) {
        for (int c = 0; c < Tabla_Facturacion.getColumnCount(); c++) {
            packColumnTablaFacturas(Tabla_Facturacion, c, 2);
        }
    }

    public void packColumnTablaFacturas(JTable Tabla_Facturacion, int vColIndex, int margin) {
        //model1 = (DefaultTableModel) jTable1.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) Tabla_Facturacion.getColumnModel();

        TableColumn col = colModel.getColumn(vColIndex);
        int width;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = Tabla_Facturacion.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(Tabla_Facturacion, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < Tabla_Facturacion.getRowCount(); r++) {
            renderer = Tabla_Facturacion.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(Tabla_Facturacion, Tabla_Facturacion.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    public boolean DatosLlenosFacturacion() {

        return !id.getText().equals("") && !pjcode.getText().equals("")
                && !pjname.getText().equals("") && !customer.getText().equals("")
                && !postatus.getText().equals("") && !pon.getText().equals("") && !poline.getText().equals("")
                && !shipment.getText().equals("") && !sitecode.getText().equals("")
                && !sitename.getText().equals("") && !itemcode.getText().equals("")
                && !itemdsc.getText().equals("") && !requestedqty.getText().equals("")
                && !dueqty.getText().equals("") && !billedqty.getText().equals("")
                && !unitprice.getText().equals("") && !amount.getText().equals("")
                && !unit.getText().equals("") && !payment.getText().equals("")
                && !category.getText().equals("") && !bidding.getText().equals("")
                && !pdate.getDateFormatString().equals("");
    }

    public void LimpiarDatos() {
        id.setText("");
        pjcode.setText("");
        pjname.setText("");
        customer.setText("");
        postatus.setText("");
        pon.setText("");
        poline.setText("");
        shipment.setText("");
        sitecode.setText("");
        sitename.setText("");
        itemcode.setText("");
        itemdsc.setText("");
        requestedqty.setText("");
        dueqty.setText("");
        billedqty.setText("");
        unitprice.setText("");
        amount.setText("");
        unit.setText("");
        payment.setText("");
        category.setText("");
        bidding.setText("");
        pdate.setCalendar(null);
    }

    public void Buscar() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        Nombre_UsuarioAdmin = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        ID_Usuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TipBusqueda = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        buscarPO = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaFacturacion = new javax.swing.JTable();
        Fecha_PO = new com.toedter.calendar.JDateChooser();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        numordenc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        contratista = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        ordencompradt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        importe = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        stat = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        stat_cierre = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(getIconImage());
        setLocationByPlatform(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(60, 59, 89));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Bienvenido(a):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(jLabel28, gridBagConstraints);

        Nombre_UsuarioAdmin.setForeground(new java.awt.Color(255, 255, 255));
        Nombre_UsuarioAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Nombre_UsuarioAdmin.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(Nombre_UsuarioAdmin, gridBagConstraints);

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(jLabel33, gridBagConstraints);

        ID_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        ID_Usuario.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(ID_Usuario, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Administrador Facturación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 50);
        jPanel1.add(jLabel2, gridBagConstraints);

        TipBusqueda.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        TipBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        TipBusqueda.setText("Busque el registro deseado en la tabla aquí:");

        update.setText("Actualizar");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("Borrar");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar P.O:");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buscarPO.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        jButton5.setText("Cerrar Sesión");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tablaFacturacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaFacturacion.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        tablaFacturacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaFacturacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaFacturacion.setColumnSelectionAllowed(true);
        tablaFacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaFacturacion.setGridColor(new java.awt.Color(0, 0, 0));
        tablaFacturacion.setSelectionBackground(new java.awt.Color(0, 153, 204));
        tablaFacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaFacturacionMousePressed(evt);
            }
        });
        tablaFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaFacturacionKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tablaFacturacion);

        Fecha_PO.setDateFormatString("yyyy/MM/dd");
        Fecha_PO.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        Fecha_PO.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(60, 59, 89));
        jPanel2.setMaximumSize(new java.awt.Dimension(316, 338));
        jPanel2.setMinimumSize(new java.awt.Dimension(316, 338));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        id.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        id.setToolTipText("ID");
        id.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Id Proyecto"));
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(id, gridBagConstraints);

        pjcode.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        pjcode.setToolTipText("Project Code");
        pjcode.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Código de Proyecto"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(pjcode, gridBagConstraints);

        pjname.setColumns(20);
        pjname.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        pjname.setLineWrap(true);
        pjname.setRows(3);
        pjname.setToolTipText("Project Name");
        pjname.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nombre Proyecto"));
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

        customer.setColumns(20);
        customer.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        customer.setLineWrap(true);
        customer.setRows(3);
        customer.setToolTipText("Customer");
        customer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Cliente"));
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

        postatus.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        postatus.setToolTipText("PO Status");
        postatus.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "PO Status"));
        postatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postatusKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(postatus, gridBagConstraints);

        pon.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        pon.setToolTipText("PO NO");
        pon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "PO NO"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(pon, gridBagConstraints);

        shipment.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        shipment.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Shipment NO"));
        shipment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shipmentKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(shipment, gridBagConstraints);

        poline.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        poline.setToolTipText("PO Line NO");
        poline.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "PO Line NO"));
        poline.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                polineKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel2.add(poline, gridBagConstraints);

        jTabbedPane1.addTab("Información Proyecto", jPanel2);

        jPanel3.setBackground(new java.awt.Color(60, 59, 89));
        jPanel3.setMaximumSize(new java.awt.Dimension(317, 338));
        jPanel3.setMinimumSize(new java.awt.Dimension(317, 338));
        jPanel3.setPreferredSize(new java.awt.Dimension(317, 338));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        sitecode.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        sitecode.setLineWrap(true);
        sitecode.setRows(3);
        sitecode.setBorder(javax.swing.BorderFactory.createTitledBorder("Site Code"));
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

        sitename.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        sitename.setLineWrap(true);
        sitename.setRows(3);
        sitename.setBorder(javax.swing.BorderFactory.createTitledBorder("Site Name"));
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

        itemcode.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        itemcode.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Code"));
        itemcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                itemcodeKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(itemcode, gridBagConstraints);

        itemdsc.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        itemdsc.setLineWrap(true);
        itemdsc.setRows(3);
        itemdsc.setText("\n");
        itemdsc.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Desc"));
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

        requestedqty.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        requestedqty.setBorder(javax.swing.BorderFactory.createTitledBorder("Requested Quantity"));
        requestedqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                requestedqtyKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(requestedqty, gridBagConstraints);

        dueqty.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        dueqty.setBorder(javax.swing.BorderFactory.createTitledBorder("Due Quantity"));
        dueqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dueqtyKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(dueqty, gridBagConstraints);

        billedqty.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        billedqty.setBorder(javax.swing.BorderFactory.createTitledBorder("Billed Quantity"));
        billedqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                billedqtyKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel3.add(billedqty, gridBagConstraints);

        jTabbedPane1.addTab("Detalle Proyecto", jPanel3);

        jPanel4.setBackground(new java.awt.Color(60, 59, 89));
        jPanel4.setPreferredSize(new java.awt.Dimension(326, 336));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        unitprice.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        unitprice.setBorder(javax.swing.BorderFactory.createTitledBorder("Unit Price"));
        unitprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                unitpriceKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(unitprice, gridBagConstraints);

        amount.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        amount.setBorder(javax.swing.BorderFactory.createTitledBorder("Line Amount"));
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(amount, gridBagConstraints);

        unit.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        unit.setBorder(javax.swing.BorderFactory.createTitledBorder("Unit"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 38, 5, 38);
        jPanel4.add(unit, gridBagConstraints);

        payment.setColumns(20);
        payment.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        payment.setLineWrap(true);
        payment.setRows(3);
        payment.setText("\n");
        payment.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Terms"));
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
        category.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        category.setLineWrap(true);
        category.setRows(3);
        category.setText("\n");
        category.setBorder(javax.swing.BorderFactory.createTitledBorder("Category"));
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
        bidding.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        bidding.setLineWrap(true);
        bidding.setRows(3);
        bidding.setText("\n");
        bidding.setBorder(javax.swing.BorderFactory.createTitledBorder("BiddingArea"));
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

        pdate.setBorder(javax.swing.BorderFactory.createTitledBorder("Publish Date"));
        pdate.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        pdate.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
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

        jPanel5.setBackground(new java.awt.Color(60, 59, 89));

        jLabel24.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("No. Orden de Compra:");

        numordenc.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        numordenc.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        numordenc.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Nombre Contratista:");

        contratista.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        contratista.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        contratista.setEnabled(false);

        jLabel25.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Orden de Compra DT:");

        ordencompradt.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        ordencompradt.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        ordencompradt.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Importe:");

        importe.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        importe.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        importe.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Total:");

        total.setEditable(false);
        total.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        total.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        total.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Status:");

        stat.setEditable(false);
        stat.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        stat.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel38.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Status Cierre:");

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("$");

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("%");

        stat_cierre.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        stat_cierre.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        stat_cierre.setEnabled(false);
        stat_cierre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stat_cierreKeyTyped(evt);
            }
        });

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("%");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel24))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(numordenc, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel23))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(contratista, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel25))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(ordencompradt, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel26))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(importe, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel27)
                        .addGap(10, 10, 10)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel39))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel29)
                        .addGap(13, 13, 13)
                        .addComponent(stat, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel40))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel38))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(stat_cierre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel44)))
                .addGap(273, 273, 273))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel24)
                .addGap(5, 5, 5)
                .addComponent(numordenc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel23)
                .addGap(15, 15, 15)
                .addComponent(contratista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel25)
                .addGap(15, 15, 15)
                .addComponent(ordencompradt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel26)
                .addGap(5, 5, 5)
                .addComponent(importe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(stat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(8, 8, 8)
                .addComponent(jLabel38)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stat_cierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)))
        );

        jTabbedPane1.addTab("Detalle Facturación", jPanel5);

        jMenu1.setText("Opciones");

        jMenuItem1.setText("Gestionar Asignaciones");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Archivo");

        jMenuItem2.setText("Importar archivo Excel");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

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
                            .addComponent(jScrollPane9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buscarPO, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(update)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 419, Short.MAX_VALUE))))
                    .addComponent(jButton5)
                    .addComponent(TipBusqueda)
                    .addComponent(Fecha_PO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(buscarPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update)
                            .addComponent(delete))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TipBusqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fecha_PO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentResized

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Buscar();
        ColumnasAutoajustadas(tablaFacturacion, margin);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

    }//GEN-LAST:event_updateActionPerformed

    private void idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_idKeyTyped

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed

    }//GEN-LAST:event_deleteActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tablaFacturacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturacionMousePressed

    }//GEN-LAST:event_tablaFacturacionMousePressed

    private void tablaFacturacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaFacturacionKeyReleased
        if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40) || (evt.getKeyCode() == 33) || (evt.getKeyCode() == 34)) {
            int fila = tablaFacturacion.getSelectedRow();

        }
    }//GEN-LAST:event_tablaFacturacionKeyReleased

    private void stat_cierreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stat_cierreKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_stat_cierreKeyTyped

    private void postatusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postatusKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'A' || car > 'Z')) {
            evt.consume();
        }
    }//GEN-LAST:event_postatusKeyTyped

    private void polineKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_polineKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_polineKeyTyped

    private void shipmentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shipmentKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_shipmentKeyTyped

    private void itemcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemcodeKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_itemcodeKeyTyped

    private void requestedqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_requestedqtyKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_requestedqtyKeyTyped

    private void dueqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dueqtyKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_dueqtyKeyTyped

    private void billedqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billedqtyKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_billedqtyKeyTyped

    private void unitpriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitpriceKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_unitpriceKeyTyped

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_amountKeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        importarExcel = new ImportarExcel();
        importarExcel.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
    private com.toedter.calendar.JDateChooser Fecha_PO;
    public static javax.swing.JLabel ID_Usuario;
    public javax.swing.JLabel Nombre_UsuarioAdmin;
    private javax.swing.JLabel TipBusqueda;
    private javax.swing.JTextField amount;
    private javax.swing.JTextArea bidding;
    private javax.swing.JTextField billedqty;
    private javax.swing.JTextField buscarPO;
    private javax.swing.JTextArea category;
    private javax.swing.JTextField contratista;
    private javax.swing.JTextArea customer;
    private javax.swing.JButton delete;
    private javax.swing.JTextField dueqty;
    private javax.swing.JTextField id;
    private javax.swing.JTextField importe;
    private javax.swing.JTextField itemcode;
    private javax.swing.JTextArea itemdsc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JTextField numordenc;
    private javax.swing.JTextField ordencompradt;
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
    private javax.swing.JTextField stat;
    private javax.swing.JTextField stat_cierre;
    private javax.swing.JTable tablaFacturacion;
    private javax.swing.JTextField total;
    private javax.swing.JTextField unit;
    private javax.swing.JTextField unitprice;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
