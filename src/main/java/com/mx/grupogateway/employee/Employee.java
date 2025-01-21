/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import com.mx.grupogateway.employee.category.EmployeeCategory;
import com.mx.grupogateway.user.User;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Employee {

    private static final String MESSAGE_VOID_FIELD = "El campo nombre está vacío.";
    private Integer id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private User user;
    private EmployeeCategory employeeCategory;

    /**
     * Constructor para obtener datos Empleado de BD.
     *
     * @param id
     * @param name
     * @param paternalSurname
     * @param maternalSurname
     * @param employeeCategory
     * @param user
     */
    public Employee(Integer id, String name, String paternalSurname,
            String maternalSurname, User user,
            EmployeeCategory employeeCategory) {
        validarEmpleado(name, paternalSurname, maternalSurname);
        this.id = id;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.user = user;
        this.employeeCategory = employeeCategory;
    }

    /**
     * Constructor para la creación de un empleado en conjunto de un User.
     *
     * @param name
     * @param paternalSurname
     * @param maternalSurname
     * @param employeeCategory
     */
    public Employee(String name, String paternalSurname,
            String maternalSurname, EmployeeCategory employeeCategory) {
        validarEmpleado(name, paternalSurname, maternalSurname);
        this.id = 0;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.employeeCategory = employeeCategory;
        this.user = new User(this);
    }

    /**
     * Constructor para crear un empleado y user.
     *
     * @param name
     * @param paternalSurname
     * @param maternalSurname
     * @param user
     */
    public Employee(String name, String paternalSurname,
            String maternalSurname, User user) {
        validarEmpleado(name, paternalSurname, maternalSurname);
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.user = user;
    }

    /**
     * Constructor para obtener la representación de este objeto en una lista de
     * tipo ProyectoAsignado.
     *
     * @param id
     * @param name
     * @param paternalSurname
     * @param maternalSurname
     */
    public Employee(Integer id, String name, String paternalSurname,
            String maternalSurname) {
        validarEmpleado(name, paternalSurname, maternalSurname);
        this.id = id;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
    }

    /**
     * Constructor para obtener los identificadores de user y categoría.
     *
     * @param user
     * @param employeeCategory
     */
    public Employee(User user, EmployeeCategory employeeCategory) {
        this.user = user;
        this.employeeCategory = employeeCategory;
    }

    /**
     * Constructor para asignar el employeeId.
     *
     * @param id
     */
    public Employee(Integer id) {
        this.id = id;
    }

    /**
     * @return the employeeId
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the paternalSurname
     */
    public String getPaternalSurname() {
        return paternalSurname;
    }

    /**
     * @param paternalSurname the paternalSurname to set
     */
    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    /**
     * @return the maternalSurname
     */
    public String getMaternalSurname() {
        return maternalSurname;
    }

    /**
     * @param maternalSurname the maternalSurname to set
     */
    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the employeeCategory
     */
    public EmployeeCategory getEmployeeCategory() {
        return employeeCategory;
    }

    /**
     * @param employeeCategory the employeeCategory to set
     */
    public void setEmployeeCategory(EmployeeCategory employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

    /**
     * Asigna el employeeId de user al método propio de un objeto user.
     *
     * @param id
     */
    public void setUserId(Integer id) {
        this.user.setId(id);
    }

    /**
     * Valida que el name del empleado este completo.
     *
     * @param name
     * @param paternalSurname
     * @param maternalSurname
     */
    private void validarEmpleado(String name, String paternalSurname,
            String maternalSurname) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_VOID_FIELD);
        }

        if (paternalSurname == null || paternalSurname.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_VOID_FIELD);
        }

        if (maternalSurname == null || maternalSurname.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_VOID_FIELD);
        }
    }

    @Override
    public String toString() {
        return String.format("[ID: %s | Nombre: %s | Apellido P: %s "
                + "| Apellido M: %s | ID Categoría: %s | ID Usuario: %s]",
                this.id, this.getName(), this.getPaternalSurname(), this.getMaternalSurname(),
                this.getEmployeeCategory().getId(),
                this.getUser().getId());
    }
}
