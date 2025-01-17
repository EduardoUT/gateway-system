/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.util;

/**
 *
 * @author eduar
 */
public class ColumnTitlesUtil {

    private ColumnTitlesUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String[] getColumnTitles(Enum[] columnTitles) {
        String[] stringColumnTitles = new String[columnTitles.length];
        for (int i = 0; i < stringColumnTitles.length; i++) {
            stringColumnTitles[i] = columnTitles[i].toString();
        }
        return stringColumnTitles;
    }
}
