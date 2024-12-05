/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author eduar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PurchaseOrderDetailTest {

    private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
    private final Set<PurchaseOrderDetail> hashSet = new HashSet<>();

    @DisplayName("Debería comparar objetos con mismo purchaseOrderDetailIdentifier.")
    @Test
    void testPurchaseOrderDetailEquals() {
        purchaseOrderDetail.setPurchaseOrderDetailIdentifier("6081150685-75");
        assertTrue(purchaseOrderDetail.equals(new PurchaseOrderDetail("6081150685-75")));
    }

    @DisplayName("Deería comparar objetos con la misma referencia")
    @Test
    void testSameReference() {
        PurchaseOrderDetail purchaseOrderDetailB = new PurchaseOrderDetail();
        purchaseOrderDetail = purchaseOrderDetailB;
        assertTrue(purchaseOrderDetail.equals(purchaseOrderDetailB));
    }

    @DisplayName("Debería comparar objetos cuya referencia sea null.")
    @Test
    void testSiteClassWithNullReference() {
        PurchaseOrderDetail purchaseOrderDetailB = null;
        assertFalse(purchaseOrderDetail.equals(purchaseOrderDetailB));
    }

    @DisplayName("Debería aceptar objetos PurchaseOrderDetail validos e irrepetibles "
            + "de acuerdo a su purchaseOrderDetailIdentifier")
    @ParameterizedTest
    @MethodSource("purchaseOrderDetailProvider")
    void testSiteHashCodeOnHashSet(PurchaseOrderDetail otherPurchaseOrderDetail) {
        hashSet.add(otherPurchaseOrderDetail);
        assertTrue(hashSet.contains(otherPurchaseOrderDetail));
        assertEquals(1, hashSet.size());
    }

    static Stream<PurchaseOrderDetail> purchaseOrderDetailProvider() {
        return Stream.of(
                new PurchaseOrderDetail("6081153460-21"),
                new PurchaseOrderDetail("6081153460-21")
        );
    }
}
