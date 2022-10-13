/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mx.grupogateway.system.view;

import com.mx.grupogateway.system.controller.EmpleadoCargoController;
import com.mx.grupogateway.system.controller.EmpleadoController;
import com.mx.grupogateway.system.controller.UsuarioController;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.EmpleadoCargo;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.view.util.TablaColumnasAutoajustables;
import com.mx.grupogateway.system.view.util.TableCommonMethods;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Gestion extends javax.swing.JFrame {

    private DefaultTableModel modeloTablaEmpleado;
    private DefaultTableModel modeloTablaUsuario;
    private DefaultComboBoxModel modeloComboBoxCargoEmpleado;
    private final TablaColumnasAutoajustables tablaColumnasAutoajustables;
    private final EmpleadoController empleadoController;
    private final EmpleadoCargoController empleadoCargoController;
    private final UsuarioController usuarioController;

    /**
     * Creates new form Gestion
     */
    public Gestion() {
        initComponents();
        this.empleadoController = new EmpleadoController();
        this.empleadoCargoController = new EmpleadoCargoController();
        this.usuarioController = new UsuarioController();
        this.tablaColumnasAutoajustables = new TablaColumnasAutoajustables();
        configurarFormularioEmpleado();
    }

    private void configurarFormularioEmpleado() {
        cargarTablaEmpleado();
        cargarTablaUsuario();
        tablaColumnasAutoajustables.autoajustarColumnas(tablaUsuario);
        tablaColumnasAutoajustables.autoajustarColumnas(tablaEmpleado);
        botonGuardar.setVisible(false);
        botonCancelarNuevoRegistro.setVisible(false);
        configurarComboBoxEmpleado();
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

            EmpleadoCargo empleadoCargo
                    = (EmpleadoCargo) empleadoCargos.getSelectedItem();
            this.empleadoController.guardar(
                    empleado,
                    empleadoCargo.getCargoId()
            );

            JOptionPane.showMessageDialog(null, "Empleado guardado "
                    + "éxitosamente.");
            tablaEmpleado.setVisible(true);
            botonGuardar.setVisible(false);
            botonCancelarNuevoRegistro.setVisible(false);
            limpiarCamposFormularioEmpleado();
            TableCommonMethods.limpiarTabla(
                    modeloTablaEmpleado, tablaEmpleado);
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
        List<Empleado> listaEmpleado = this.empleadoController.listar();
        listaEmpleado.forEach((empleado) -> {
            modeloTablaEmpleado.addRow(
                    new Object[]{
                        empleado.getIdEmpleado(),
                        empleado.getNombre(),
                        empleado.getApellidoPaterno(),
                        empleado.getApellidoMaterno(),
                        empleado.getCargoId(),
                        empleado.getUsuarioId()
                    }
            );
        });
    }

    /**
     * Método que recopila los datos adquiridos de la tablaEmpleado pasados por
     * parámetro al método modificar() del EmpleadoController.
     */
    private void actualizarEmpleado() {
        if (sonCamposValidosEmpleado()) {

            int lineasActualizadas;
            lineasActualizadas = this.empleadoController.modificar(
                    TableCommonMethods.obtenerUUID(tablaEmpleado, 0),
                    campoNombre.getText(),
                    campoApellidoP.getText(),
                    campoApellidoM.getText(),
                    empleadoCargos.getSelectedIndex()
            );

            JOptionPane.showMessageDialog(null, lineasActualizadas
                    + " registro actualizado exitosamente.");
            limpiarCamposFormularioEmpleado();
            TableCommonMethods.limpiarTabla(
                    modeloTablaEmpleado, tablaEmpleado);
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
                .eliminar(TableCommonMethods.obtenerUUID(tablaEmpleado, 0));
        JOptionPane.showMessageDialog(null, cantidadEliminada
                + " registro eliminado exitosamente.");
        limpiarCamposFormularioEmpleado();
        TableCommonMethods.limpiarTabla(modeloTablaEmpleado, tablaEmpleado);
        cargarTablaEmpleado();
    }

    /**
     * Pasa los registros de la tablaEmpleado al formulario para ser editados.
     */
    private void llenarCamposFormularioFromTablaEmpleado() {
        int fila = TableCommonMethods.indiceFilaSeleccionada(tablaEmpleado);
        if (TableCommonMethods.filaEstaSeleccionada(tablaEmpleado)) {
            campoNombre.setText(
                    String.valueOf(tablaEmpleado.getValueAt(fila, 1))
            );
            campoApellidoP.setText(
                    String.valueOf(tablaEmpleado.getValueAt(fila, 2))
            );
            campoApellidoM.setText(
                    String.valueOf(tablaEmpleado.getValueAt(fila, 3))
            );
            empleadoCargos.setSelectedIndex(
                    Integer.valueOf(tablaEmpleado.getValueAt(fila, 4)
                            .toString())
            );
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
        modeloComboBoxCargoEmpleado.addElement(
                new EmpleadoCargo(0, "Seleccione un Cargo")
        );

        List<EmpleadoCargo> listaCargos = this.empleadoCargoController
                .listar();

        listaCargos.forEach((empleadoCargo) -> {
            modeloComboBoxCargoEmpleado.addElement(empleadoCargo);
        });
    }

    /**
     * Obteniendo List de tipo Usuario de UsuarioController y asignando
     * registros en la TablaUsuario.
     */
    private void cargarTablaUsuario() {
        modeloTablaUsuario = (DefaultTableModel) tablaUsuario.getModel();
        List<Usuario> listaUsuario = this.usuarioController.listar();
        listaUsuario.forEach((usuario) -> {
            modeloTablaUsuario.addRow(
                    new Object[]{
                        usuario.getUsuarioId(),
                        usuario.getNombreUsuario(),
                        usuario.getClaveSeguridad()
                    }
            );
        });
    }

    /**
     * Recopila el identificador del usuario de la tabla tablaUsuario y lo pasa
     * por parámetro en el método eliminar() del UsuarioController.
     */
    private void eliminarUsuario() {
        int cantidadEliminada = this.usuarioController
                .eliminar(TableCommonMethods.obtenerID(tablaUsuario, 0));
        JOptionPane.showMessageDialog(null, cantidadEliminada
                + " registro eliminado exitosamente.");
        TableCommonMethods.limpiarTabla(modeloTablaUsuario, tablaUsuario);
        TableCommonMethods.limpiarTabla(modeloTablaEmpleado, tablaEmpleado);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        botonEliminarUsuario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Apellido Paterno:");

        jLabel4.setText("Apellido Materno:");

        tablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido Paterno", "Apellido Materno", "ID Cargo", "ID Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEmpleado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaEmpleado.setFocusable(false);
        tablaEmpleado.getTableHeader().setReorderingAllowed(false);
        tablaEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadoMouseClicked(evt);
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
                    .addComponent(empleadoCargos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(campoNombre)
                                    .addComponent(campoApellidoP)
                                    .addComponent(campoApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addComponent(botonCancelarNuevoRegistro)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botonActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonNuevoRegistro)
                            .addComponent(botonGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCancelarNuevoRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonActualizar)
                            .addComponent(botonEliminar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro Empleado", jPanel1);

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
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
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gestión de Usuarios", jPanel2);

        jButton1.setText("Perfil");

        jButton2.setText("Cerrar Sesión");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEliminarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarUsuarioMouseClicked
        if (TableCommonMethods.filaEstaSeleccionada(tablaUsuario)) {
            eliminarUsuario();
        }
    }//GEN-LAST:event_botonEliminarUsuarioMouseClicked

    private void botonCancelarNuevoRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCancelarNuevoRegistroMouseClicked
        evt.consume();
        limpiarCamposFormularioEmpleado();
        botonGuardar.setVisible(false);
        botonCancelarNuevoRegistro.setVisible(false);
        tablaEmpleado.setVisible(true);
    }//GEN-LAST:event_botonCancelarNuevoRegistroMouseClicked

    private void botonNuevoRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoRegistroMouseClicked
        evt.consume();
        limpiarCamposFormularioEmpleado();
        TableCommonMethods.limpiarSeleccion(tablaEmpleado);
        botonGuardar.setVisible(true);
        botonCancelarNuevoRegistro.setVisible(true);
        tablaEmpleado.setVisible(false);
    }//GEN-LAST:event_botonNuevoRegistroMouseClicked

    private void botonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseClicked
        evt.consume();
        if (TableCommonMethods.filaEstaSeleccionada(tablaEmpleado)) {
            eliminarEmpleado();
        }
    }//GEN-LAST:event_botonEliminarMouseClicked

    private void botonActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarMouseClicked
        evt.consume();
        if (TableCommonMethods.filaEstaSeleccionada(tablaEmpleado)) {
            actualizarEmpleado();
        }
    }//GEN-LAST:event_botonActualizarMouseClicked

    private void botonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseClicked
        evt.consume();
        guardarEmpleado();
    }//GEN-LAST:event_botonGuardarMouseClicked

    private void tablaEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadoMouseClicked
        evt.consume();
        llenarCamposFormularioFromTablaEmpleado();
    }//GEN-LAST:event_tablaEmpleadoMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion().setVisible(true);
            }
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
