package com.example.javierhuinocana.grupo03_cibertec.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JMartinez on 11/09/2015.
 */
public class OrdenMaterial implements Parcelable {
    private int IdRegistro;
    private int IdOrden;
    private int IdMaterial;
    private String Descripcion;
    private int Cantidad;

    public OrdenMaterial() {
    }

    public int getIdRegistro() {
        return IdRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        IdRegistro = idRegistro;
    }

    public int getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(int idOrden) {
        IdOrden = idOrden;
    }

    public int getIdMaterial() {
        return IdMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        IdMaterial = idMaterial;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    protected OrdenMaterial(Parcel in) {
        IdRegistro = in.readInt();
        IdOrden = in.readInt();
        IdMaterial = in.readInt();
        Descripcion = in.readString();
        Cantidad = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdRegistro);
        dest.writeInt(IdOrden);
        dest.writeInt(IdMaterial);
        dest.writeString(Descripcion);
        dest.writeInt(Cantidad);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OrdenMaterial> CREATOR = new Parcelable.Creator<OrdenMaterial>() {
        @Override
        public OrdenMaterial createFromParcel(Parcel in) {
            return new OrdenMaterial(in);
        }

        @Override
        public OrdenMaterial[] newArray(int size) {
            return new OrdenMaterial[size];
        }
    };
}