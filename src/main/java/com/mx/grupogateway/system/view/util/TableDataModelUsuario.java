/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.view.util;

import com.mx.grupogateway.system.modelo.Usuario;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TableDataModelUsuario {

    private DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final List<Usuario> usuarios;

    public TableDataModelUsuario(DefaultTableModel modeloTabla, JTable tabla,
            List<Usuario> usuarios) {
        this.modeloTabla = modeloTabla;
        this.tabla = tabla;
        this.usuarios = usuarios;
    }

    public void cargarModeloTablaUsuario() {
        TableMethods.limpiarTabla(modeloTabla, tabla);
        modeloTabla = (DefaultTableModel) tabla.getModel();
        tabla.getTableHeader().setFont(tabla.getFont());
        usuarios.forEach((usuario) -> {
            modeloTabla.addRow(
                    new Object[]{
                        usuario.getIdUsuario(),
                        usuario.getNombreUsuario(),
                        usuario.getClaveSeguridad()
                    }
            );
        });
    }
}
