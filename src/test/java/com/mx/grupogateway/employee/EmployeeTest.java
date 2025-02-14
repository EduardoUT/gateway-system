/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.employee;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author eduar
 */
class EmployeeTest {

    private final Employee employee = new Employee(1);

    @DisplayName("Debería lanzar IllegalArgumentException para cada argumento "
            + "null en Employee.")
    @Test
    void testIllegalArgumentExceptionOnEmployeeArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setId(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setUserId(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setEmployeeCategory(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería lanzar IllegalArgumentException para argumentos de tipo "
            + "String cuyo valor sea null o vacío.")
    @Test
    void testIllegalArgumentExceptionOnStringArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setName(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setName("");
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setPaternalSurname(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setPaternalSurname("");
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setMaternalSurname(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setMaternalSurname("");
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setName(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setName(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería lanzar IllegalArgumentException cuando se ingresa un "
            + "valor menor o igual a 0 o mayor a Long.MAX_VALUE")
    @Test
    void testNumericArgumentBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setId(0);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setId(Integer.MIN_VALUE);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            employee.setId(Integer.MAX_VALUE + 1);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería aceptar identificadores dentro del rango adecuado.")
    @ParameterizedTest()
    @ValueSource(ints = {5, Integer.MAX_VALUE})
    void testId(Integer id) {
        employee.setId(id);
        assertTrue(employee.getId() > 0);
        assertTrue(employee.getId() <= Integer.MAX_VALUE);
    }
}
