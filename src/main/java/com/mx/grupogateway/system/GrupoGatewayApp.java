/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mx.grupogateway.system.view.Login;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class GrupoGatewayApp {

    public static void main(String[] args) {
        LoggerConfig.config();
        FlatDarkLaf.setup();
        Login login = new Login();
        login.setVisible(true);
    }
}
