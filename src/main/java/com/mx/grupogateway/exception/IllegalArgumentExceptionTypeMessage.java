/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mx.grupogateway.exception;

/**
 * Enum con mensajes para excepciones de tipo IllegalArgumentException.
 *
 * @author eduar
 */
public enum IllegalArgumentExceptionTypeMessage {
    NULL_VALUE_MESSAGE("Argumento null propocionado."),
    LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE("Argumento es menor o igual a cero, "
            + "o excede el límite máximo."),
    NULL_VALUE_OR_EMPTY_MESSAGE("Argumento null o vacío.");

    private final String messageValue;

    private IllegalArgumentExceptionTypeMessage(String messageValue) {
        this.messageValue = messageValue;
    }

    @Override
    public String toString() {
        return messageValue;
    }
}
