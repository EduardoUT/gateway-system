/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mx.grupogateway.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.user.UserController;
import com.mx.grupogateway.user.User;
import com.mx.grupogateway.util.ValidacionJPasswordField;
import com.mx.grupogateway.util.IconoVentana;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Registro extends javax.swing.JFrame {

    private UserController userController;

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        iniciarProcesos();
    }

    private void iniciarProcesos() {
        cargarIconoVentana();
        this.userController = new UserController();
        passwordStatusLabel.setVisible(false);
    }

    private void cargarIconoVentana() {
        this.setIconImage(IconoVentana.getIconoVentana());
    }

    /**
     * Método que realiza el registro de un usuario que no posea una cuenta.
     *
     * Recibe un Optional con el valor del usuarioId en el objeto Empleado.
     *
     * Si devuelve Optional.empty: El empleado no existe.
     *
     * Si esta presente un valor diferente a NULL: El empleado ya tiene un
     * usuario y contraseña asignados.
     */
    private void registrarUsuario() {
        if (sonCamposValidos()) {
            int idUsuario = this.userController.actualizarPasswordNula(new User(
                            Integer.valueOf(campoIdUsuario.getText()),
                            campoNombreUsuario.getText(),
                            String.valueOf(campoCheckPassword.getPassword())
                    ));
            if (idUsuario > 0) {
                JOptionPane.showMessageDialog(null,
                        "Usuario registrado éxitosamente.",
                        "Registro éxitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarRegistros();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Es posible que este usuario no exista en la Base de Datos "
                        + "o ya cuente con una contraseña asignada, verifique "
                        + "con su Administrador.",
                        "Registro anulado.",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Por favor, llene correctamente el formulario..",
                    "Formulario incompleto", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean sonCamposValidos() {
        return !campoIdUsuario.getText().isEmpty()
                && !campoNombreUsuario.getText().isEmpty()
                && campoPassword.getPassword().length != 0
                && campoCheckPassword.getPassword().length != 0
                && ValidacionJPasswordField.esPasswordSimilar(campoPassword,
                        campoCheckPassword);
    }

    private void limpiarRegistros() {
        campoIdUsuario.setText("");
        campoNombreUsuario.setText("");
        campoPassword.setText("");
        campoCheckPassword.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        campoIdUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoNombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        campoCheckPassword = new javax.swing.JPasswordField();
        botonRegistrar = new javax.swing.JButton();
        passwordStatusLabel = new javax.swing.JLabel();
        checkBoxVerPassword = new javax.swing.JCheckBox();
        botonAyuda = new javax.swing.JButton();
        passwordConfirmLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Id Usuario:");

        campoIdUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        campoIdUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoIdUsuarioKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Nombre de Usuario:");

        campoNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Contraseña:");

        campoPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        campoPassword.setToolTipText("Mínimo 10 carácteres, debe tener al menos 1 (minúscula, mayúscula, número, carácter especial ! $ % & * ? @)");
        campoPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoPasswordKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Confirmar Contraseña:");

        campoCheckPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        campoCheckPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoCheckPasswordKeyReleased(evt);
            }
        });

        botonRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botonRegistrar.setText("Registrarse");
        botonRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegistrarMouseClicked(evt);
            }
        });

        passwordStatusLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordStatusLabel.setText("Estado");

        checkBoxVerPassword.setBorder(null);
        checkBoxVerPassword.setContentAreaFilled(false);
        checkBoxVerPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        checkBoxVerPassword.setFocusPainted(false);
        checkBoxVerPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ojoCerrado.png"))); // NOI18N
        checkBoxVerPassword.setRolloverEnabled(false);
        checkBoxVerPassword.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ojoAbierto.png"))); // NOI18N
        checkBoxVerPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxVerPasswordActionPerformed(evt);
            }
        });

        botonAyuda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botonAyuda.setText("?");
        botonAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAyudaMouseClicked(evt);
            }
        });

        passwordConfirmLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordConfirmLabel.setText("Estado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkBoxVerPassword))
                            .addComponent(campoIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addComponent(campoNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(passwordConfirmLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(passwordStatusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoCheckPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonAyuda))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(botonRegistrar)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(checkBoxVerPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordStatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCheckPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAyuda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordConfirmLabel)
                .addGap(18, 18, 18)
                .addComponent(botonRegistrar)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            registrarUsuario();
        }
    }//GEN-LAST:event_botonRegistrarMouseClicked

    private void botonAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAyudaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            JOptionPane.showMessageDialog(null, "1. Mínimo 10 carácteres.\n"
                    + "2. Al menos una letra mayúscula.\n"
                    + "3. Al menos una letra minúsula.\n"
                    + "4. Al menos un número.\n"
                    + "5. Al menos uno de los siguientes "
                    + "carácteres ! $ % & * ? @", "Escribir contraseña segura.",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }//GEN-LAST:event_botonAyudaMouseClicked

    private void campoPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPasswordKeyReleased
        evt.getID();
        ValidacionJPasswordField.evaluarCampoPassword(
                campoPassword, passwordStatusLabel
        );
    }//GEN-LAST:event_campoPasswordKeyReleased

    private void checkBoxVerPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxVerPasswordActionPerformed
        evt.getID();
        ValidacionJPasswordField.visualizacionPassword(
                checkBoxVerPassword, campoPassword
        );
    }//GEN-LAST:event_checkBoxVerPasswordActionPerformed

    private void campoCheckPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCheckPasswordKeyReleased
        evt.getID();
        ValidacionJPasswordField.concidePassword(
                campoPassword,
                campoCheckPassword,
                passwordConfirmLabel
        );
    }//GEN-LAST:event_campoCheckPasswordKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        evt.getID();
        new Login().setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void campoIdUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIdUsuarioKeyPressed
        char c = evt.getKeyChar();
        if (Character.isDigit(c) || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE
                || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            campoIdUsuario.setEditable(true);
        } else {
            campoIdUsuario.setEditable(false);
        }
    }//GEN-LAST:event_campoIdUsuarioKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Registro().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAyuda;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JPasswordField campoCheckPassword;
    private javax.swing.JTextField campoIdUsuario;
    private javax.swing.JTextField campoNombreUsuario;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JCheckBox checkBoxVerPassword;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel passwordConfirmLabel;
    private javax.swing.JLabel passwordStatusLabel;
    // End of variables declaration//GEN-END:variables
}
