package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Gustavo on 20/09/2015.
 */
public class MainActivity extends AppCompatActivity {

    Button btnEspañol, btnIngles;
    private SharedPreferences preferencias;

    private Boolean CAMBIAR_IDIOMA = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEspañol = (Button) findViewById(R.id.btnEspañol);
        btnIngles = (Button) findViewById(R.id.btnIngles);

        btnEspañol.setOnClickListener(btnEspañolsetOnClickListener);
        btnIngles.setOnClickListener(btnInglessetOnClickListener);

        preferencias = getSharedPreferences("Usuario", MODE_PRIVATE);


        /*PREGUNTAMOS SI VIENE ALGO EN EL GETINTENT*/
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ListaOrdenesActivity.CAMBIAR_IDIOMA)) {
            CAMBIAR_IDIOMA = true;
        } else {
            /*PREGUNTAMOS SI YA SE ESCOGIÓ IDIOMA*/
            if (preferencias.contains("IDIOMA")) {
                /*CAMBIAMOS A LA CONFIGURACION QUE HAYA ESCOGIDO EL USUARIO*/
                ConfigurarIdiomaAplicacion(new Locale(preferencias.getString("IDIOMA", "").toString()));

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }
    }

    View.OnClickListener btnEspañolsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ConfigurarIdiomaAplicacion(new Locale("es"));
            GuardarIdiomaSeleccionada("es");

            if (CAMBIAR_IDIOMA == false) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };


    View.OnClickListener btnInglessetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ConfigurarIdiomaAplicacion(new Locale("en"));
            GuardarIdiomaSeleccionada("en");

            if (CAMBIAR_IDIOMA == false) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };

    public void ConfigurarIdiomaAplicacion(Locale locale) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void GuardarIdiomaSeleccionada(String Dato) {
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("IDIOMA", Dato);
        editor.commit();


    }
}
