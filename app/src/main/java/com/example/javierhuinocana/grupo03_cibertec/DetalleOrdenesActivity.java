package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;

import java.util.ArrayList;

/**
 * Created by Javier Huiñocana on 08/09/2015.
 */
public class DetalleOrdenesActivity extends AppCompatActivity {

    EditText txtZonal, txtFecha_Registro, txtFecha_Modificacion_Detalle, txtNegocio, txtActividad, txtOrden, txtTelefono, txtCliente, txtDireccion;
    Button btnVerMapa, btnLiquidar, btnRechazar;
    ListaOrdenes listaOrdenes;
    TextView tvFechaModicicacion_Detalle;
    LinearLayout lyFechaModificacion_Detalle;

    public final int Code_LIQ = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_orden);

        txtZonal = (EditText) findViewById(R.id.txtZonal_Detalle);
        txtFecha_Registro = (EditText) findViewById(R.id.txtFecha_Registro_Detalle);
        txtFecha_Modificacion_Detalle = (EditText) findViewById(R.id.txtFecha_Modificacion_Detalle);
        txtNegocio = (EditText) findViewById(R.id.txtNegocio_Detalle);
        txtActividad = (EditText) findViewById(R.id.txtActividad_Detalle);
        txtOrden = (EditText) findViewById(R.id.txtOrden_Detalle);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono_Detalle);
        txtCliente = (EditText) findViewById(R.id.txtCliente_Detalle);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion_Detalle);
        btnVerMapa = (Button) findViewById(R.id.btnVerMapa_Detalle);
        btnLiquidar = (Button) findViewById(R.id.btnLiquidar_Detalle);
        btnRechazar = (Button) findViewById(R.id.btnRechazar_Detalle);
        tvFechaModicicacion_Detalle = (TextView) findViewById(R.id.tvFechaModicicacion_Detalle);
        lyFechaModificacion_Detalle = (LinearLayout) findViewById(R.id.lyFechaModificacion_Detalle);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ListaOrdenesActivity.ARG_ORDEN)) {
            listaOrdenes = getIntent().getParcelableExtra(ListaOrdenesActivity.ARG_ORDEN);
            txtZonal.setText(listaOrdenes.getZonal());
            txtNegocio.setText(listaOrdenes.getNegocio());
            txtActividad.setText(listaOrdenes.getActividad());
            txtOrden.setText(listaOrdenes.getOrden());
            txtTelefono.setText(listaOrdenes.getTelefono());
            txtCliente.setText(listaOrdenes.getCliente());
            txtDireccion.setText(listaOrdenes.getDireccion());
            txtFecha_Registro.setText(listaOrdenes.getFecha_Registro());
            txtFecha_Modificacion_Detalle.setText(listaOrdenes.getFecha_Liquidacion());
        }
        //diferente a estado pendiente
        if (listaOrdenes.getEstado() != 0) {
            btnLiquidar.setVisibility(View.INVISIBLE);
            btnVerMapa.setVisibility(View.INVISIBLE);
            btnRechazar.setVisibility(View.INVISIBLE);
            lyFechaModificacion_Detalle.setVisibility(View.VISIBLE);
            if (listaOrdenes.getEstado() == 1)
                tvFechaModicicacion_Detalle.setText("F.Liq");
            else
                tvFechaModicicacion_Detalle.setText("F.Rechazo");
        } else {
            btnVerMapa.setOnClickListener(btnVerMapaOnClickListener);
            btnLiquidar.setOnClickListener(btnLiquidarOnClickListener);
            btnRechazar.setOnClickListener(btnRechazarOnClickListener);
        }

        btnVerMapa.setOnClickListener(btnVerMapaOnClickListener);
        btnLiquidar.setOnClickListener(btnLiquidarOnClickListener);
        btnRechazar.setOnClickListener(btnRechazarOnClickListener);

        /*IMPEDIMOS QUE SE MODIFIQUEN LOS VALORES*/
        txtFecha_Registro.setKeyListener(null);
        txtFecha_Modificacion_Detalle.setKeyListener(null);
        txtNegocio.setKeyListener(null);
        txtActividad.setKeyListener(null);
        txtOrden.setKeyListener(null);
        txtTelefono.setKeyListener(null);
        txtCliente.setKeyListener(null);
        txtDireccion.setKeyListener(null);
    }

    View.OnClickListener btnVerMapaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DetalleOrdenesActivity.this, Mapa_Ordenes.class);
            ArrayList<ListaOrdenes> lista = new ArrayList<ListaOrdenes>();
            lista.add(listaOrdenes);
            intent.putExtra(ListaOrdenesActivity.ARG_ORDEN, lista);
            startActivity(intent);
        }
    };
    View.OnClickListener btnLiquidarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DetalleOrdenesActivity.this, LiquidarOrdenActivity.class);
            intent.putExtra(ListaOrdenesActivity.ARG_ORDEN, listaOrdenes);
            startActivityForResult(intent, Code_LIQ);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Code_LIQ && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    View.OnClickListener btnRechazarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DetalleOrdenesActivity.this, RechazarOrdenActivity.class);
            intent.putExtra(ListaOrdenesActivity.ARG_ORDEN, listaOrdenes);
            startActivity(intent);
        }
    };
}
