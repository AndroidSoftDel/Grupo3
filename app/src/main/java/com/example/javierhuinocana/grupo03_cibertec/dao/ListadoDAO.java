package com.example.javierhuinocana.grupo03_cibertec.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
/**
 * Created by luisrios on 9/5/15.
 */
public class ListadoDAO {

    public ArrayList<ListaOrdenes> listOrdenes() {
        ArrayList<ListaOrdenes> lstOrdenes = new ArrayList<>();
        Cursor cursor = null;


        try {
            cursor = DataBaseHelper.myDataBase.query("ListaOrdenes", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    ListaOrdenes listaOrdenes = new ListaOrdenes();
                    listaOrdenes.setIdOrden(cursor.isNull(cursor.getColumnIndex("IdOrden")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdOrden")));
                    listaOrdenes.setZonal(cursor.isNull(cursor.getColumnIndex("Zonal")) ? "" : cursor.getString(cursor.getColumnIndex("Zonal")));
                    listaOrdenes.setOrden(cursor.isNull(cursor.getColumnIndex("Orden")) ? "" : cursor.getString(cursor.getColumnIndex("Orden")));
                    listaOrdenes.setTelefono(cursor.isNull(cursor.getColumnIndex("Telefono")) ? "" : cursor.getString(cursor.getColumnIndex("Telefono")));
                    listaOrdenes.setCliente(cursor.isNull(cursor.getColumnIndex("Cliente")) ? "" : cursor.getString(cursor.getColumnIndex("Cliente")));
                    listaOrdenes.setDireccion(cursor.isNull(cursor.getColumnIndex("Direccion")) ? "" : cursor.getString(cursor.getColumnIndex("Direccion")));
                    listaOrdenes.setNegocio(cursor.isNull(cursor.getColumnIndex("Negocio")) ? "" : cursor.getString(cursor.getColumnIndex("Negocio")));
                    listaOrdenes.setActividad(cursor.isNull(cursor.getColumnIndex("Actividad")) ? "" : cursor.getString(cursor.getColumnIndex("Actividad")));
                    listaOrdenes.setClienteAtendio(cursor.isNull(cursor.getColumnIndex("ClienteAtendio")) ? "" : cursor.getString(cursor.getColumnIndex("ClienteAtendio")));
                    listaOrdenes.setDniCliente(cursor.isNull(cursor.getColumnIndex("DniCliente")) ? "" : cursor.getString(cursor.getColumnIndex("DniCliente")));
                    listaOrdenes.setCoordenada(cursor.isNull(cursor.getColumnIndex("Coordenada")) ? "" : cursor.getString(cursor.getColumnIndex("Coordenada")));
                    listaOrdenes.setFecha_Registro(cursor.isNull(cursor.getColumnIndex("Fecha_Registro")) ? "" : cursor.getString(cursor.getColumnIndex("Fecha_Registro")));
                    listaOrdenes.setFecha_Liquidacion(cursor.isNull(cursor.getColumnIndex("Fecha_Liquidacion")) ? "" : cursor.getString(cursor.getColumnIndex("Fecha_Liquidacion")));
                    listaOrdenes.setObservaciones(cursor.isNull(cursor.getColumnIndex("Observaciones")) ? "" : cursor.getString(cursor.getColumnIndex("Observaciones")));
                    listaOrdenes.setEstado(cursor.isNull(cursor.getColumnIndex("Estado")) ? 0 : cursor.getInt(cursor.getColumnIndex("Estado")));
                    lstOrdenes.add(listaOrdenes);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstOrdenes;
    }


    public long updateListado(ListaOrdenes listaOrdenes) {
        long udp = 0;
        try {
            ContentValues cv = new ContentValues();
            cv.put("ClienteAtendio", listaOrdenes.getClienteAtendio());
            cv.put("DniCliente", listaOrdenes.getDniCliente());
            cv.put("Fecha_Liquidacion", listaOrdenes.getFecha_Liquidacion());
            cv.put("Estado", listaOrdenes.getEstado());
            cv.put("Observaciones", listaOrdenes.getObservaciones());

            DataBaseHelper.myDataBase.beginTransaction();
            udp = DataBaseHelper.myDataBase.update("ListaOrdenes", cv, "Orden = ?", new String[]{String.valueOf(listaOrdenes.getOrden())});
            DataBaseHelper.myDataBase.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DataBaseHelper.myDataBase.endTransaction();
        }
        return  udp;
    }

}