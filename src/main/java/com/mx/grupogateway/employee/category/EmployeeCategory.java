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

    private Integer id;
    private String categoryName;

    /**
     * Constructor para la creación y asignación de una categoría en un nuevo
     * empleado.
     *
     * @param id
     * @param categoryName
     */
    public EmployeeCategory(Integer id, String categoryName) {
        validarCategoriaEmpleado(id, categoryName);
        this.id = id;
        this.categoryName = categoryName;
    }

    public EmployeeCategory() {
    }

    /**
     * @return the categoriaId
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombreCargo
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private void validarCategoriaEmpleado(Integer id,
            String categoryName) {
        if (id <= 0 && id > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("El campo " + id
                    + " está fuera de rango");
        }

        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("El campo " + categoryName
                    + " está vacío.");
        }
    }

    @Override
    public String toString() {
        return String.format("[ID Categoria: %d | Nombre: %s]",
                this.id,
                this.categoryName);
    }
}
