/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.crud;

import java.util.List;

/**
 *
 * @author eduar
 * @param <T> The type of Entity model.
 */
public interface GetAllDAO<T>{
    /**
     * 
     * @return A list of entities of the model type.
     */
    List<T> getAll();
}
