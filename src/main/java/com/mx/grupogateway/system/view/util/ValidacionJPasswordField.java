/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.util.Validaciones;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 * Métodos encargados de validar y actualizar de forma visual al usuario cuando
 * interactua con campos de tipo JPasswordField.
 *
 * @author Eduardo Reyes Hernández
 */
public class ValidacionJPasswordField {

    private ValidacionJPasswordField() {
    }

    /**
     * Realiza una representación visual cuando el usuario escribe en el campo.
     *
     * @param password Campo contraseña.
     * @param jLabel Etiqueta donde se informa el estado de la contraseña.
     */
    public static void evaluarCampoPassword(JPasswordField password,
            JLabel jLabel) {
        if (Validaciones.esPasswordValida(password.getPassword())) {
            statusJlabelOk(jLabel, "Segura");
        } else {
            statusJlabelNotOk(jLabel, "Insegura");
        }
    }

    /**
     * Cambia el estilo de un JLabel cuando este es correcto.
     *
     * @param jLabel Etiqueta donde se informa el estado de la contraseña.
     * @param mensaje Cadena de texto.
     */
    private static void statusJlabelOk(JLabel jLabel, String mensaje) {
        jLabel.setVisible(true);
        jLabel.setText(mensaje);
        jLabel.setForeground(Color.GREEN);
    }

    /**
     * Cambia el estilo de un JLabel cuando este es incorrecto.
     *
     * @param jLabel Etiqueta donde se informa el estado de la contraseña.
     * @param mensaje Cadena de texto.
     */
    private static void statusJlabelNotOk(JLabel jLabel, String mensaje) {
        jLabel.setVisible(true);
        jLabel.setText(mensaje);
        jLabel.setForeground(Color.RED);
    }

    /**
     * Intercambia el formato de texto a asteriscos de un JPasswordField cuando
     * es activado un JCheckBox.
     *
     * @param jCheckBox JCheckBox a ser evaluado.
     * @param password Campo a ser mostrado.
     */
    public static void visualizacionPassword(JCheckBox jCheckBox,
            JPasswordField password) {
        if (jCheckBox.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
    }

    /**
     * Evalúa que coincidan dos campos JPasswordField.
     *
     * @param password Campo JPasswordField.
     * @param passwordConfirm Campo a comparar.
     * @param jLabel Informa el estatus de la contraseña.
     */
    public static void concidePassword(JPasswordField password,
            JPasswordField passwordConfirm, JLabel jLabel) {
        if (esPasswordSimilar(password, passwordConfirm)) {
            statusJlabelOk(jLabel, "Coincide");
        } else {
            statusJlabelNotOk(jLabel, "No coincide");
        }
    }

    /**
     * Evalúa que dos campos JPasswordField sean similares.
     *
     * @param password Campo JPasswordField.
     * @param passwordCheck Campo a comparar.
     * @return
     */
    public static boolean esPasswordSimilar(JPasswordField password,
            JPasswordField passwordCheck) {
        boolean coincidePassword = String.valueOf(password.getPassword())
                .equals(String.valueOf(passwordCheck.getPassword()));
        return coincidePassword;
    }
}
