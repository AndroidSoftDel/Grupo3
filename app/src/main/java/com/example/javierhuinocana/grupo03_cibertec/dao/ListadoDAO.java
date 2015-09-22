package com.example.javierhuinocana.grupo03_cibertec.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.entities.OrdenMaterial;

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
                    listaOrdenes.setIdUsuario(cursor.isNull(cursor.getColumnIndex("IdUsuario")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdUsuario")));
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

    public ArrayList<OrdenMaterial> listOrdenMaterial(String IdOrden) {
        ArrayList<OrdenMaterial> lstOrdenMaterial = null;
        Cursor cursor = null;

        try {
            cursor = DataBaseHelper.myDataBase.query("OrdenMaterial", null, "IdOrden=? ", new String[]{IdOrden}, null, null, null);
            if (cursor.moveToFirst()) {
                lstOrdenMaterial = new ArrayList<OrdenMaterial>();
                do {
                    OrdenMaterial ordenMaterial = new OrdenMaterial();
                    ordenMaterial.setIdOrden(cursor.isNull(cursor.getColumnIndex("IdOrden")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdOrden")));
                    ordenMaterial.setIdMaterial(cursor.isNull(cursor.getColumnIndex("IdMaterial")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdMaterial")));
                    ordenMaterial.setDescripcion(cursor.isNull(cursor.getColumnIndex("Descripcion")) ? "" : cursor.getString(cursor.getColumnIndex("Descripcion")));
                    ordenMaterial.setCantidad(cursor.isNull(cursor.getColumnIndex("Cantidad")) ? 0 : cursor.getInt(cursor.getColumnIndex("Cantidad")));
                    ordenMaterial.setStock(0);
                    lstOrdenMaterial.add(ordenMaterial);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstOrdenMaterial;
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
            cv.put("IdUsuario", listaOrdenes.getIdUsuario());

            DataBaseHelper.myDataBase.beginTransaction();
            udp = DataBaseHelper.myDataBase.update("ListaOrdenes", cv, "Orden = ?", new String[]{String.valueOf(listaOrdenes.getOrden())});
            DataBaseHelper.myDataBase.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DataBaseHelper.myDataBase.endTransaction();
        }
        return udp;
    }

    public long LiquidarOrden(ListaOrdenes listaOrdenes, ArrayList<OrdenMaterial> ordenMaterial) {
        long udp = 0;
        try {
            ContentValues cv = new ContentValues();
            cv.put("ClienteAtendio", listaOrdenes.getClienteAtendio());
            cv.put("DniCliente", listaOrdenes.getDniCliente());
            cv.put("Fecha_Liquidacion", listaOrdenes.getFecha_Liquidacion());
            cv.put("Estado", listaOrdenes.getEstado());
            cv.put("Observaciones", listaOrdenes.getObservaciones());
            cv.put("IdUsuario", listaOrdenes.getIdUsuario());

            DataBaseHelper.myDataBase.beginTransaction();
            udp = DataBaseHelper.myDataBase.update("ListaOrdenes", cv, "Orden = ?", new String[]{String.valueOf(listaOrdenes.getOrden())});

            cv = new ContentValues();
            for (int i = 0; i < ordenMaterial.size(); i++) {
                OrdenMaterial OrdMat = ordenMaterial.get(i);
                cv.put("Cantidad", OrdMat.getStock() - OrdMat.getCantidad());
                udp = DataBaseHelper.myDataBase.update("StockMaterial", cv, "IdMaterial = ?", new String[]{String.valueOf(OrdMat.getIdMaterial())});

                ContentValues cm = new ContentValues();
                cm.put("IdOrden", OrdMat.getIdOrden());
                cm.put("IdMaterial", OrdMat.getIdMaterial());
                cm.put("Descripcion", OrdMat.getDescripcion());
                cm.put("Cantidad", OrdMat.getCantidad());
                udp = DataBaseHelper.myDataBase.insert("OrdenMaterial", null, cm);

                if (udp == 0) {
                    throw new Exception();
                }
            }

            DataBaseHelper.myDataBase.setTransactionSuccessful();
            udp = 1;
        } catch (Exception ex) {
            udp = 0;
            ex.printStackTrace();
        } finally {
            DataBaseHelper.myDataBase.endTransaction();

        }
        return udp;
    }

}