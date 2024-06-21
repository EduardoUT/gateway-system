/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.security.Validaciones;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class CamposCommonMethods {

    public static void evaluarCampoPassword(JPasswordField password,
            JLabel jLabel) {
        if (Validaciones.esPasswordValida(password.getPassword())) {
            statusJlabelOk(jLabel, "Segura");
        } else {
            statusJlabelNotOk(jLabel, "Insegura");
        }
    }

    private static void statusJlabelOk(JLabel jLabel, String mensaje) {
        jLabel.setVisible(true);
        jLabel.setText(mensaje);
        jLabel.setForeground(Color.GREEN);
    }

    private static void statusJlabelNotOk(JLabel jLabel, String mensaje) {
        jLabel.setVisible(true);
        jLabel.setText(mensaje);
        jLabel.setForeground(Color.RED);
    }

    public static void visualizacionPassword(JCheckBox jCheckBox,
            JPasswordField password) {
        if (jCheckBox.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
    }

    public static void concidePassword(JPasswordField password,
            JPasswordField passwordConfirm, JLabel jLabel) {
        boolean coincidePassword = String.valueOf(password.getPassword())
                .equals(String.valueOf(passwordConfirm.getPassword()));
        if (coincidePassword) {
            statusJlabelOk(jLabel, "Coincide");
        } else {
            statusJlabelNotOk(jLabel, "No coincide");
        }
    }
}
