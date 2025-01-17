/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.config;

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
 *
 * @author eduar
 */
public class LoggerConfig {

    private static final Logger LOG_RAIZ = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private LoggerConfig() {
        throw new IllegalStateException("Utility class");
    }

    public static final void config() {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler(
                    "./bitacora.log", true
            );
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOG_RAIZ.addHandler(consoleHandler);
            LOG_RAIZ.addHandler(fileHandler);
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOG_RAIZ.setLevel(Level.ALL);
            LOG_RAIZ.log(Level.INFO, "Log de registro iniciado.");
        } catch (IOException e) {
            LOG_RAIZ.log(Level.SEVERE, LoggerConfig.getStackTrace(e));
        } catch (SecurityException e) {
            LOG_RAIZ.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * @return the rootPackagePath
     */
    public static Logger getLogger() {
        return LOG_RAIZ;
    }

    public static String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
