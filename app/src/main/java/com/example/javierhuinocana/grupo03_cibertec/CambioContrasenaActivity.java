package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.dao.UsuarioDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.Usuario;

/**
 * Created by Gustavo on 20/09/2015.
 */
public class CambioContrasenaActivity extends AppCompatActivity {

    TextInputLayout tilUsuario_cambioClave, tilAntiguaClave_cambioClave, tilNuevaClave_cambioClave, tilNuevaClave2_cambioClave;
    Button btnCambiarClave, btnCancelar_cambioclave;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_clave);
        preferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);

        tilUsuario_cambioClave = (TextInputLayout) findViewById(R.id.tilUsuario_cambioClave);
        tilAntiguaClave_cambioClave = (TextInputLayout) findViewById(R.id.tilAntiguaClave_cambioClave);
        tilNuevaClave_cambioClave = (TextInputLayout) findViewById(R.id.tilNuevaClave_cambioClave);
        tilNuevaClave2_cambioClave = (TextInputLayout) findViewById(R.id.tilNuevaClave2_cambioClave);

        tilUsuario_cambioClave.getEditText().setText(preferences.getString("nickUsuario", ""));
        tilUsuario_cambioClave.getEditText().setKeyListener(null);

        btnCambiarClave = (Button) findViewById(R.id.btnCambiarClave);
        btnCancelar_cambioclave = (Button) findViewById(R.id.btnCancelar_cambioclave);
        tilAntiguaClave_cambioClave.getEditText().requestFocus();
        btnCambiarClave.setOnClickListener(btnCambiarClavesetOnClickListener);
        btnCancelar_cambioclave.setOnClickListener(btnCancelar_cambioclaveOnClickListener);
    }

    View.OnClickListener btnCambiarClavesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Usuario user = new Usuario();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            long rpta;

            if (tilAntiguaClave_cambioClave.getEditText().getText().toString().trim().length() <= 0) {
                tilAntiguaClave_cambioClave.setErrorEnabled(true);
                tilAntiguaClave_cambioClave.setError("Ingrese clave actual");
                return;
            }

            if (tilNuevaClave_cambioClave.getEditText().getText().toString().trim().length() <= 0) {
                tilNuevaClave_cambioClave.setErrorEnabled(true);
                tilNuevaClave_cambioClave.setError("Ingrese clave nueva");
                return;
            }

            if (tilNuevaClave2_cambioClave.getEditText().getText().toString().trim().length() <= 0) {
                tilNuevaClave2_cambioClave.setErrorEnabled(true);
                tilNuevaClave2_cambioClave.setError("Ingrese clave nueva");
                return;
            }

            user = usuarioDAO.obtenerUsuario(preferences.getString("nickUsuario", "").toString().trim().toLowerCase(),
                    tilAntiguaClave_cambioClave.getEditText().getText().toString().trim().toLowerCase());

            //validar usuario y contraseña
            if (user != null) {
                //validar que las nuevas claves coincidan
                if (tilNuevaClave_cambioClave.getEditText().getText().toString().trim().toLowerCase().
                        equals(tilNuevaClave2_cambioClave.getEditText().getText().toString().trim().toLowerCase())) {
                    //datos correctos, cambiar clave
                    rpta = usuarioDAO.actualizarContraseña(user.getIdUsuario(),
                            tilNuevaClave_cambioClave.getEditText().getText().toString().trim().toLowerCase());
                    if (rpta != 0) {
                        new AlertDialog.Builder(CambioContrasenaActivity.this).setTitle("Cambio de Contraseña").setMessage("Se ha cambiado la clave satisfactoriamente").
                                setNeutralButton("Aceptar", CambioClaveOnClickListener).setCancelable(false).show();

                        //se eliminan los datos de shared preferences
                    }
                } else {
                    new AlertDialog.Builder(CambioContrasenaActivity.this).setTitle("Cambio de Contraseña").setMessage("Las contraseñas no coinciden").
                            setNeutralButton("Aceptar", alertSingleOnClickListener).setCancelable(false).show();
                    tilNuevaClave_cambioClave.getEditText().setText("");
                    tilNuevaClave2_cambioClave.getEditText().setText("");
                    tilNuevaClave_cambioClave.getEditText().setFocusable(true);
                }

            }
            //usuario y clave incorrectas
            else {

                new AlertDialog.Builder(CambioContrasenaActivity.this).setTitle("Cambio de Contraseña").setMessage("Usuario o contraseña incorrecta"
                        + " user: " + preferences.getString("nickUsuario", "").toString().trim().toLowerCase() + " pass: " +
                        tilAntiguaClave_cambioClave.getEditText().getText().toString().trim().toLowerCase()).
                        setNeutralButton("Aceptar", alertSingleOnClickListener).setCancelable(false).show();

                tilAntiguaClave_cambioClave.getEditText().setText("");
                tilNuevaClave_cambioClave.getEditText().setText("");
                tilNuevaClave2_cambioClave.getEditText().setText("");
                tilUsuario_cambioClave.getEditText().requestFocus();
            }

        }
    };

    View.OnClickListener btnCancelar_cambioclaveOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            finish();
        }
    };


    DialogInterface.OnClickListener alertSingleOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };

    DialogInterface.OnClickListener CambioClaveOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            finish();
        }
    };

}
