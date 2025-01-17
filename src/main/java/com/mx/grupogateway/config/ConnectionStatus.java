/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.config;

import java.sql.Connection;

/**
 *
 * @author eduar
 */
public abstract class ConnectionStatus {

    private boolean statusConnection;
    private Connection con;

    protected ConnectionStatus(Connection con) {
        if (con != null) {
            setStatusConnection(true);
            this.con = con;
        } else {
            setStatusConnection(false);
        }
    }

    /**
     * Se establece el status de la conexión.
     *
     * @param statusConnection
     */
    private void setStatusConnection(boolean statusConnection) {
        this.statusConnection = statusConnection;
    }

    /**
     *
     * @return Status de conexión.
     */
    public boolean isStatusConnectionNotActive() {
        return this.statusConnection;
    }

    /**
     * @return Objeto Connection.
     */
    public Connection getConnection() {
        return con;
    }

}
