/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author eduar
 */
public class ExcelDataCellFormatter {

    private ExcelDataCellFormatter() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Método para procesar el tipo de dato genérico según corresponda en la
     * celda a procesar.
     *
     * @param <T> Tipo correspondiente al dato de celda procesado y por defecto.
     * @param row Fila de la hoja de excel.
     * @param numCell Número de celda.
     * @param mapper Función a implementar acorde al tipo de dato a procesar.
     * @param defaultValue Valor por defecto cuando sea vacío o no válido el
     * valor.
     * @return Retorna un genérico acorde a la implementación del tipo de dato.
     */
    private static <T> T getCellValue(Row row, int numCell, Function<String, T> mapper, T defaultValue) {
        return Optional.ofNullable(row.getCell(numCell))
                .map(cell -> cell.toString())
                .map(mapper)
                .orElse(defaultValue);
    }

    /**
     * Obtiene el objeto Row y el número de celda ideal para celdas con dato de
     * tipo texto.
     *
     * @param row
     * @param numCell
     * @return La representación del dato obtenido en un String.
     */
    public static String getCellValueString(Row row, int numCell) {
        return getCellValue(row, numCell, String::valueOf, "N/D");
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para celdas con datos
     * numéicos enteros.
     *
     * @param row
     * @param numCell
     * @return La reprecentación del dato obtenido en un Integer.
     */
    public static Integer getCellValueInteger(Row row, int numCell) {
        return getCellValue(row, numCell, value -> {
            if (value.equals("")) {
                return 0;
            }
            Long number = Math.round(Double.parseDouble(value));
            return number.intValue();
        }, 0);
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para campos con números
     * grandes.
     *
     * @param row
     * @param numCell
     * @return La representación del valor obtenido en un Long.
     */
    public static Long getCellValueLong(Row row, int numCell) {
        return getCellValue(row, numCell, value -> {
            if (value.equals("")) {
                return 0L;
            }
            return BigDecimal.valueOf(Double.parseDouble(value)).longValue();
        }, 0L);
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para datos de tipo
     * decimal.
     *
     * @param row
     * @param numCell
     * @return La representación del dato obrenido en BigDecimal.
     */
    public static BigDecimal getCellValueBigDecimal(Row row, int numCell) {
        return getCellValue(row, numCell, BigDecimal::new, BigDecimal.ZERO);
    }

    /**
     * Obtiene el objeto Row y el número de celda, ideal para celdas con fechas
     * en formato Timestamp.
     *
     * @param row
     * @param numCell
     * @return La representación de una fecha en un LocalDateTime.
     */
    public static LocalDateTime getCellValueTimestamp(Row row, int numCell) {
        return getCellValue(row, numCell, value -> {
            if (value.matches("\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}")) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(row.getCell(numCell).getStringCellValue(), dateTimeFormatter);
            } else {
                return LocalDateTime.now();
            }
        }, LocalDateTime.now());
    }
}
