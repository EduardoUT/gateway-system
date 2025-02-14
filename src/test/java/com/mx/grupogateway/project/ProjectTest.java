/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mx.grupogateway.project;

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
class ProjectTest {

    private Project project = new Project();
    private final Set<Project> hashSet = new HashSet<>();

    @DisplayName("Debería lanzar IllegalArgumentException para cada argumento "
            + "null en Project.")
    @Test
    void testIllegalArgumentExceptionOnProjectArguments() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setId(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setSite(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setProjectCode(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setProjectName(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setCustomer(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setCategory(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setPublishDate(null);
        });
        assertEquals(NULL_VALUE_MESSAGE.toString(), e.getMessage());
    }

    @DisplayName("Debería aceptar una clave de proyecto dentro del rango")
    @ParameterizedTest()
    @ValueSource(longs = {102517684201L, Long.MAX_VALUE})
    void testProjectId(Long projectId) {
        project.setId(projectId);
        assertTrue(project.getId() > 0);
        assertTrue(project.getId() <= Long.MAX_VALUE);
    }

    @DisplayName("Debería lanzar un IllegalArgumentException cuando se ingresa "
            + "un valor menor o igual a 0 o mayor a Long.MAX_VALUE")
    @Test
    void testProjectIdBoundaries() {
        IllegalArgumentException e;
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setId(0L);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(),
                e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setId(Long.MIN_VALUE);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(),
                e.getMessage());
        e = assertThrowsExactly(IllegalArgumentException.class, () -> {
            project.setId(Long.MAX_VALUE + 1);
        });
        assertEquals(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString(),
                e.getMessage());
    }

    @DisplayName("Debería comparar objetos con mismo identificador.")
    @Test
    void testProjectEquals() {
        Project projectB = new Project(62446507901L);
        project.setId(62446507901L);
        assertTrue(project.getId().equals(projectB.getId()));
    }

    @DisplayName("Debería comparar objetos con la misma referencia")
    @Test
    void testSameReference() {
        Project projectB = new Project(63555002501L);
        project = projectB;
        assertTrue(project.equals(projectB));
    }

    @DisplayName("Debería validar un objeto cuya referencia sea null")
    @Test
    void testProjectClassWithNullReference() {
        Project projectB = null;
        assertFalse(project.equals(projectB));
    }

    @DisplayName("Debería validar dos objetos cuyo nombre de clase sea distinto")
    @Test
    void testProjectClassName() {
        Project projectA = new Project(63555002501L);
        String someString = "";
        assertFalse(projectA.getClass().equals(someString.getClass()));
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

    @SuppressWarnings("unused")
    static Stream<Project> projectProvider() {
        return Stream.of(
                new Project(63555002501L),
                new Project(63555002501L)
        );
    }
}
