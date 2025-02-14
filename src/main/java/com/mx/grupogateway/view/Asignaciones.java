/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.grupogateway.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.employee.EmployeeController;
import com.mx.grupogateway.purchaseorder.assignment.PurchaseOrderAssignmentController;
import com.mx.grupogateway.purchaseorder.PurchaseOrderController;
import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.project.Project;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
import com.mx.grupogateway.purchaseorder.assignment.PurchaseOrderAssignment;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.util.IconoVentana;
import com.mx.grupogateway.util.TableDataModelUtil;
import com.mx.grupogateway.util.AccionesTabla;
import com.mx.grupogateway.util.ColumnTitlesUtil;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author mcore
 */
public final class Asignaciones extends javax.swing.JFrame {

    private EmployeeController employeeController;
    private PurchaseOrderController purchaseOrderController;
    private PurchaseOrderAssignmentController purchaseOrderAssignmentController;
    private int filaTablaProyectos;
    private int filaTablaEmpleados;
    private int filaTablaAsignaciones;
    private JFrame jFrame;

    public Asignaciones() {
        initComponents();
        iniciarProcesos();
    }

    private void iniciarProcesos() {
        cargarIconoVentana();
        filaTablaProyectos = tablaProyectos.getSelectedRow();
        filaTablaEmpleados = tablaEmpleados.getSelectedRow();
        filaTablaAsignaciones = tablaAsignaciones.getSelectedRow();
        this.employeeController = new EmployeeController();
        this.purchaseOrderController = new PurchaseOrderController();
        this.purchaseOrderAssignmentController = new PurchaseOrderAssignmentController();
        cargarTablaEmpleados();
        cargarTablaProyectos();
        cargarTablaProyectosAsignados();
        selectionRowListener();
        AccionesTabla.filtrarResultados(tablaProyectos, buscadorProyecto, filtroProyecto);
        AccionesTabla.filtrarResultados(tablaEmpleados, buscadorEmpleado, filtroEmpleado);
        AccionesTabla.filtrarResultados(tablaAsignaciones, buscadorAsignacion, filtroAsignacion);
    }

    private void cargarIconoVentana() {
        this.setIconImage(IconoVentana.getIconoVentana());
    }

    private void cargarTablaEmpleados() {
        List<Object[]> empleados = this.employeeController.getDataModelForJTable();
        TableDataModelUtil.loadTableDataModel(
                tablaEmpleados.getModel(),
                tablaEmpleados,
                empleados,
                ColumnTitlesUtil.getColumnTitles(
                        EmpleadoColumnTitles.values())
        );
    }

    private void cargarTablaProyectos() {
        List<Object[]> purchaseOrders = this.purchaseOrderController.getDataModelForJTable();
        TableDataModelUtil.loadTableDataModel(
                tablaProyectos.getModel(),
                tablaProyectos,
                purchaseOrders,
                ColumnTitlesUtil.getColumnTitles(PurchaseOrderColumnTitles.values())
        );
    }

    private void cargarTablaProyectosAsignados() {
        List<Object[]> purchaseOrderAssignments
                = this.purchaseOrderAssignmentController.getDataModelForJTable();
        TableDataModelUtil.loadTableDataModel(
                tablaAsignaciones.getModel(),
                tablaAsignaciones,
                purchaseOrderAssignments,
                ColumnTitlesUtil.getColumnTitles(
                        PurchaseOrderAssignmentColumnTitles.values()
                )
        );
    }

    /**
     * Obtiene la selección de la fila de cada tabla.
     */
    private void selectionRowListener() {
        tablaProyectos.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
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

    /**
     * Crea una nueva asignación de proyecto a un empleado.
     */
    private void guardarAsignacion() {
        if (filaTablaProyectos == -1 || filaTablaEmpleados == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, "
                    + "seleccione la fila de la tabla de proyectos y una de la "
                    + "tabla de empleados.", "Filas no seleccionadas",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Project project = new Project();
            project.setId(Long.valueOf(tablaProyectos.getValueAt(filaTablaProyectos, 0).toString()));
            PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail(
                    tablaProyectos.getValueAt(filaTablaProyectos, 5).toString()
            );
            PurchaseOrder purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withProject(project)
                    .withPurchaseOrderDetail(purchaseOrderDetail)
                    .build();
            PurchaseOrderAssignment purchaseOrderAssignment = new PurchaseOrderAssignment(
                    new Employee(
                            Integer.valueOf(
                                    tablaEmpleados.getValueAt(filaTablaEmpleados, 0).toString()
                            ),
                            tablaEmpleados.getValueAt(filaTablaEmpleados, 1).toString(),
                            tablaEmpleados.getValueAt(filaTablaEmpleados, 2).toString(),
                            tablaEmpleados.getValueAt(filaTablaEmpleados, 3).toString()),
                    purchaseOrder
            );
            this.purchaseOrderAssignmentController.create(purchaseOrderAssignment);
        }
    }

    /**
     * Actualiza el empleado asignado a un proyecto con uno diferente, el
     * progreso del proyecto se mantiene.
     */
    private void actualizarAsignacion() {
        if (filaTablaEmpleados == -1 || filaTablaAsignaciones == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, "
                    + "seleccione la fila de la tabla de empleados y una de la "
                    + "tabla de asignaciones para actualizar la asignación.",
                    "Filas no seleccionadas",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Integer empleadoActualId = Integer.valueOf(tablaAsignaciones.getValueAt(
                    filaTablaAsignaciones, 0).toString());
            Integer empleadoNuevoId = Integer.valueOf(tablaEmpleados.getValueAt(
                    filaTablaEmpleados, 0)
                    .toString());
            String purchaseOrderIdentifier = tablaAsignaciones.getValueAt(
                    filaTablaAsignaciones, 6)
                    .toString();
            PurchaseOrderAssignment purchaseOrderAssignment = new PurchaseOrderAssignment();
            PurchaseOrder purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withPurchaseOrderDetail(new PurchaseOrderDetail(purchaseOrderIdentifier))
                    .build();
            purchaseOrderAssignment.setPurchaseOrder(purchaseOrder);
            if (empleadoActualId.equals(empleadoNuevoId)) {
                JOptionPane.showMessageDialog(null, "Por favor, "
                        + "seleccione un empleado diferente.", "Asignación existente.",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.purchaseOrderAssignmentController.updateAssignment(
                        empleadoActualId, empleadoNuevoId, purchaseOrderAssignment
                );
            }
        }
    }

    private void llenarResumenAsignaciones() {
        String nombre = tablaAsignaciones
                .getValueAt(filaTablaAsignaciones, 1).toString()
                .concat(" ")
                .concat(tablaAsignaciones
                        .getValueAt(filaTablaAsignaciones, 2).toString())
                .concat(" ")
                .concat(tablaAsignaciones
                        .getValueAt(filaTablaAsignaciones, 3).toString());

        nombreEmpleado.setText(nombre);
        poNoCampo.setText(tablaAsignaciones
                .getValueAt(filaTablaAsignaciones, 6).toString()
        );
        fechaAsignacion.setText(tablaAsignaciones.getValueAt(filaTablaAsignaciones, 4).toString());
        importe.setText(tablaAsignaciones.getValueAt(filaTablaAsignaciones, 7).toString());
        total.setText(tablaAsignaciones.getValueAt(filaTablaAsignaciones, 8).toString());
        statusFacturacion.setText(tablaAsignaciones.getValueAt(filaTablaAsignaciones, 9).toString());
    }

    protected void setJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    private JFrame getFacturacion() {
        return this.jFrame;
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
        setTitle("Asignaciones");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1766, 910));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1651, 95));

        Asignaciones.setMinimumSize(new java.awt.Dimension(1651, 854));
        Asignaciones.setName(""); // NOI18N

        buscadorProyecto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        buscadorProyecto.setToolTipText("Buscar proyecto");
        buscadorProyecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscadorProyectoKeyTyped(evt);
            }
        });

        filtroProyecto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        filtroProyecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Project", "PO No" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Seleccione en la tabla el proyecto que desee asignar");

        tablaProyectos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProyectos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProyectos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaProyectos.getTableHeader().setResizingAllowed(false);
        tablaProyectos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaProyectos);

        buscadorEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        buscadorEmpleado.setToolTipText("Buscar Empleado");

        filtroEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        filtroEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Empleado", "IDUsuario", "Nombre", "Cargo" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Seleccione en la tabla el empleado que desee asignar.");

        botonGuardarAsignacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botonGuardarAsignacion.setText("Guardar Asignación");
        botonGuardarAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonGuardarAsignacionMouseClicked(evt);
            }
        });

        jScrollPane4.setAutoscrolls(true);

        tablaAsignaciones.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tablaAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaAsignaciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAsignaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaAsignaciones.getTableHeader().setResizingAllowed(false);
        tablaAsignaciones.getTableHeader().setReorderingAllowed(false);
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
        tablaAsignaciones.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        filtroAsignacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        filtroAsignacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Project", "ID Empleado", "Nombre", "PO No" }));

        buscadorAsignacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        botonActualizarAsignacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botonActualizarAsignacion.setText("Actualizar Asignación");
        botonActualizarAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarAsignacionMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Asignaciones");

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 402));

        tablaEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaEmpleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.getTableHeader().setResizingAllowed(false);
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaEmpleados);
        tablaEmpleados.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(370, 227));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PO NO:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        poNoCampo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        poNoCampo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(poNoCampo, gridBagConstraints);

        importe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        importe.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(importe, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Importe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        total.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        total.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(total, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

        statusFacturacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        statusFacturacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(statusFacturacion, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Status Facturación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel10, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha Asignacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        fechaAsignacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        fechaAsignacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(fechaAsignacion, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre Empleado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 35, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        nombreEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nombreEmpleado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 35);
        jPanel3.add(nombreEmpleado, gridBagConstraints);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
                                .addGap(0, 1096, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
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
                .addContainerGap())
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

    private void tablaAsignacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAsignacionesKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP)
                || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            llenarResumenAsignaciones();
        }
    }//GEN-LAST:event_tablaAsignacionesKeyReleased

    private void buscadorProyectoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorProyectoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_buscadorProyectoKeyTyped

    private void botonGuardarAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarAsignacionMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            guardarAsignacion();
            cargarTablaProyectos();
            cargarTablaProyectosAsignados();
        }
    }//GEN-LAST:event_botonGuardarAsignacionMouseClicked

    private void tablaAsignacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignacionesMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            llenarResumenAsignaciones();
        }
    }//GEN-LAST:event_tablaAsignacionesMouseClicked

    private void botonActualizarAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarAsignacionMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            actualizarAsignacion();
            cargarTablaProyectosAsignados();
        }
    }//GEN-LAST:event_botonActualizarAsignacionMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        evt.getID();
        getFacturacion().setVisible(true);
    }//GEN-LAST:event_formWindowClosed

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
    protected javax.swing.JTable tablaProyectos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
