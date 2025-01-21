/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.user;

import com.mx.grupogateway.employee.Employee;
import com.mx.grupogateway.factory.ConnectionFactory;
import com.mx.grupogateway.util.SecurityPassword;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author eduar
 */
public class UserService {

    private final UserImpl userImpl;

    public UserService() {
        this.userImpl = new UserImpl(new ConnectionFactory().realizarConexion());
    }

    /**
     * Verifica la conexión con la BD.
     *
     * @return
     */
    public boolean isConnectionStatusNotActive() {
        return this.userImpl.isStatusConnectionNotActive();
    }

    /**
     * Crea un nuevo user.
     *
     * @param user User.
     * @return Status de la operación.
     */
    public int create(User user) {
        return this.userImpl.create(user);
    }

    /**
     *
     * @return Listado de users.
     */
    public List<User> getAll() {
        return this.userImpl.getAll();
    }

    /**
     *
     * @param id Identificador de user.
     * @return Optional de tipo User.
     */
    public Optional<User> getEntityById(Integer id) {
        return this.userImpl.getEntityById(id);
    }

    /**
     * Evalúa que la password se encuentre en la BD, comparando la ingresada por
     * el usuario con la encriptada.<br>
     * Si es NULL se considera una password como nueva.
     *
     * @param user User con id asignado.
     * @return Status de la validación.
     */
    public boolean isCurrentPasswordValid(User user) {
        Optional<User> userOptional = this.userImpl.getEntityById(user.getId());
        if (userOptional.isPresent()) {
            if (userOptional.get().getHash().equals("NULL")) {
                return false;
            } else {
                return SecurityPassword.assertData(
                        userOptional.get().getHash(),
                        user.getPassword()
                );
            }
        } else {
            return false;
        }
    }

    /**
     * Permite validar que una contraseña contenga el valor NULL en la BD, lo
     * cual índica que es un nuevo usuario que aún no ha registrado su
     * contraseña.
     *
     * @param id Identificador del usuario.
     * @return Status de la validación.
     */
    public boolean isPasswordNull(Integer id) {
        Optional<User> user = this.userImpl.getEntityById(id);
        if (user.isPresent()) {
            return user.get().getHash().equals("NULL");
        } else {
            return false;
        }
    }

    /**
     *
     * @param user User con userName asignado.
     * @return Status de la autenticación.
     */
    private boolean authenticate(User user) {
        HashMap<String, Integer> hashMap = this.userImpl.getEntityByUserName(user);
        if (hashMap.isEmpty()) {
            return false;
        } else {
            for (Map.Entry<String, Integer> registro : hashMap.entrySet()) {
                String clave = registro.getKey();
                if (SecurityPassword.assertData(clave, user.getPassword())) {
                    Integer valor = registro.getValue();
                    user.setId(valor);
                    return true;
                }
            }
            return false;
        }
    }

    //Rename to getEmployeeByUser and declare in EmployeeService
    public Optional<Employee> getUserProfile(User user) {
        if (authenticate(user)) {
            return this.userImpl.getEmployeeByUser(user);
        }
        return Optional.ofNullable(null);
    }

    /**
     * Valida si una password es NULL, y la actualiza a la proporcionada por
     * User.
     *
     * @param user User con identificador asignado.
     * @return Status de la operación.
     */
    public int updateNullPassword(User user) {
        if (isPasswordNull(user.getId())) {
            return this.userImpl.updatePassword(user);
        } else {
            return -1;
        }
    }

    /**
     *
     * @param user User a actualizar.
     * @return Status de la operación.
     */
    public int updatePassword(User user) {
        return this.userImpl.updatePassword(user);
    }

    /**
     *
     * @param user User a eliminar.
     * @return Status de la operación.
     */
    public int delete(User user) {
        return this.userImpl.delete(user);
    }
}
