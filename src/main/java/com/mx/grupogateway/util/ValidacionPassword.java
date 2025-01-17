/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.util;

/**
 * Validaciones de Contraseña segura.
 *
 * @author Eduardo Reyes Hernández
 */
public class ValidacionPassword {

    private ValidacionPassword() {
    }

    /**
     * Permite validar que una contraseña sea segura y cumpla los siguientes
     * requerimientos:
     *
     * 1. Mínimo de 10 carácteres.
     *
     * 2. Al menos una letra minuscula.
     *
     * 3. Al menos una letra mayúscula.
     *
     * 4. Al menos un número.
     *
     * 5. Al menos un carácter especial.
     *
     * @param password Contraseña a evaluar.
     * @return Verdadero en caso cumpla con todas las condiciones anteriores.
     */
    public static boolean esPasswordValida(char[] password) {
        boolean minusculas = false;
        boolean mayusculas = false;
        boolean numero = false;
        boolean caracteresEspeciales = esCaracterEspecialValido(password);

        if (password.length < 10) {
            return false;
        }

        for (char c : password) {
            if (c >= 48 && c <= 57) {
                numero = true;
            }

            if (c >= 65 && c <= 90) {
                mayusculas = true;
            }

            if (c >= 97 && c <= 122) {
                minusculas = true;
            }
        }
        return minusculas && mayusculas && numero && caracteresEspeciales;
    }

    /**
     * Verifica que la contraseña contenga almenos un carácter especial del
     * arrreglo charsEspeciales.
     *
     * @param password Contraseña a evaluar.
     * @return Verdadero si coincide con algún carácter.
     */
    private static boolean esCaracterEspecialValido(char[] password) {
        char[] charsEspeciales = {'!', '$', '%', '&', '*', '?', '@'};
        for (char p : password) {
            for (char cs : charsEspeciales) {
                if (p == cs) {
                    return true;
                }
            }
        }
        return false;
    }
}
