package com.example.javierhuinocana.grupo03_cibertec;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.dao.DataBaseHelper;
import com.example.javierhuinocana.grupo03_cibertec.dao.UsuarioDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtPassword;
    Button btnIngresar;
    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        final SharedPreferences preferencias = getSharedPreferences("Usuario", MODE_PRIVATE);

         /*CREAMOS Y/O COPIAMOS BD AL CELULAR*/
        try {
            dataBaseHelper = new DataBaseHelper(LoginActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (preferencias.contains("nombreUsuario")) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.Bienvenido) + " " + preferencias.getString("nombreUsuario", "").toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ListaOrdenesActivity.class);
            startActivity(intent);
            finish();
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar usuario y contraseña
                Usuario user;
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                if (txtUsuario.getText().toString().trim().length() <= 0 || txtPassword.getText().toString().trim().length() <= 0) {
                    new AlertDialog.Builder(LoginActivity.this).setTitle(getResources().getString(R.string.LoginTitulo_login)).setMessage(getResources().getString(R.string.Ingrese_Usuario_Contrasenia)).
                            setNeutralButton(getResources().getString(R.string.Aceptar_login), alertSingleOnClickListener).setCancelable(false).show();
                    return;
                }
                //consulta a base de datos
                user = usuarioDAO.obtenerUsuario(txtUsuario.getText().toString().trim().toLowerCase(), txtPassword.getText().toString().trim().toLowerCase());

                if (user != null) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.Bienvenido) + " " + user.getNombres(), Toast.LENGTH_SHORT).show();
                    //guardando nombre de usuario en shared preferences
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("IdUsuario", String.valueOf(user.getIdUsuario()));
                    editor.putString("nombreUsuario", user.getNombres().toString().toLowerCase());
                    editor.putString("nickUsuario", user.getUsuario().toString().toLowerCase());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, ListaOrdenesActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    new AlertDialog.Builder(LoginActivity.this).setTitle(getResources().getString(R.string.LoginTitulo_login)).setMessage(getResources().getString(R.string.Usuario_Contrasenia_Icorrecta_login)).
                            setNeutralButton(getResources().getString(R.string.Aceptar_login), alertSingleOnClickListener).setCancelable(false).show();
                    txtUsuario.setText("");
                    txtPassword.setText("");
                    txtUsuario.setFocusable(true);
                }
            }
        });
    }


    DialogInterface.OnClickListener alertSingleOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };

}
