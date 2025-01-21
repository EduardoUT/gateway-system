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
public interface UpdateEntityDAO<T> {

    /**
     * Update an entity.
     *
     * @param t The entity model.
     */
    void update(T t);
}
