/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.site;

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
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author eduar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SiteTest {

    private Site site = new Site();
    private final Set<Site> hashSet = new HashSet<>();

    @DisplayName("Debería lanzar IllegalArgumentException para cada argumento "
            + "null en Site.")
    @Test
    void testIllegalArgumentExceptionOnSiteArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setId(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setSiteCode(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setSiteName(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setBiddigArea(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setShipmentNo(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería aceptar identificadores dentro del rango adecuado.")
    @ParameterizedTest()
    @ValueSource(longs = {100000062811002L, Long.MAX_VALUE})
    void testSiteId(Long id) {
        site.setId(id);
        assertTrue(site.getId() > 0);
        assertTrue(site.getId() <= Long.MAX_VALUE);
    }

    @DisplayName("Debería lanzar IllegalArgumentException cuando se ingresa un "
            + "valor menor o igual a 0 o mayor a Long.MAX_VALUE")
    @Test
    void testNumericArgumentBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setId(0L);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setId(Long.MIN_VALUE);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            site.setId(Long.MAX_VALUE + 1);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería comparar objetos con mismo id.")
    @Test
    void testSiteEquals() {
        site.setId(100000062811002L);
        assertTrue(site.equals(new Site(100000062811002L)));
    }

    @DisplayName("Deería comparar objetos con la misma referencia")
    @Test
    void testSameReference() {
        Site siteB = new Site();
        site = siteB;
        assertTrue(site.equals(siteB));
    }

    @DisplayName("Debería comparar objetos cuya referencia sea null.")
    @Test
    void testSiteClassWithNullReference() {
        Site siteB = null;
        assertFalse(site.equals(siteB));
    }

    @DisplayName("Debería aceptar objetos Site validos e irrepetibles "
            + "de acuerdo a su siteId")
    @ParameterizedTest
    @MethodSource("siteProvider")
    void testSiteHashCodeOnHashSet(Site otherSite) {
        hashSet.add(otherSite);
        assertTrue(hashSet.contains(otherSite));
        assertEquals(1, hashSet.size());
    }

    @SuppressWarnings("unused")
    static Stream<Site> siteProvider() {
        return Stream.of(new Site(200000213693002L), new Site(200000213693002L));
    }
}
