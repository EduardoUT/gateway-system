/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.user;

import com.mx.grupogateway.user.UserDAO;
import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class UserController {

    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO(
                new ConnectionFactory().realizarConexion()
        );
    }

    public boolean isConnectionStatusNotActive() {
        return this.userDAO.isStatusConnectionNotActive();
    }

    /**
     * Almacena un nuevo usuario y obtiene el identificador generado si la
     * ejecución fue exitosa, devuelve -1 en caso contrario.
     *
     * @param user
     * @return Identificador del usuario guardado.
     */
    public int guardar(User user) {
        return this.userDAO.guardar(user);
    }

    /**
     *
     * @return List de tipo User.
     */
    public List<Object[]> listar() {
        List<User> users = this.userDAO.listar();
        List<Object[]> dataModelUsers = new ArrayList<>();
        if (!users.isEmpty()) {
            for (User usuario : users) {
                dataModelUsers.add(
                        new Object[]{
                            usuario.getId(),
                            usuario.getUserName(),
                            usuario.getClaveSeguridad()
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
     */
    public Integer consultarIdUsuario(Integer id) {
        return this.userDAO.consultarIdUsuario(id);
    }

    /**
     * Consulta si la password existe en la BD y es válida y no conrenga el
     * valor por defecto NULL al crearse un nuevo empleado.
     *
     * @param user
     * @return
     */
    public boolean esPasswordValida(User user) {
        return this.userDAO.esPasswordValida(user);
    }

    /**
     * Vslida si una password contiene NULL en la BD.
     *
     * @param id
     * @return
     */
    public boolean esPasswordNula(Integer id) {
        return this.userDAO.esPasswordNula(id);
    }

    /**
     * Obtiene los identificadores de Categoría de empleado e identificador de
     * usuario.
     *
     * @param user
     * @return
     */
    public Employee consultarPerfilUsuario(User user) {
        return this.userDAO.consultarPerfilUsuario(user);
    }

    /**
     * Actualiza la password acorde al id, nombre y password dados por el
     * usuario
     *
     * @param user
     * @return
     */
    public int actualizarPasswordNula(User user) {
        int id = this.consultarIdUsuario(user.getId());
        if (id > 0 && this.esPasswordNula(id)) {
            this.userDAO.actualizarPasswordNula(user);
        }
        return id;
    }

    public int actualizarPassword(User user) {
        return this.userDAO.actualizarPassword(user);
    }

    /**
     * Obtiene el identificador de usuario como referencia de la tablaUsuario.
     *
     * @param id
     * @return
     */
    public int eliminar(Integer id) {
        return this.userDAO.eliminar(id);
    }
}
