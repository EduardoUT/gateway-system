/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.assignment;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author eduar
 */
class PurchaseOrderAssignmentTest {

    private final PurchaseOrderAssignment purchaseOrderAssignment = new PurchaseOrderAssignment();

    @DisplayName("Debería lanzar IllegalArgumentException para cada argumento "
            + "null en PurchaseOrderAssignment.")
    @Test
    void testIllegalArgumentExceptionOnPurchaserOrderAssignmentArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderAssignment.setEmployee(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderAssignment.setPurchaseOrder(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderAssignment.setAssignmentDate(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderAssignment.setAmount(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderAssignment.setTotalPayment(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderAssignment.setStatus(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }
}
