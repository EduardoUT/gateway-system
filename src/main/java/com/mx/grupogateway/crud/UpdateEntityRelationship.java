/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.grupogateway.crud;

/**
 *
 * @author eduar
 * @param <T> Tipo de la entidad.
 */
public interface UpdateEntityRelationship<T> {

    /**
     * Se recomienda invocar este método cuando se elimina una entidad con
     * claves foraneas.
     *
     * @param entity Entidad cuya relación será actualizada en la base de datos.
     */
    void updateEntityRelationship(T entity);
}
