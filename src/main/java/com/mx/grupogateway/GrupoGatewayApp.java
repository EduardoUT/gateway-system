/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.view.Login;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class GrupoGatewayApp {

    public static void main(String[] args) {
        GlobalLogger.setUpLoggerConfigurationFile();
        FlatDarkLaf.setup();
        Login login = new Login();
        login.setVisible(true);
    }
}
