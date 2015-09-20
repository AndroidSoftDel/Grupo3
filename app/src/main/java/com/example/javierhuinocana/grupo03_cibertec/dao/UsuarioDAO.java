package com.example.javierhuinocana.grupo03_cibertec.dao;

import android.database.Cursor;

import com.example.javierhuinocana.grupo03_cibertec.entities.Usuario;


/**
 * Created by Administrator on 19/09/2015.
 */
public class UsuarioDAO {

    public Usuario obtenerUsuario(String user, String pass) {
        Cursor cursor = null;
        Usuario usu = null;
        try {
            cursor = DataBaseHelper.myDataBase.query("Usuario", null, "Usuario=? and Password=?", new String[]{user, pass}, null, null, null);

            if (cursor.moveToFirst()) {
                usu = new Usuario();

                usu.setIdUsuario(cursor.isNull(cursor.getColumnIndex("IdUsuario")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdUsuario")));
                usu.setUsuario(cursor.isNull(cursor.getColumnIndex("Usuario")) ? "" : cursor.getString(cursor.getColumnIndex("Usuario")));
                usu.setPassword(cursor.isNull(cursor.getColumnIndex("Password")) ? "" : cursor.getString(cursor.getColumnIndex("Password")));
                usu.setNombres(cursor.isNull(cursor.getColumnIndex("Nombres")) ? "" : cursor.getString(cursor.getColumnIndex("Nombres")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return usu;
    }

}
