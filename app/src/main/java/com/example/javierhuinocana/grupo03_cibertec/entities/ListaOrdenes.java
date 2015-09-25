package com.example.javierhuinocana.grupo03_cibertec.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JMartinez on 07/09/2015.
 */
public class ListaOrdenes implements Parcelable {
    private int IdOrden;
    private String Zonal;
    private String Orden;
    private String Telefono;
    private String Cliente;
    private String Direccion;
    private String Negocio;
    private String Actividad;
    private String ClienteAtendio;
    private String DniCliente;
    private String Coordenada;
    private String Fecha_Registro;
    private String Fecha_Liquidacion;
    private String Observaciones;
    private int Estado;
    private int IdUsuario;
    private Boolean Chequeado;

    public ListaOrdenes() {
    }

    public int getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(int idOrden) {
        IdOrden = idOrden;
    }

    public String getZonal() {
        return Zonal;
    }

    public void setZonal(String zonal) {
        Zonal = zonal;
    }

    public String getOrden() {
        return Orden;
    }

    public void setOrden(String orden) {
        Orden = orden;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getNegocio() {
        return Negocio;
    }

    public void setNegocio(String negocio) {
        Negocio = negocio;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    public String getClienteAtendio() {
        return ClienteAtendio;
    }

    public void setClienteAtendio(String clienteAtendio) {
        ClienteAtendio = clienteAtendio;
    }

    public String getDniCliente() {
        return DniCliente;
    }

    public void setDniCliente(String dniCliente) {
        DniCliente = dniCliente;
    }

    public String getCoordenada() {
        return Coordenada;
    }

    public void setCoordenada(String coordenada) {
        Coordenada = coordenada;
    }

    public String getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(String fecha_Registro) {
        Fecha_Registro = fecha_Registro;
    }

    public String getFecha_Liquidacion() {
        return Fecha_Liquidacion;
    }

    public void setFecha_Liquidacion(String fecha_Liquidacion) {
        Fecha_Liquidacion = fecha_Liquidacion;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public Boolean getChequeado() {
        return Chequeado;
    }

    public void setChequeado(Boolean chequeado) {
        Chequeado = chequeado;
    }

    protected ListaOrdenes(Parcel in) {
        IdOrden = in.readInt();
        Zonal = in.readString();
        Orden = in.readString();
        Telefono = in.readString();
        Cliente = in.readString();
        Direccion = in.readString();
        Negocio = in.readString();
        Actividad = in.readString();
        ClienteAtendio = in.readString();
        DniCliente = in.readString();
        Coordenada = in.readString();
        Fecha_Registro = in.readString();
        Fecha_Liquidacion = in.readString();
        Observaciones = in.readString();
        Estado = in.readInt();
        IdUsuario = in.readInt();
        byte ChequeadoVal = in.readByte();
        Chequeado = ChequeadoVal == 0x02 ? null : ChequeadoVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdOrden);
        dest.writeString(Zonal);
        dest.writeString(Orden);
        dest.writeString(Telefono);
        dest.writeString(Cliente);
        dest.writeString(Direccion);
        dest.writeString(Negocio);
        dest.writeString(Actividad);
        dest.writeString(ClienteAtendio);
        dest.writeString(DniCliente);
        dest.writeString(Coordenada);
        dest.writeString(Fecha_Registro);
        dest.writeString(Fecha_Liquidacion);
        dest.writeString(Observaciones);
        dest.writeInt(Estado);
        dest.writeInt(IdUsuario);
        if (Chequeado == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (Chequeado ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ListaOrdenes> CREATOR = new Parcelable.Creator<ListaOrdenes>() {
        @Override
        public ListaOrdenes createFromParcel(Parcel in) {
            return new ListaOrdenes(in);
        }

        @Override
        public ListaOrdenes[] newArray(int size) {
            return new ListaOrdenes[size];
        }
    };
}