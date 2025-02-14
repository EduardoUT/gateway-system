/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.user.User;
import com.mx.grupogateway.util.SecurityPassword;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author eduar
 */
class UserTest {

    private final User user = new User();

    public UserTest() {
    }

    @DisplayName("Debería encriptar un usuario")
    @Test
    void testEncryptUser() {
        char[] passwordForTest = {'T', '5', 'e', '2', 'a', 'F', '@', '3', '_'};
        user.setId(1);
        user.setUserName("Johndoe");
        user.setPassword(passwordForTest, true);
        assertTrue(SecurityPassword.assertData(user.getHash(), passwordForTest));
    }

}
