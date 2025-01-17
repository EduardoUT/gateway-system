/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

/**
 *
 * Utilizando patrón de diseño Factory para crear un pool de conexiones de
 * máximo 10.
 *
 * Al ser utilizada esta clase en distintos puntos del proyecto, para conectarse
 * a la base de datos.
 *
 * @author Eduardo Reyes Hernández
 */
public class ConnectionFactory {

    private static final Logger logger = Logger.getLogger("com.mx.grupogateway.system.factory.ConnectionFactory");
    private final DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/gateway?"
                + "useTimeZone=true&serverTimeZone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("12345");
        comboPooledDataSource.setMaxPoolSize(10);
        this.dataSource = comboPooledDataSource;
    }

    public Connection realizarConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al conectar con la Base de Datos "
                    + "verificar estado de conexion del servicio MySQL: {0}",
                    e.getMessage());
            int option = JOptionPane.showOptionDialog(
                    null,
                    "Error al intentar conectar con la Base de Datos.",
                    "Error en Base de Datos.",
                    JOptionPane.YES_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new Object[]{"Aceptar"},
                    0
            );
            if (option == 0 || option == -1) {
                System.exit(0);
            }
            return null;
        }
    }
}
