/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.util;

import java.util.UUID;

/**
 *
 * @author eduar
 */
public class GeneradorUUID {

    private GeneradorUUID() {
    }

    /**
     * Método que generá el identificador único de los empleados creados.
     *
     * @return Cadena aleatoria de 18 carácteres de formato UUID.
     */
    public static String generarIdentificador() {
        return UUID.randomUUID().toString().substring(0, 18);
    }
}
