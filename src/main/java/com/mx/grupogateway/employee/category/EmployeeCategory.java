/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mx.grupogateway.employee.category;

/**
 *
 * @author eduar
 */
public enum EmployeeCategory {
    ADMINISTRADOR("Administrador Principal"),
    FACTURACION("Administrador Facturación");

    private final String employeeCategoryName;

    private EmployeeCategory(String employeeCategoryName) {
        this.employeeCategoryName = employeeCategoryName;
    }

    public String getEmployeeCategoryName() {
        return employeeCategoryName;
    }

    public static EmployeeCategory fromString(String employeeCategory) {
        for (EmployeeCategory e : values()) {
            if (employeeCategory.equals(e.getEmployeeCategoryName())) {
                return e;
            }
        }
        throw new IllegalArgumentException("No se encontro ninguna coincidencia para " + employeeCategory);
    }
}
