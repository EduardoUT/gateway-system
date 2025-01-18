/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.factory;

import com.mx.grupogateway.user.UserController;
import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.employee.category.EmployeeCategory;
import com.mx.grupogateway.user.User;
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
