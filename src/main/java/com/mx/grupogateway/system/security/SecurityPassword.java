/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Usando algoritmo hash Argon2 para encriptación de datos, este tipo de
 * encriptación es irreversible, para comprobar que el dato sea valido es
 * necesario comprobarlo con el método booleano assertData()
 *
 * @author Eduardo Reyes Hernández
 */
public class SecurityPassword {

    private static final int ITERACIONES_DEFAULT = 4;

    /**
     * Este método se utiliza para almacenar de forma segura el dato ingresado
     * en versión String.
     *
     * @param data Dato asociado.
     * @return Una cadena hash con el dato encriptado.
     */
    public static String encriptar(String data) {
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
     * @return Una cadena hash con el dato encriptado.
     */
    public static String encriptar(char[] data) {
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
     * @param hash Dato encriptado.
     * @param data Dato asociado al hash.
     * @return Verifica si el dato ingresado coincide con el hash.
     */
    public static boolean assertData(String hash, String data) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hash, data.toCharArray());
    }

    /**
     * Permite que la clave ingresada coincida con el hash.
     *
     * @param hash Dato encriptado.
     * @param data Dato asociado al hash.
     * @return Verifica si el dato ingresado coincide con el hash.
     */
    public static boolean assertData(String hash, char[] data) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hash, data);
    }
}
