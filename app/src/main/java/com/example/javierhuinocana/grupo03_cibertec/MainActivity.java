package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.dao.DataBaseHelper;
import com.example.javierhuinocana.grupo03_cibertec.dao.UsuarioDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario,txtPassword;
    Button btnIngresar;
    private DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        btnIngresar=(Button)findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*CREAMOS Y/O COPIAMOS BD AL CELULAR*/
                try {
                    dataBaseHelper = new DataBaseHelper(MainActivity.this);
                    dataBaseHelper.createDataBase();
                    dataBaseHelper.openDataBase();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                //validar usuario y contraseña
                Usuario user = new Usuario();
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                if(usuarioDAO.obtenerUsuario(txtUsuario.getText().toString().trim(), txtPassword.getText().toString().trim()) != null)
                {
                    Toast.makeText(MainActivity.this,"Bienvenidortgyhy "+user.getNombres(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,ListaOrdenesActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(MainActivity.this,"incorrecto",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
