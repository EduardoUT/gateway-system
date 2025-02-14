/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.purchaseorder.detail;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
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

    @DisplayName("Debería lanzar IllegalArgumentException para argumentos de tipo "
            + "String cuyo valor sea null o vacío.")
    @Test
    void testIllegalArgumentExceptionOnPurchaseOrderDetailArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setId(null);
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setId("");
        });
        assertEquals(NULL_VALUE_OR_EMPTY_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setPoStatus(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setItemCode(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setItemDesc(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setRequestedQty(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setLineAmount(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setPaymentTerms(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería lanzar IllegalArgumentException cuando se ingresa un "
            + "valor menor a 0 o mayor a Long.MAX_VALUE")
    @Test
    void testNumericArgumentsBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setItemCode(Long.MIN_VALUE);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrderDetail.setItemCode(Long.MAX_VALUE + 1);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería comparar objetos con mismo purchaseOrderDetailIdentifier.")
    @Test
    void testPurchaseOrderDetailEquals() {
        purchaseOrderDetail.setId("6081150685-75");
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

    @SuppressWarnings("unused")
    static Stream<PurchaseOrderDetail> purchaseOrderDetailProvider() {
        return Stream.of(
                new PurchaseOrderDetail("6081153460-21"),
                new PurchaseOrderDetail("6081153460-21")
        );
    }
}
