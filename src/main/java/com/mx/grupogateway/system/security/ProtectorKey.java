/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class PasswordSegura {

    private final String passwordUser;
    private final int ITERACIONES_DEFAULT = 4;

    public PasswordSegura(String passwordUser) {
        if (passwordUser == null || passwordUser.isEmpty()) {
            throw new NullPointerException("La contraseña "
                    + "ingresada está vacía.");
        }

        this.passwordUser = passwordUser;
    }

    /**
     * Convirtiendo String a char array para buen funcionamiento en algoritmo
     * Argon2.
     *
     * @return La contraseña en un char array.
     */
    private char[] passwordUserToCharArray() {
        return this.passwordUser.toCharArray();
    }

    /**
     * Usando algoritmo hash Argon2 para encriptación de contraseñas, este tipo
     * de encriptación es irreversible, para comprobar que la contraseña sea
     * valida es necesario comprobarla con el método booleano
     * verificarPassword()
     *
     * Este método se utiliza para almacenar de forma segura la contraseña
     * registrada por el usuario.
     *
     * @return Una cadena con la contraseña encriptada.
     */
    public String encriptarPassword() {
        Argon2 argon2 = Argon2Factory.create();
        String hash = argon2.hash(
                ITERACIONES_DEFAULT,
                65536,
                1,
                passwordUserToCharArray()
        );
        return hash;
    }

    /**
     * Permite verificar que la contraseña del usuario coincida con el hash.
     *
     * @param hash La contraseña encriptada.
     * @return Verifica si la contraseña ingresada es válida.
     */
    public boolean verificarPassword(String hash) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hash, passwordUserToCharArray());
    }
}
