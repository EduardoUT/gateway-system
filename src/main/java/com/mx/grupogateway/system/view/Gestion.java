/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mx.grupogateway.system.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.mx.grupogateway.system.controller.EmpleadoCategoriaController;
import com.mx.grupogateway.system.controller.EmpleadoController;
import com.mx.grupogateway.system.controller.UsuarioController;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.EmpleadoCategoria;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.view.util.TablaColumnasAutoajustables;
import com.mx.grupogateway.system.view.util.TableDataModelEmpleado;
import com.mx.grupogateway.system.view.util.TableDataModelUsuario;
import com.mx.grupogateway.system.view.util.TableMethods;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Gestion extends javax.swing.JFrame {

    private DefaultTableModel modeloTablaEmpleado;
    private DefaultTableModel modeloTablaUsuario;
    private DefaultComboBoxModel modeloComboBoxCargoEmpleado;
    private TableDataModelEmpleado tableDataModelEmpleado;
    private TableDataModelUsuario tableDataModelUsuario;
    private EmpleadoController empleadoController;
    private EmpleadoCategoriaController empleadoCargoController;
    private UsuarioController usuarioController;

    /**
     * Creates new form Gestion
     */
    public Gestion() {
        initComponents();
        iniciarProcesos();
    }

    private void iniciarProcesos() {
        this.empleadoController = new EmpleadoController();
        this.empleadoCargoController = new EmpleadoCategoriaController();
        this.usuarioController = new UsuarioController();
        configurarFormularioEmpleado();
        cargarTablaUsuario();
    }

    private void configurarFormularioEmpleado() {
        cargarTablaEmpleado();
        botonGuardar.setVisible(false);
        botonCancelarNuevoRegistro.setVisible(false);
        configurarComboBoxEmpleado();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/Logo.png"));
        return retValue;
    }

    /**
     * Construyendo objeto de tipo Empleado y pasandolo por parámetro en el
     * método guardar() del EmpleadoController.
     */
    private void guardarEmpleado() {
        if (sonCamposValidosEmpleado()) {
            Empleado empleado = new Empleado(
                    campoNombre.getText(),
                    campoApellidoP.getText(),
                    campoApellidoM.getText()
            );

            EmpleadoCategoria empleadoCargo
                    = (EmpleadoCategoria) empleadoCargos.getSelectedItem();
            this.empleadoController.guardar(
                    empleado,
                    empleadoCargo.getCategoriaId()
            );

            JOptionPane.showMessageDialog(null, "Empleado guardado "
                    + "éxitosamente.");
            tablaEmpleado.setVisible(true);
            botonGuardar.setVisible(false);
            botonCancelarNuevoRegistro.setVisible(false);
            limpiarCamposFormularioEmpleado();
            cargarTablaEmpleado();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Campos obligatorios, por favor, complete el formulario.",
                    "Formulario incompleto", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Obteniendo List de tipo Empleado del método listar() del
     * EmpleadoController y asignando registros en la tablaEmpleado.
     */
    private void cargarTablaEmpleado() {
        modeloTablaEmpleado = (DefaultTableModel) tablaEmpleado.getModel();
        List<Empleado> empleados = this.empleadoController.listar();
        tableDataModelEmpleado = new TableDataModelEmpleado(
                modeloTablaEmpleado, tablaEmpleado, empleados
        );
        tableDataModelEmpleado.cargarModeloTablaEmpleados();
        TablaColumnasAutoajustables.autoajustarColumnas(tablaEmpleado);
    }

    /**
     * Método que recopila los datos adquiridos de la tablaEmpleado pasados por
     * parámetro al método modificar() del EmpleadoController.
     */
    private void actualizarEmpleado() {
        if (sonCamposValidosEmpleado()) {

            int lineasActualizadas;
            lineasActualizadas = this.empleadoController
                    .modificar(
                            TableMethods
                                    .obtenerUUID(tablaEmpleado, 0),
                            campoNombre.getText(),
                            campoApellidoP.getText(),
                            campoApellidoM.getText(),
                            String.valueOf(
                                    empleadoCargos.getSelectedIndex()
                            )
                    );

            JOptionPane.showMessageDialog(null, lineasActualizadas
                    + " registro actualizado exitosamente.");
            limpiarCamposFormularioEmpleado();
            cargarTablaEmpleado();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Campos obligatorios, por favor, complete el formulario.",
                    "Formulario incompleto", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Recopila el identificador del empleado de la tabla tablaEmpleado y lo
     * pasa por parámetro en el método eliminar() del EmpleadoController.
     */
    private void eliminarEmpleado() {
        int cantidadEliminada = this.empleadoController
                .eliminar(TableMethods.obtenerUUID(tablaEmpleado, 0));
        JOptionPane.showMessageDialog(null, cantidadEliminada
                + " registro eliminado exitosamente.");
        limpiarCamposFormularioEmpleado();
        TableMethods.limpiarTabla(modeloTablaEmpleado, tablaEmpleado);
        cargarTablaEmpleado();
    }

    /**
     * Pasa los registros de la tablaEmpleado al formulario para ser editados.
     */
    private void llenarCamposFormularioFromTablaEmpleado() {
        int fila = TableMethods.indiceFila(tablaEmpleado);
        if (TableMethods.filaEstaSeleccionada(tablaEmpleado)) {
            campoNombre.setText(
                    String.valueOf(tablaEmpleado.getValueAt(fila, 1))
            );
            campoApellidoP.setText(
                    String.valueOf(tablaEmpleado.getValueAt(fila, 2))
            );
            campoApellidoM.setText(
                    String.valueOf(tablaEmpleado.getValueAt(fila, 3))
            );
            empleadoCargos.setSelectedItem(tablaEmpleado.getValueAt(fila, 5)
                    .toString());
        }
    }

    /**
     * Evalúa si los campos del formulario son llenados correctamente.
     *
     * @return boolean
     */
    private boolean sonCamposValidosEmpleado() {
        return !campoNombre.getText().isEmpty()
                && !campoApellidoP.getText().isEmpty()
                && !campoApellidoM.getText().isEmpty()
                && empleadoCargos.getSelectedIndex() != 0;
    }

    private void limpiarCamposFormularioEmpleado() {
        campoNombre.setText("");
        campoApellidoP.setText("");
        campoApellidoM.setText("");
        empleadoCargos.setSelectedIndex(0);
    }

    /**
     * Configura las opciones que se obtendrán de la List de tipo EmpleadoCargo
     * del empleadoCargoController.
     *
     * La primer opción es la selección por default con valor "Seleccione un
     * Cargo"
     */
    private void configurarComboBoxEmpleado() {
        modeloComboBoxCargoEmpleado = (DefaultComboBoxModel) empleadoCargos.getModel();
        modeloComboBoxCargoEmpleado.addElement("Seleccione un cargo");
        List<EmpleadoCategoria> listaCargos = this.empleadoCargoController
                .listar();
        listaCargos.forEach((empleadoCargo) -> {
            modeloComboBoxCargoEmpleado.addElement(empleadoCargo.getNombreCategoria());
        });
    }

    /**
     * Obteniendo List de tipo Usuario de UsuarioController y asignando
     * registros en la TablaUsuario.
     */
    private void cargarTablaUsuario() {
        modeloTablaUsuario = (DefaultTableModel) tablaUsuario.getModel();
        List<Usuario> usuarios = this.usuarioController.listar();
        tableDataModelUsuario = new TableDataModelUsuario(modeloTablaUsuario, tablaUsuario, usuarios);
        tableDataModelUsuario.cargarModeloTablaUsuario();
        TablaColumnasAutoajustables.autoajustarColumnas(tablaUsuario);
    }

    /**
     * Recopila el identificador del usuario de la tabla tablaUsuario y lo pasa
     * por parámetro en el método eliminar() del UsuarioController.
     */
    private void eliminarUsuario() {
        int cantidadEliminada = this.usuarioController
                .eliminar(String.valueOf(TableMethods
                        .obtenerID(tablaUsuario, 0)));
        JOptionPane.showMessageDialog(null, cantidadEliminada
                + " registro eliminado exitosamente.");
        TableMethods.limpiarTabla(modeloTablaUsuario, tablaUsuario);
        TableMethods.limpiarTabla(modeloTablaEmpleado, tablaEmpleado);
        cargarTablaUsuario();
        cargarTablaEmpleado();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        botonEliminarUsuario = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoApellidoM = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleado = new javax.swing.JTable();
        botonGuardar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        campoNombre = new javax.swing.JTextField();
        campoApellidoP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        empleadoCargos = new javax.swing.JComboBox<>();
        botonNuevoRegistro = new javax.swing.JButton();
        botonCancelarNuevoRegistro = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Getsión de Personal");
        setIconImage(getIconImage());
        setResizable(false);

        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre de Usuario", "Clave de Seguridad"
            }
        ));
        tablaUsuario.setFocusable(false);
        tablaUsuario.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaUsuario);

        botonEliminarUsuario.setText("Eliminar Usuario ");
        botonEliminarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarUsuarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(botonEliminarUsuario)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEliminarUsuario)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gestión de Usuarios", jPanel2);

        jLabel3.setText("Apellido Paterno:");

        jLabel4.setText("Apellido Materno:");

        tablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Id Usuario", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEmpleado.getTableHeader().setReorderingAllowed(false);
        tablaEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadoMouseClicked(evt);
            }
        });
        tablaEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaEmpleadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEmpleado);

        botonGuardar.setText("Guardar");
        botonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonGuardarMouseClicked(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarMouseClicked(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarMouseClicked(evt);
            }
        });

        jLabel2.setText("Nombre:");

        jLabel5.setText("Cargo:");

        botonNuevoRegistro.setText("Nuevo Registro");
        botonNuevoRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevoRegistroMouseClicked(evt);
            }
        });

        botonCancelarNuevoRegistro.setText("Cancelar Registro");
        botonCancelarNuevoRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCancelarNuevoRegistroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(empleadoCargos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(campoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                    .addComponent(campoApellidoP)
                                    .addComponent(campoApellidoM)))
                            .addComponent(jLabel5)
                            .addComponent(botonCancelarNuevoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(botonNuevoRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonActualizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empleadoCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonNuevoRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCancelarNuevoRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonEliminar)
                            .addComponent(botonActualizar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Registro Empleado", jPanel1);

        jButton1.setText("Perfil");

        jButton2.setText("Cerrar Sesión");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonEliminarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarUsuarioMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (TableMethods.filaEstaSeleccionada(tablaUsuario)) {
                eliminarUsuario();
            }
        }
    }//GEN-LAST:event_botonEliminarUsuarioMouseClicked

    private void botonCancelarNuevoRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCancelarNuevoRegistroMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            limpiarCamposFormularioEmpleado();
            botonGuardar.setVisible(false);
            botonCancelarNuevoRegistro.setVisible(false);
            tablaEmpleado.setVisible(true);
        }
    }//GEN-LAST:event_botonCancelarNuevoRegistroMouseClicked

    private void botonNuevoRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoRegistroMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            limpiarCamposFormularioEmpleado();
            TableMethods.limpiarSeleccion(tablaEmpleado);
            botonGuardar.setVisible(true);
            botonCancelarNuevoRegistro.setVisible(true);
        }
    }//GEN-LAST:event_botonNuevoRegistroMouseClicked

    private void botonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (TableMethods.filaEstaSeleccionada(tablaEmpleado)) {
                eliminarEmpleado();
            }
        }
    }//GEN-LAST:event_botonEliminarMouseClicked

    private void botonActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (TableMethods.filaEstaSeleccionada(tablaEmpleado)) {
                actualizarEmpleado();
            }
        }
    }//GEN-LAST:event_botonActualizarMouseClicked

    private void botonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            guardarEmpleado();
        }
    }//GEN-LAST:event_botonGuardarMouseClicked

    private void tablaEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadoMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            llenarCamposFormularioFromTablaEmpleado();
        }
    }//GEN-LAST:event_tablaEmpleadoMouseClicked

    private void tablaEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEmpleadoKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP)
                || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            llenarCamposFormularioFromTablaEmpleado();
        }
    }//GEN-LAST:event_tablaEmpleadoKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarculaLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Gestion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonCancelarNuevoRegistro;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonEliminarUsuario;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonNuevoRegistro;
    private javax.swing.JTextField campoApellidoM;
    private javax.swing.JTextField campoApellidoP;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JComboBox<String> empleadoCargos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaEmpleado;
    private javax.swing.JTable tablaUsuario;
    // End of variables declaration//GEN-END:variables
}
