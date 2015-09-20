package com.example.javierhuinocana.grupo03_cibertec;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.javierhuinocana.grupo03_cibertec.dao.UsuarioDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.Usuario;

/**
 * Created by Gustavo on 20/09/2015.
 */
public class CambioContrasenaActivity extends AppCompatActivity {

    TextInputLayout tilUsuario_cambioClave, tilAntiguaClave_cambioClave, tilNuevaClave_cambioClave, tilNuevaClave2_cambioClave;
    Button btnCambiarClave, btnCancelar_cambioclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_clave);

        tilUsuario_cambioClave = (TextInputLayout) findViewById(R.id.tilUsuario_cambioClave);
        tilAntiguaClave_cambioClave = (TextInputLayout) findViewById(R.id.tilAntiguaClave_cambioClave);
        tilNuevaClave_cambioClave = (TextInputLayout) findViewById(R.id.tilNuevaClave_cambioClave);
        tilNuevaClave2_cambioClave = (TextInputLayout) findViewById(R.id.tilNuevaClave2_cambioClave);

        btnCambiarClave = (Button) findViewById(R.id.btnCambiarClave);
        btnCancelar_cambioclave = (Button) findViewById(R.id.btnCancelar_cambioclave);

        btnCambiarClave.setOnClickListener(btnCambiarClavesetOnClickListener);


    }

    View.OnClickListener btnCambiarClavesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Usuario user;
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            long rpta;

            user = usuarioDAO.obtenerUsuario(tilUsuario_cambioClave.getEditText().getText().toString().trim().toLowerCase(),
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
                        //eliminar toda la pila de actividades y llamar a login
                        //SharedPreferences preferencias = getSharedPreferences("Usuario", MODE_PRIVATE);
                        //SharedPreferences.Editor editor=preferencias.edit();
                        //editor.remove(preferencias);
                        //se eliminan los datos de shared preferences
                    }
                } else {
                    new AlertDialog.Builder(CambioContrasenaActivity.this).setTitle("Cambio de Contraseña").setMessage("Las contraseñas no coinciden").
                            setNeutralButton("Aceptar", alertSingleOnClickListener).setCancelable(false).show();
                    tilNuevaClave_cambioClave.getEditText().setText("");
                    tilNuevaClave2_cambioClave.getEditText().setText("");
                    tilNuevaClave_cambioClave.getEditText().setFocusable(true);
                }

            } else {
                new AlertDialog.Builder(CambioContrasenaActivity.this).setTitle("Cambio de Contraseña").setMessage("Usuario o contraseña incorrecta").
                        setNeutralButton("Aceptar", alertSingleOnClickListener).setCancelable(false).show();
                tilUsuario_cambioClave.getEditText().setText("");
                tilAntiguaClave_cambioClave.getEditText().setText("");
                tilNuevaClave_cambioClave.getEditText().setText("");
                tilNuevaClave2_cambioClave.getEditText().setText("");
                tilUsuario_cambioClave.getEditText().setFocusable(true);
            }

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
            finish();
            dialogInterface.dismiss();
        }
    };

}
