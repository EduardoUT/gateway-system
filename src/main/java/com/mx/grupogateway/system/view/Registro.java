/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mx.grupogateway.system.view;

import com.mx.grupogateway.system.controller.UsuarioController;
import com.mx.grupogateway.system.modelo.Empleado;
import com.mx.grupogateway.system.modelo.Usuario;
import com.mx.grupogateway.system.security.ProtectorData;
import com.mx.grupogateway.system.view.util.CamposCommonMethods;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 * TODO: Hacer validación que valide que los campos password coincidan.
 * 
 * @author Eduardo Reyes Hernández
 */
public class Registro extends javax.swing.JFrame {

    private final UsuarioController usuarioController;

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        this.usuarioController = new UsuarioController();
        passwordStatusLabel.setVisible(false);
    }

    /**
     * Método que realiza el registro de un usuario que no posea una cuenta.
     *
     * Recibe un Optional con el valor del usuarioId en el objeto Empleado.
     *
     * Si devuelve Optional.empty: El empleado no existe.
     *
     * Si esta presente un valor entero y sea diferente de 0: El empleado ya
     * tiene un usuario asignado.
     *
     * Si esta presente un valor entero y sea igual a 0: Se procede a registrar
     * el usuario asociandolo a ese empleadoID.
     */
    private void registrarUsuario() {
        String empleadoId = campoIdEmpleado.getText();
        if (sonCamposValidosRegistro()) {
            Empleado empleado = new Empleado(empleadoId);
            Optional id = this.usuarioController.consultarIdUsuario(empleado);
            if (id.equals(Optional.empty())) {
                JOptionPane.showMessageDialog(
                        null,
                        "El ID Empleado " + empleadoId + " no existe en la BD.",
                        "Empleado inexistente.",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (id.isPresent() && !id.get().equals(0)) {
                JOptionPane.showMessageDialog(
                        null, "El usuario asociado al ID Empleado " + empleadoId
                        + " ya existe.", "Usuario ya existe",
                        JOptionPane.WARNING_MESSAGE
                );
            }

            if (id.isPresent() && id.get().equals(0)) {
                Usuario usuario = new Usuario(
                        campoNombreUsuario.getText(),
                        ProtectorData.encriptar(
                                campoPassword.getPassword())
                );
                this.usuarioController.guardar(usuario, empleadoId);
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Campos obligatorios, por favor, complete el formulario.",
                    "Formulario incompleto", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean sonCamposValidosRegistro() {
        return !campoIdEmpleado.getText().isEmpty()
                && !campoNombreUsuario.getText().isEmpty()
                && campoPassword.getPassword().length != 0
                && campoCheckPassword.getPassword().length != 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        campoIdEmpleado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoNombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        campoCheckPassword = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        passwordStatusLabel = new javax.swing.JLabel();
        checkBoxVerPassword = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Regresar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLabel6.setText("ID Empleado:");

        jLabel2.setText("Nombre de Usuario:");

        jLabel3.setText("Contraseña:");

        campoPassword.setToolTipText("Mínimo 10 carácteres, debe tener al menos 1 (minúscula, mayúscula, número, carácter especial ! $ % & * ? @)");
        campoPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPasswordKeyTyped(evt);
            }
        });

        jLabel4.setText("Confirmar Contraseña:");

        jButton2.setText("Registrarse");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        passwordStatusLabel.setForeground(new java.awt.Color(204, 0, 0));
        passwordStatusLabel.setText("Status");

        checkBoxVerPassword.setBorder(null);
        checkBoxVerPassword.setContentAreaFilled(false);
        checkBoxVerPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBoxVerPassword.setFocusPainted(false);
        checkBoxVerPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ojoCerrado.png"))); // NOI18N
        checkBoxVerPassword.setRolloverEnabled(false);
        checkBoxVerPassword.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ojoAbierto.png"))); // NOI18N
        checkBoxVerPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxVerPasswordActionPerformed(evt);
            }
        });

        jButton3.setText("?");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBoxVerPassword))
                    .addComponent(campoIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addComponent(campoNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(passwordStatusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoCheckPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        evt.consume();
        this.setVisible(false);
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        evt.consume();
        registrarUsuario();
    }//GEN-LAST:event_jButton2MouseClicked

    private void campoPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPasswordKeyTyped
        CamposCommonMethods.evaluarCampoPassword(campoPassword, passwordStatusLabel);
    }//GEN-LAST:event_campoPasswordKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        evt.consume();
        JOptionPane.showMessageDialog(null, "1. Mínimo 10 carácteres.\n"
                + "2. Al menos una letra mayúscula.\n"
                + "3. Al menos una letra minúsula.\n"
                + "4. Al menos un número.\n"
                + "5. Al menos uno de los siguientes "
                + "carácteres ! $ % & * ? @", "Escribir contraseña segura.",
                JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_jButton3MouseClicked

    private void campoPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPasswordKeyReleased

        CamposCommonMethods.evaluarCampoPassword(campoPassword, passwordStatusLabel);
    }//GEN-LAST:event_campoPasswordKeyReleased

    private void checkBoxVerPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxVerPasswordActionPerformed
        CamposCommonMethods.visualizacionPassword(checkBoxVerPassword, campoPassword);
    }//GEN-LAST:event_checkBoxVerPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField campoCheckPassword;
    private javax.swing.JTextField campoIdEmpleado;
    private javax.swing.JTextField campoNombreUsuario;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JCheckBox checkBoxVerPassword;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel passwordStatusLabel;
    // End of variables declaration//GEN-END:variables

}
