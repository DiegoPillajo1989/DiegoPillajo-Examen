package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText loginusuario;
    EditText clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginusuario = findViewById(R.id.etUs);
        clave = findViewById(R.id.etpass);
    }
    public void Enviar (View V)
    {
        if (loginusuario.getText().toString().equals("estudiante2020")  && clave.getText().toString().equals("uisrael2020" ))
        {
            //Enviar
            Intent intentEnviar = new Intent (login.this,Registro.class);
            intentEnviar.putExtra("datoEnviado",loginusuario.getText().toString());
            startActivity(intentEnviar);
            Toast.makeText(this,"Login Aceptado", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(this,"Usuario o Contraseña Incorrecto", Toast.LENGTH_LONG).show();
        }


    }

}
