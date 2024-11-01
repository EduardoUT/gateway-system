/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.factory;

import com.mx.grupogateway.system.modelo.Usuario;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class TestKeyGenerator {

    public static void main(String[] args) {
        //Usuario usuario = new Usuario("Lalo", "1234");
        //System.out.println(usuario.getPassword());
        //System.out.println(usuario.checkUser("Lalo", "1234"));
        //System.out.println(ProtectorData.encriptar("rtp333"));
        
        // Obtén la fecha y hora actual en milisegundos desde la Época Unix (1 de enero de 1970)
        long tiempoActualEnMilisegundos = new Date().getTime();

        // Crea un objeto Timestamp a partir del tiempo actual en milisegundos
        Timestamp timestamp = new Timestamp(tiempoActualEnMilisegundos);

        // Imprime el Timestamp
        System.out.println("Timestamp actual: " + timestamp);
    }
}
