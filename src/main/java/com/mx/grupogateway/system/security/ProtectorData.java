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
public class ProtectorData {

    private final int ITERACIONES_DEFAULT = 4;

    /**
     * Usando algoritmo hash Argon2 para encriptación de contraseñas, este tipo
     * de encriptación es irreversible, para comprobar que la contraseña sea
     * valida es necesario comprobarla con el método booleano assertData()
     *
     * Este método se utiliza para almacenar de forma segura la contraseña
     * registrada por el usuario.
     *
     * @param data Dato asociado.
     * @return Una cadena con el dato encriptado.
     */
    public String encriptarString(String data) {
        if (data == null || data.isEmpty()) {
            throw new NullPointerException("La clave "
                    + "ingresada está vacía.");
        }
        Argon2 argon2 = Argon2Factory.create();
        return argon2.hash(
                ITERACIONES_DEFAULT,
                65536,
                1,
                data.toCharArray()
        );
    }

    /**
     * Versión alterna que encripta directamente un char[].
     *
     * @param data Dato asociado.
     * @return Una cadena con el dato encriptado.
     */
    public String encriptarArrayCharset(char[] data) {
        if (data == null || data.length == 0) {
            throw new NullPointerException("La clave "
                    + "ingresada está vacía.");
        }
        Argon2 argon2 = Argon2Factory.create();
        return argon2.hash(
                ITERACIONES_DEFAULT,
                65536,
                1,
                data
        );
    }

    /**
     * Permite que la clave ingresada coincida con el hash.
     *
     * @param hash La clave encriptada.
     * @param data Dato asociado..
     * @return Verifica si el dato ingresado coincide con el hash.
     */
    public boolean assertData(String hash, String data) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hash, data.toCharArray());
    }

    /**
     * Permite que la clave ingresada coincida con el hash.
     *
     * @param hash La clave encriptada.
     * @param data Dato asociado.
     * @return Verifica si el dato ingresado coincide con el hash.
     */
    public boolean assertData(String hash, char[] data) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hash, data);
    }
}
