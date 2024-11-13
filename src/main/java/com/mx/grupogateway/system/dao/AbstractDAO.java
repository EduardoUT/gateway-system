/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.system.util;

import java.sql.Connection;

/**
 *
 * @author eduar
 */
public abstract class AbstractDAO {

    private boolean statusConnection;
    private Connection con;

    protected AbstractDAO(Connection con) {
        if (con != null) {
            setStatusConnection(true);
            this.con = con;
        } else {
            setStatusConnection(false);
        }
    }

    private void setStatusConnection(boolean statusConnection) {
        this.statusConnection = statusConnection;
    }

    public boolean isStatusConnectionNotActive() {
        return this.statusConnection;
    }

    /**
     * @return the con
     */
    public Connection getConnection() {
        return con;
    }
    
}
