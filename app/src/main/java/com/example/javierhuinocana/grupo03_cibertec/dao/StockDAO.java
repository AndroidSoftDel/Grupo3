package com.example.javierhuinocana.grupo03_cibertec.dao;

import android.database.Cursor;

import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by JMartinez on 12/09/2015.
 */
public class StockDAO {

    public ArrayList<StockMaterial> lstStockMaterial() {
        ArrayList<StockMaterial> lstMaterial = new ArrayList<>();
        Cursor cursor = null;


        try {
            cursor = DataBaseHelper.myDataBase.query("StockMaterial", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    StockMaterial stockMaterial = new StockMaterial();
                    stockMaterial.setIdMaterial(cursor.isNull(cursor.getColumnIndex("IdMaterial")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdMaterial")));
                    stockMaterial.setDescripcion(cursor.isNull(cursor.getColumnIndex("Descripcion")) ? "" : cursor.getString(cursor.getColumnIndex("Descripcion")));
                    stockMaterial.setStock(cursor.isNull(cursor.getColumnIndex("Cantidad")) ? 0 : cursor.getInt(cursor.getColumnIndex("Cantidad")));
                    stockMaterial.setCantidad(0);
                    lstMaterial.add(stockMaterial);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstMaterial;
    }
}
