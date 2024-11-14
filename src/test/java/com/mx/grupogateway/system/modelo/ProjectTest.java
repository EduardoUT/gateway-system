/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author eduar
 */
public class ProjectTest {

    private Project project;

    public ProjectTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        project = new Project();
    }

    @DisplayName("Debería lanzar NullPointerException para cada objeto de Project "
            + "con null asignado.")
    @Test
    public void testNullPointerExceptionOnProject() {
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
    public void testProjectId(Long projectId) {
        project.setProjectId(projectId);
        assertTrue(project.getProjectId() > 0);
        assertTrue(project.getProjectId() <= Long.MAX_VALUE);
    }

    @DisplayName("Debería lanzar un IllegalArgumentException cuando se ingresa "
            + "un valor menor o igual a 0 y mayor a Long.MAX_VALUE")
    @Test
    public void testProjectIdBoundaries() {
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

    @DisplayName("Debería aceptar objetos Site validos e irrepetibles"
            + "de acuerdo a su idSite asociado al hashCode")
    @Test
    public void testSiteEqualsToAndHashCode() {
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
    }

    @AfterEach
    public void tearDown() {
    }

}
