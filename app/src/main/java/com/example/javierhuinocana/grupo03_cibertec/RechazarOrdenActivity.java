package com.example.javierhuinocana.grupo03_cibertec;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.dao.ListadoDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Javier Huiñocana on 08/09/2015.
 */
public class RechazarOrdenActivity extends AppCompatActivity {

    private Button btnRechazarOrden_Rechazar, btnCancelar_Rechazar;
    private TextInputLayout tilNombre_Rechazar, tilDni_Rechazar, tilObservaciones_Rechazar;
    TextView tvOrden_Rechazar, tvTelefono_Rechazar;
    ListaOrdenes listaOrdenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechazar_orden);

        tilNombre_Rechazar = (TextInputLayout) findViewById(R.id.tilNombre_Rechazar);
        tilDni_Rechazar = (TextInputLayout) findViewById(R.id.tilDni_Rechazar);
        tilObservaciones_Rechazar = (TextInputLayout) findViewById(R.id.tilObservaciones_Rechazar);

        btnRechazarOrden_Rechazar = (Button) findViewById(R.id.btnRechazarOrden_Rechazar);
        btnCancelar_Rechazar = (Button) findViewById(R.id.btnCancelar_Rechazar);

        tvOrden_Rechazar = (TextView) findViewById(R.id.tvOrden_Rechazar);
        tvTelefono_Rechazar = (TextView) findViewById(R.id.tvTelefono_Rechazar);


        /*ASOCIAMOS EVENTO CLICK AL BOTON*/
        btnRechazarOrden_Rechazar.setOnClickListener(btnRechazarOrden_RechazarOnClickListener);
        btnCancelar_Rechazar.setOnClickListener(btnCancelar_RechazarOnClickListener);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ListaOrdenesActivity.ARG_ORDEN)) {
            listaOrdenes = getIntent().getParcelableExtra(ListaOrdenesActivity.ARG_ORDEN);
            tvOrden_Rechazar.setText(listaOrdenes.getOrden());
            tvTelefono_Rechazar.setText(listaOrdenes.getTelefono());
        }
    }

    View.OnClickListener btnRechazarOrden_RechazarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isCorrect = true;
            ListaOrdenes listaOrdenes = new ListaOrdenes();

            tilObservaciones_Rechazar.setErrorEnabled(false);
            tilDni_Rechazar.setErrorEnabled(false);
            //falta actualizar la BD
            //no es obligatorio que se ingrese el nombre del cliente si se rechaza la orden
            if (tilNombre_Rechazar.getEditText().getText().toString().trim().length() == 0) {
                listaOrdenes.setClienteAtendio("");
            } else
                listaOrdenes.setClienteAtendio(tilNombre_Rechazar.getEditText().getText().toString().trim());

            //No es necesario llenar DNI si se rechaza la orden, pero si se ingresa algo debe ser un DNI correcto
            if (tilDni_Rechazar.getEditText().getText().toString().trim().length() > 0) {
                if (tilDni_Rechazar.getEditText().getText().toString().trim().length() != 8) {
                    tilDni_Rechazar.setError("Ingrese un DNI válido");
                    tilDni_Rechazar.setErrorEnabled(true);
                    isCorrect = false;
                } else
                    listaOrdenes.setDniCliente(tilDni_Rechazar.getEditText().getText().toString().trim());
            } else
                listaOrdenes.setDniCliente("");
            //Si es obligatorio ingresar una observacion si se rechaza la orden
            if (tilObservaciones_Rechazar.getEditText().getText().toString().trim().length() <= 0) {
                tilObservaciones_Rechazar.setError("Ingrese las observaciones");
                tilObservaciones_Rechazar.setErrorEnabled(true);
                isCorrect = false;
            } else
                listaOrdenes.setObservaciones(tilObservaciones_Rechazar.getEditText().getText().toString().trim());

            if (isCorrect) {
                //estado para orden rechazada = 10
                listaOrdenes.setEstado(ListaOrdenesActivity.estadoOrdenRechazada);
                //se obtiene la fecha
                String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", java.util.Locale.getDefault()).format(Calendar.getInstance().getTime());
                listaOrdenes.setFecha_Liquidacion(fecha);

                listaOrdenes.setOrden(tvOrden_Rechazar.getText().toString().trim());

                ListadoDAO listadoDAO = new ListadoDAO();
                long rc = listadoDAO.updateListado(listaOrdenes);
                if(rc==1)
                    Toast.makeText(RechazarOrdenActivity.this, "Orden Rechazada satisfactoriamente", Toast.LENGTH_LONG).show();

                //Intent intent = new Intent();
                //intent.putExtra(MainActivity.ARG_PERSONA, persona);
                //intent.putExtra(MainActivity.ARG_POSITION, position);
                //setResult(RESULT_OK, intent);
                finish();
            }


        }
    };
    View.OnClickListener btnCancelar_RechazarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
