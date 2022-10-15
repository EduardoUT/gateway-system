/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.security.Validaciones;
import java.awt.Color;
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
            jLabel.setVisible(true);
            jLabel.setText("Buena");
            jLabel.setForeground(Color.GREEN);
        } else {
            jLabel.setVisible(true);
            jLabel.setText("Mala");
            jLabel.setForeground(Color.RED);
        }
    }
    
    public static void visualizacionPassword(JCheckBox jCheckBox, 
            JPasswordField password) {
        if (jCheckBox.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
    }
}
