/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.factory;

import com.mx.grupogateway.system.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class TestConnectionFactory {

    public static void main(String[] args) {
        try {
            Connection con = new ConnectionFactory().realizarConexion();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
