/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.mx.grupogateway.crud.DataModelForJTable;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UserController implements DataModelForJTable {

    private final UserService userService;

    public UserController() {
        userService = new UserService();
    }

    /**
     * Almacena un nuevo usuario y obtiene el identificador generado si la
     * ejecución fue exitosa, devuelve -1 en caso contrario.
     *
     * @param user
     * @return Identificador del usuario guardado.
     */
    public int create(User user) {
        return userService.create(user);
    }

    /**
     * Recibe un List de tipo User para construir el modelo de filas de un
     * JTable.
     *
     * @return Lista de Object[] con los datos del User a mostrar en el
     * JTable.
     */
    @Override
    public List<Object[]> getDataModelForJTable() {
        List<User> users = userService.getAll();
        List<Object[]> dataModelUsers = new ArrayList<>();
        if (!users.isEmpty()) {
            for (User usuario : users) {
                dataModelUsers.add(
                        new Object[]{
                            usuario.getId(),
                            usuario.getUserName(),
                            usuario.getHash()
                        }
                );
            }
            return dataModelUsers;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Permite comprobar y validar que un empleado tenga una cuenta de usuario
     * existente.
     *
     * @param id
     * @return Optional de tipo User.
     */
    public Optional<User> getEntityById(Integer id) {
        return userService.getEntityById(id);
    }

    /**
     * Consulta si la password existe en la BD y es válida y no conrenga el
     * valor por defecto NULL al crearse un nuevo empleado.
     *
     * @param user
     * @return Devuelve el status del servicio para la consulta del password
     * actual.
     */
    public boolean isCurrentPasswordValid(User user) {
        return userService.isCurrentPasswordValid(user);
    }

    /**
     * Actualiza la password acorde al id, nombre y password dados por el
     * usuario
     *
     * @param user
     * @return Status de la operaión.
     */
    public int updateNullPassword(User user) {
        return userService.updateNullPassword(user);
    }

    /**
     * Actualiza la password actual.
     *
     * @param user Credenciales del user.
     * @return Status de la operación.
     */
    public int updatePassword(User user) {
        return userService.updatePassword(user);
    }

    /**
     * Elimina el usuario como referencia de la tablaUsuario.
     *
     * @param user
     * @return
     */
    public int delete(User user) {
        return userService.delete(user);
    }
}
