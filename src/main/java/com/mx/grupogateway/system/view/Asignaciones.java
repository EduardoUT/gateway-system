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
import com.mx.grupogateway.system.view.util.IconoVentana;
import com.mx.grupogateway.system.view.util.MargenTabla;
import com.mx.grupogateway.system.view.model.TableDataModelAsignacion;
import com.mx.grupogateway.system.view.model.TableDataModelEmpleado;
import com.mx.grupogateway.system.view.model.TableDataModelProyecto;
import com.mx.grupogateway.system.view.util.AccionesTabla;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mcore
 */
public final class Asignaciones extends javax.swing.JFrame {

    private DefaultTableModel modeloTablaProyectos;
    private DefaultTableModel modeloTablaEmpleados;
    private DefaultTableModel modeloTablaAsignaciones;
    private TableDataModelProyecto tableDataModelProyecto;
    private TableDataModelEmpleado tableDataModelEmpleado;
    private TableDataModelAsignacion tableDataModelAsignacion;
    private ProyectoController proyectoController;
    private EmpleadoController empleadoController;
    private ProyectoAsignadoController proyectosAsignadosController;
    private LinkedList<ProyectoAsignado> filtroProyectosAsignados;
    private List<Proyecto> listaProyectos;
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
        this.proyectoController = new ProyectoController();
        this.empleadoController = new EmpleadoController();
        this.proyectosAsignadosController = new ProyectoAsignadoController();
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
        modeloTablaEmpleados = (DefaultTableModel) tablaEmpleados.getModel();
        List<Empleado> empleados = this.empleadoController.listar();
        tableDataModelEmpleado = new TableDataModelEmpleado(
                modeloTablaEmpleados, tablaEmpleados, empleados
        );
        tableDataModelEmpleado.cargarModeloTablaEmpleados();
        MargenTabla.ajustarColumnas(tablaEmpleados);
    }

    private void cargarTablaProyectos() {
        modeloTablaProyectos = (DefaultTableModel) tablaProyectos.getModel();
        listaProyectos = this.proyectoController.listar();
        tableDataModelProyecto = new TableDataModelProyecto(
                modeloTablaProyectos, tablaProyectos, listaProyectos
        );
        tableDataModelProyecto.cargarModeloTablaProyecto();
        MargenTabla.ajustarColumnas(tablaProyectos);
    }

    private void cargarTablaProyectosAsignados() {
        modeloTablaAsignaciones = (DefaultTableModel) tablaAsignaciones.getModel();
        List<ProyectoAsignado> listaProyectosAsignados
                = this.proyectosAsignadosController.listar();
        tableDataModelAsignacion = new TableDataModelAsignacion(
                modeloTablaAsignaciones, tablaAsignaciones, listaProyectosAsignados);
        tableDataModelAsignacion.cargarModeloTablaAsignaciones();
        MargenTabla.ajustarColumnas(tablaAsignaciones);
    }

    /**
     * Obtiene la selección de la fila de cada tabla.
     */
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
            String idEmpleadoAsignado = tablaAsignaciones.getValueAt(
                    filaTablaAsignaciones, 0).toString();
            String idEmpleado = tablaEmpleados.getValueAt(
                    filaTablaEmpleados, 0)
                    .toString();
            String poNo = tablaAsignaciones.getValueAt(
                    filaTablaAsignaciones, 6)
                    .toString();
            if (idEmpleadoAsignado.equals(idEmpleado)) {
                JOptionPane.showMessageDialog(null, "Por favor, "
                        + "seleccione un empleado diferente.", "Asignación existente.",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                int filasActualizadas = this.proyectosAsignadosController.actualizar(
                        idEmpleado, poNo, idEmpleadoAsignado
                );
                System.out.println(filasActualizadas);
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
        filtroProyecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Proyecto", "Po No" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Seleccione en la tabla el proyecto que desee asignar");

        tablaProyectos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Proyecto", "Project Code", "Project Name", "Customer", "Po Status", "Po No", "Po Line No", "Shipment No", "Site Code", "Site Name", "Item Code", "Item Description", "Requested Qty", "Due Qty", "Billed Qty", "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category", "Bidding Area", "Publish Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProyectos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProyectos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaProyectos.getTableHeader().setResizingAllowed(false);
        tablaProyectos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaProyectos);

        buscadorEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        buscadorEmpleado.setToolTipText("Buscar Empleado");

        filtroEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        filtroEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Empleado", "Id Usuario", "Nombre", "Cargo" }));

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
                "Id Empleado", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha Asignación", "Id Proyecto", "Po No", "Importe", "Total Pagar", "Status", "Customer", "Project Name", "Po Status", "Po Line No", "Site Code", "Site Name", "Item Desc", "Requested Qty", "Due Qty", "Billed Qty", "Unit Price", "Line Amount", "Unit", "Payment Terms", "Category", "Publish Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAsignaciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaAsignaciones.setColumnSelectionAllowed(true);
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
        filtroAsignacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id Proyecto", "Id Empleado", "Nombre", "Po No" }));

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
                "Id Empleado", "Nombre", "Apellido Paterno", "Apellido Materno", "Id Usuario", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEmpleados.setColumnSelectionAllowed(true);
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
