/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import com.mx.grupogateway.site.Site;
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
class SiteTest {

    private Site site = new Site();
    private final Set<Site> hashSet = new HashSet<>();

    @DisplayName("Debería comparar objetos con mismo siteId.")
    @Test
    void testSiteEquals() {
        site.setSiteId(100000062811002L);
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

    static Stream<Site> siteProvider() {
        return Stream.of(new Site(200000213693002L), new Site(200000213693002L));
    }
}
