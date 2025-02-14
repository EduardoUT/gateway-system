/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.purchaseorder.detail.PurchaseOrderDetail;
import com.mx.grupogateway.purchaseorder.PurchaseOrder;
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

    private PurchaseOrder purchaseOrder = new PurchaseOrder();
    private final Set<PurchaseOrder> hashSet = new HashSet<>();

    @DisplayName("Debería comparar objetos con mismo identificador en Project "
            + "y PurchaseOrderDetail.")
    @Test
    void testPurchaseOrderEquals() {
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail("6081150685-63");
        Project project = new Project(67936906801L);
        PurchaseOrder purchaseOrderB = new PurchaseOrder();
        purchaseOrderB.setPurchaseOrderDetail(purchaseOrderDetail);
        purchaseOrderB.setProject(project);
        purchaseOrder.setPurchaseOrderDetail(purchaseOrderDetail);
        purchaseOrder.setProject(project);
        assertTrue(purchaseOrder.equals(purchaseOrderB));
    }

    @DisplayName("Deería comparar objetos con la misma referencia")
    @Test
    void testSameReference() {
        PurchaseOrder purchaseOrderB = new PurchaseOrder();
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

    static Stream<PurchaseOrder> purchaseOrderProvider() {
        return Stream.of(
                new PurchaseOrder(
                        new PurchaseOrderDetail("6081150685-63"),
                        new Project(67936906801L)
                ),
                new PurchaseOrder(
                        new PurchaseOrderDetail("6081150685-63"),
                        new Project(67936906801L)
                )
        );
    }
}
