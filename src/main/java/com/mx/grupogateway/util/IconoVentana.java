/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.util;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Posee el objeto Image con la representación de la imagen que se usa en las
 * ventanas del proyecto.
 *
 * @author eduar
 */
public class IconoVentana {

    private IconoVentana() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Image getIconoVentana() {
        return Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("Imagenes/Logo.png"));
    }
}
