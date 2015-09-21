package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

/**
 * Created by Gustavo on 20/09/2015.
 */
public class MainActivity extends AppCompatActivity {

    Button btnEspañol, btnIngles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selccionar_idioma);

        btnEspañol = (Button) findViewById(R.id.btnEspañol);
        btnIngles = (Button) findViewById(R.id.btnIngles);

        btnEspañol.setOnClickListener(btnEspañolsetOnClickListener);
        btnIngles.setOnClickListener(btnInglessetOnClickListener);

    }

    View.OnClickListener btnEspañolsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Locale locale = new Locale("es");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };


    View.OnClickListener btnInglessetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };


}
