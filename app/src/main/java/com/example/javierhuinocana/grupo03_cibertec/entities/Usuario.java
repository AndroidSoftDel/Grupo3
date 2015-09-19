package com.example.javierhuinocana.grupo03_cibertec.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 19/09/2015.
 */
public class Usuario implements Parcelable {
    private int idUsuario;
    private String usuario;
    private String password;
    private String nombres;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    protected Usuario(Parcel in) {
        idUsuario = in.readInt();
        usuario = in.readString();
        password = in.readString();
        nombres = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idUsuario);
        dest.writeString(usuario);
        dest.writeString(password);
        dest.writeString(nombres);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}