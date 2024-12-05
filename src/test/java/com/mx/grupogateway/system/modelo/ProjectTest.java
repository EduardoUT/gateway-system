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
import org.junit.jupiter.api.Disabled;
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
class ProjectTest {

    private Project project = new Project();
    private final Set<Project> hashSet = new HashSet<>();

    @DisplayName("Debería lanzar NullPointerException para cada objeto de Project "
            + "con null asignado.")
    @Test
    void testNullPointerExceptionOnProject() {
        NullPointerException e;
        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setProjectId(null);
        });
        assertEquals("projectId no puede ser null.", e.getMessage());

        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setSite(null);

        });
        assertEquals("Site no puede ser null.", e.getMessage());

        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setProjectCode(null);
        });
        assertEquals("projectCode no puede ser null.", e.getMessage());

        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setProjectName(null);
        });
        assertEquals("projectName no puede ser null.", e.getMessage());

        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setCustomer(null);
        });
        assertEquals("projectCustomer no puede ser null.", e.getMessage());

        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setCategory(null);
        });
        assertEquals("projectCategory no puede ser null.", e.getMessage());

        e = assertThrowsExactly(NullPointerException.class, () -> {
            project.setPublishDate(null);
        });
        assertEquals("publishDate no puede ser null.", e.getMessage());
    }

    @DisplayName("Debería aceptar una clave de proyecto dentro del rango")
    @ParameterizedTest()
    @ValueSource(longs = {102517684201L, Long.MAX_VALUE})
    void testProjectId(Long projectId) {
        project.setProjectId(projectId);
        assertTrue(project.getProjectId() > 0);
        assertTrue(project.getProjectId() <= Long.MAX_VALUE);
    }

    @DisplayName("Debería lanzar un IllegalArgumentException cuando se ingresa "
            + "un valor menor o igual a 0 y mayor a Long.MAX_VALUE")
    @Test
    void testProjectIdBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setProjectId(0L);
        });
        assertEquals("id menor o igual a 0, o mayor al limite permitido.",
                e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setProjectId(Long.MIN_VALUE);
        });
        assertEquals("id menor o igual a 0, o mayor al limite permitido.",
                e.getMessage());

        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setProjectId(Long.MAX_VALUE + 1);
        });
        assertEquals("id menor o igual a 0, o mayor al limite permitido.",
                e.getMessage());
    }

    /*
    @DisplayName("Debería aceptar objetos Site validos e irrepetibles"
            + "de acuerdo a su idSite asociado al hashCode")
    @Test
    void testSiteEqualsToAndHashCode() {
        Set hashSet = new HashSet();
        Site siteOne = new Site(200000213693002L);
        Site siteTwo = new Site(200000213138002L);
        Site siteThree = new Site(200000213693002L);
        hashSet.add(siteOne);
        hashSet.add(siteTwo);
        hashSet.add(siteThree);
        assertTrue(hashSet.contains(siteOne));
        assertTrue(hashSet.contains(siteTwo));
        assertTrue(hashSet.contains(siteThree));
        assertTrue(siteOne.equals(siteThree));
    }*/
    @DisplayName("Debería comparar objetos con mismo identificador.")
    @Test
    void testProjectEquals() {
        Project projectB = new Project(62446507901L);
        project.setProjectId(62446507901L);
        assertTrue(project.equals(projectB));
    }

    @DisplayName("Deería comparar objetos con la misma referencia")
    @Test
    void testSameReference() {
        Project projectB = new Project(63555002501L);
        project = projectB;
        assertTrue(project.equals(projectB));
    }

    @DisplayName("Debería comparar objetos cuya referencia sea null.")
    @Test
    void testProjectClassWithNullReference() {
        Project projectB = null;
        assertFalse(project.equals(projectB));
    }

    @DisplayName("Debería aceptar objetos Project validos e irrepetibles "
            + "de acuerdo a su projectId asociado al hashCode")
    @ParameterizedTest
    @MethodSource("projectProvider")
    void testProjectHashCodeOnHashSet(Project otherProject) {
        hashSet.add(otherProject);
        assertTrue(hashSet.contains(otherProject));
        assertEquals(1, hashSet.size());
    }

    static Stream<Project> projectProvider() {
        return Stream.of(
                new Project(63555002501L),
                new Project(63555002501L));
    }
}
