/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import com.mx.grupogateway.user.User;
import com.mx.grupogateway.user.UserService;
import java.util.Optional;

/**
 *
 * @author eduar
 */
public class EmployeeService {

    /**
     * Obtiene a los identificadores de Employee y EmployeeCategory a apartir de
     * las credenciales de un User.
     *
     * @param user User a autenticar.
     * @return Identificadores correspondiantes a Employee y EmployeeCategory.
     */
    public Optional<Employee> getEmployeeIdentifiersByUserAutenticated(User user) {
        UserService userService = new UserService();
        if (userService.authenticate(user)) {
            return userService.getUserImpl().getEmployeeByUser(user);
        }
        return Optional.ofNullable(null);
    }
}
