/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.crud;

/**
 *
 * @author eduar
 * @param <T> The type of Entity model.
 */
public interface DeleteEntityDAO<T> {
    /**
     * 
     * @param t Deletes a given entity model. 
     */
    void delete(T t);
}
