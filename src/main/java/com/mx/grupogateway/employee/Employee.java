/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.employee;

import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;
import com.mx.grupogateway.employee.category.EmployeeCategory;
import com.mx.grupogateway.user.User;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Employee {

    private Integer id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private EmployeeCategory employeeCategory;
    private User user;

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
            String maternalSurname, EmployeeCategory employeeCategory, User user) {
        validateEmployee(name, paternalSurname, maternalSurname, user, employeeCategory);
        this.id = id;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.employeeCategory = employeeCategory;
        this.user = user;
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
        validateEmployee(name, paternalSurname, maternalSurname);
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.employeeCategory = employeeCategory;
        user = new User(this);
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
        validateEmployee(name, paternalSurname, maternalSurname);
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
        validateUser(user);
        validateEmployeeCategory(employeeCategory);
        this.user = user;
        this.employeeCategory = employeeCategory;
    }

    /**
     * Constructor para asignar el id.
     *
     * @param id
     */
    public Employee(Integer id) {
        validateId(id);
        this.id = id;
    }

    /**
     * @return the employeeId
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        validateId(id);
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
        validateName(name);
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
        validatePaternalSurname(paternalSurname);
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
        validateMaternalSurname(maternalSurname);
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
        validateEmployeeCategory(employeeCategory);
        this.employeeCategory = employeeCategory;
    }

    /**
     * Asigna el employeeId de user al método propio de un objeto user.
     *
     * @param id
     */
    public void setUserId(Integer id) {
        validateId(id);
        this.user.setId(id);
    }

    private void validateId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (id <= 0 || id > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString());
        }
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(NULL_VALUE_OR_EMPTY_MESSAGE.toString());
        }
    }

    private void validatePaternalSurname(String paternalSurname) {
        if (paternalSurname == null || paternalSurname.isEmpty()) {
            throw new IllegalArgumentException(NULL_VALUE_OR_EMPTY_MESSAGE.toString());
        }
    }

    private void validateMaternalSurname(String maternalSurname) {
        if (maternalSurname == null || maternalSurname.isEmpty()) {
            throw new IllegalArgumentException(NULL_VALUE_OR_EMPTY_MESSAGE.toString());
        }
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateEmployeeCategory(EmployeeCategory employeeCategory) {
        if (employeeCategory == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    /**
     * Valida que el name del empleado este completo.
     *
     * @param name
     * @param paternalSurname
     * @param maternalSurname
     */
    private void validateEmployee(String name, String paternalSurname,
            String maternalSurname, User user, EmployeeCategory employeeCategory) {
        validateName(name);
        validatePaternalSurname(paternalSurname);
        validateMaternalSurname(maternalSurname);
        validateUser(user);
        validateEmployeeCategory(employeeCategory);
    }

    private void validateEmployee(String name, String paternalSurname,
            String maternalSurname) {
        validateName(name);
        validatePaternalSurname(paternalSurname);
        validateMaternalSurname(maternalSurname);
    }

    @Override
    public String toString() {
        return String.format("[ID: %s | Nombre: %s | Apellido P: %s "
                + "| Apellido M: %s | Employee Category: %s | ID Usuario: %s]",
                id, name, paternalSurname, maternalSurname,
                employeeCategory.getEmployeeCategoryName(), user.getId()
        );
    }
}
