/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase para la configuración e inizialización del archivo de registro, para el
 * seguimiento de las operaciones con la base de datos y los SwingWorker
 * involucrados.
 *
 * @author eduar
 */
public class GlobalLogger {

    private static final Logger GLOBAL_LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private GlobalLogger() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Realiza la inisialización del archivo de log.
     */
    protected static void setUpLoggerConfigurationFile() {
        LoggerConfigurationFile loggerConfigFile = new LoggerConfigurationFile();
        loggerConfigFile.setUpLoggerFile();
    }

    /**
     * Captura el mensaje y el stackTrace de la excepción configurando el nivel
     * severo.
     *
     * @param message Mensaje personalizado acorde al contexto del error.
     * @param e Exception para poder obtener el stackTrace.
     */
    public static void registerLoggerSevere(String message, Exception e) {
        GLOBAL_LOGGER.log(Level.SEVERE, message, printStackTrace(e));
    }

    /**
     * Impresión de la pila de ejecución donde la excepción fue interceptada.
     *
     * @param e Objeto de tipo Exception, ideal para obtener el detalle de la
     * exceptión captada.
     * @return Fragmento de la pila donde el error ocurrió.
     */
    public static String printStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    /**
     * Clase dedicada a la configuración del archivo de registro.
     */
    private static class LoggerConfigurationFile {

        /**
         * Se configura e inicializa el archivo de registro.
         */
        public final void setUpLoggerFile() {
            try {
                Handler consoleHandler = new ConsoleHandler();
                Handler fileHandler = new FileHandler(
                        "./bitacora.log", true
                );
                SimpleFormatter simpleFormatter = new SimpleFormatter();
                fileHandler.setFormatter(simpleFormatter);
                GLOBAL_LOGGER.addHandler(consoleHandler);
                GLOBAL_LOGGER.addHandler(fileHandler);
                consoleHandler.setLevel(Level.ALL);
                fileHandler.setLevel(Level.ALL);
                GLOBAL_LOGGER.setLevel(Level.ALL);
                GLOBAL_LOGGER.log(Level.INFO, "Log de registro iniciado.");
            } catch (IOException | SecurityException e) {
                GLOBAL_LOGGER.log(Level.SEVERE, printStackTrace(e));
            }
        }
    }
}
