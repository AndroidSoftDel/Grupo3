package com.example.javierhuinocana.grupo03_cibertec.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JMartinez on 11/09/2015.
 */
public class StockMaterial implements Parcelable {
    private int IdMaterial;
    private String Descripcion;
    private int Stock;
    private int Cantidad;

    public StockMaterial() {
    }

    public StockMaterial(int idMaterial, String descripcion, int stock, int cantidad) {
        IdMaterial = idMaterial;
        Descripcion = descripcion;
        Stock = stock;
        Cantidad = cantidad;
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

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    protected StockMaterial(Parcel in) {
        IdMaterial = in.readInt();
        Descripcion = in.readString();
        Stock = in.readInt();
        Cantidad = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdMaterial);
        dest.writeString(Descripcion);
        dest.writeInt(Stock);
        dest.writeInt(Cantidad);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StockMaterial> CREATOR = new Parcelable.Creator<StockMaterial>() {
        @Override
        public StockMaterial createFromParcel(Parcel in) {
            return new StockMaterial(in);
        }

        @Override
        public StockMaterial[] newArray(int size) {
            return new StockMaterial[size];
        }
    };
}