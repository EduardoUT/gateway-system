/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.user;

import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.util.SecurityPassword;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class User {

    private Integer id;
    private String userName;
    private char[] password;
    private String hash;

    public User() {
        id = 0;
    }

    /**
     * Constructor para actualizar una password nula.
     *
     * @param id
     * @param userName
     * @param password
     */
    public User(Integer id, String userName,
            char[] password) {
        this.id = id;
        this.userName = userName;
        this.hash = encriptarPass(password);
    }

    /**
     * Constructor para crear un nuevo usuario con una clave de seguridad
     * generada por defecto.
     *
     * @param userName
     * @param password
     */
    public User(String userName, char[] password) {
        this.userName = userName;
        this.hash = encriptarPass(password);
    }

    /**
     * Constructor usado para listar un usuario con id y nombre.
     *
     * @param id
     * @param userName
     */
    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    /**
     * Constructor para crear un nuevo Usuario con nombre de usuario,
     * identificador y sin password desde un objeto Employee.
     *
     * @param employee Recibe un objeto Employee para crear el nombre de Usuario
     * con el nombre del empleado.
     */
    public User(Employee employee) {
        this.id = 0;
        this.userName = generarNombreUsuario(
                employee.getName(),
                employee.getPaternalSurname(),
                employee.getMaternalSurname()
        );
        this.hash = "NULL";
    }

    /**
     * Constructor para asignar el identificador de un usuario.
     *
     * @param id
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * @return the usuarioId
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the usuarioId to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Este método recibe la password para encriptar, si el valor no requiere
     * ser encriptado asignar false.
     *
     * @param password the password to set
     * @param encrypt true para encriptar.
     */
    public void setPassword(char[] password, boolean encrypt) {
        if (encrypt) {
            this.setHash(encriptarPass(password));
        } else {
            this.password = password;
        }
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     *
     * @param employeeName
     * @param paternalSurname
     * @param maternalSurname
     * @return Genera el nombre del usuario apartir de su nombre y apellidos.
     */
    private String generarNombreUsuario(String employeeName,
            String paternalSurname, String maternalSurname) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(employeeName.substring(0, 1))
                .append(paternalSurname)
                .append(maternalSurname.substring(0, 1));
        String user = stringBuilder.toString().toLowerCase();
        this.setUserName(user);
        return this.userName;
    }

    /**
     * Encripta la contraseña.
     *
     * @param password Contraseña a encriptar.
     * @return Hash.
     */
    private String encriptarPass(char[] password) {
        return SecurityPassword.encriptar(password);
    }

    @Override
    public String toString() {
        return String.format("[ID: %d | Nombre Usuario: %s ]",
                this.getId(),
                this.userName
        );
    }
}
