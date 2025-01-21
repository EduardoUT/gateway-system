/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.crud;

/**
 *
 * @author eduar
 * @param <T> The type of Entity model.
 * @param <I> The type of id.
 */
public interface GetByIdDAO<T, I> {

    /**
     *
     * @param id The id of the identity type.
     * @return An entity of the model.
     */
    T getEntityById(I id);
}
