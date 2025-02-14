/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.purchaseorder;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.project.Project;
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
class PurchaseOrderTest {

    private PurchaseOrder purchaseOrder;
    private final Set<PurchaseOrder> hashSet = new HashSet<>();

    @DisplayName("Debería lanzar IllegalArgumentException para cada argumento "
            + "null en PurchaseOrder.")
    @Test
    void testIllegalArgumentExceptionOnPurchaseOrderArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withPurchaseOrderDetail(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());

        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withProject(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withPoLineNo(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withDueQty(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withBilledQty(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withUnit(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withUnitPrice(null)
                    .build();
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería lanzar IllegalArgumentException cuando se ingresa un "
            + "valor menor o igual a 0 o mayor a Long.MAX_VALUE")
    @Test
    void testNumericArgumentBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withPoLineNo(Integer.MIN_VALUE)
                    .build();
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                    .withPoLineNo(Integer.MAX_VALUE + 1)
                    .build();
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería comparar objetos con mismo identificador en Project "
            + "y PurchaseOrderDetail.")
    @Test
    void testPurchaseOrderEquals() {
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail("6081150685-63");
        Project project = new Project(67936906801L);
        PurchaseOrder purchaseOrderB = new PurchaseOrder.PurchaseOrderBuilder()
                .withPurchaseOrderDetail(purchaseOrderDetail)
                .withProject(project)
                .build();
        purchaseOrder = new PurchaseOrder.PurchaseOrderBuilder()
                .withPurchaseOrderDetail(purchaseOrderDetail)
                .withProject(project)
                .build();
        assertTrue(purchaseOrder.equals(purchaseOrderB));
    }

    @DisplayName("Deería comparar objetos con la misma referencia")
    @Test
    void testSameReference() {
        PurchaseOrder purchaseOrderB = new PurchaseOrder.PurchaseOrderBuilder()
                .build();
        purchaseOrder = purchaseOrderB;
        assertTrue(purchaseOrder.equals(purchaseOrderB));
    }

    @DisplayName("Debería comparar objetos cuya referencia sea null.")
    @Test
    void testPurchaseOrderClassWithNullReference() {
        PurchaseOrder purchaseOrderB = null;
        assertFalse(purchaseOrder.equals(purchaseOrderB));
    }

    @DisplayName("Debería aceptar objetos PurchaseOrder validos e irrepetibles "
            + "de acuerdo a su projectId (Project) y "
            + "purchaseOrderIdentifier (PurchaseOrderDetail)")
    @ParameterizedTest
    @MethodSource("purchaseOrderProvider")
    void testPurchaseOrderHashCodeOnHashSet(PurchaseOrder otherPurchaseOrder) {
        hashSet.add(otherPurchaseOrder);
        assertTrue(hashSet.contains(otherPurchaseOrder));
        assertEquals(1, hashSet.size());
    }

    @SuppressWarnings("unused")
    static Stream<PurchaseOrder> purchaseOrderProvider() {
        return Stream.of(
                new PurchaseOrder.PurchaseOrderBuilder()
                        .withPurchaseOrderDetail(new PurchaseOrderDetail("6081150685-63"))
                        .withProject(new Project(67936906801L))
                        .build(),
                new PurchaseOrder.PurchaseOrderBuilder()
                        .withPurchaseOrderDetail(new PurchaseOrderDetail("6081150685-63"))
                        .withProject(new Project(67936906801L))
                        .build()
        );
    }
}
