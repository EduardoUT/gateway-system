/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee.category;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class EmployeeCategory {

    private String id;
    private String categoryName;

    /**
     * Constructor para la creación y asignación de una categoría en un nuevo
     * empleado.
     *
     * @param id
     * @param categoryName
     */
    public EmployeeCategory(String id, String categoryName) {
        validarCategoriaEmpleado(id, categoryName);
        this.id = id;
        this.categoryName = categoryName;
    }

    public EmployeeCategory() {
    }

    /**
     * @return the categoriaId
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombreCargo
     */
    public String getCategoryName() {
        return categoryName;
    }

    private void validarCategoriaEmpleado(String id,
            String categoryName) {
        if (id.equals("") || id.isEmpty()) {
            throw new IllegalArgumentException("El campo " + id
                    + " está vacío");
        }

        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("El campo " + categoryName
                    + " está vacío.");
        }
    }

    @Override
    public String toString() {
        return String.format("[ID Categoria: %s | Nombre: %s]",
                this.id,
                this.categoryName);
    }
}
