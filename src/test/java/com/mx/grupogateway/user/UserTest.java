/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.user;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
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

    @DisplayName("Debería lanzar IllegalArgumentException para cada argumento "
            + "null en User.")
    @Test
    void testIllegalArgumentExceptionOnUserArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setId(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setPassword(null, false);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería lanzar IllegalArgumentException para argumentos de tipo "
            + "String cuyo valor sea null o vacío.")
    @Test
    void testIllegalArgumentExceptionOnStringArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setUserName(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setUserName("");
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setPassword(new char[]{}, false);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setHash(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setHash("");
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería lanzar IllegalArgumentException cuando se ingresa un "
            + "valor menor o igual a 0 o mayor a Long.MAX_VALUE")
    @Test
    void testNumericArgumentBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setId(0);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setId(Integer.MIN_VALUE);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setId(Integer.MAX_VALUE + 1);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería encriptar un usuario")
    @Test
    void testEncryptUser() {
        char[] passwordForTest = {'T', '5', 'e', '2', 'a', 'F', '@', '3', '_', '3'};
        user.setId(1);
        user.setUserName("Johndoe");
        user.setPassword(passwordForTest, true);
        assertTrue(SecurityPassword.assertData(user.getHash(), passwordForTest));
    }
    
    @DisplayName("Debería lanzar IllegalArgumentException si la password es menor a 10 "
            + "carácteres.")
    @Test
    void testPasswordValidity() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            user.setPassword(new char[] {'E','t','-','5'}, true);
        });
        assertEquals("Password inválida.", e.getMessage());
    }
}
