package com.example.diegopillajo_examen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }


    public void encuesta (View V)
    {
            Intent intentEncuesta = new Intent (inicio.this,Encuesta.class);
            startActivity(intentEncuesta);
    }
    public void agendar (View V)
    {
            Intent intentAgenda = new Intent (inicio.this,agenda.class);
            startActivity(intentAgenda);
    }
    public void Salir (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo);
        builder.setMessage(R.string.Mensaje);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Paso de Intent
                Intent intentinicio = new Intent (inicio.this,login.class);
                startActivity(intentinicio);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        Dialog dialog = builder.create();
        dialog.show();

    }
}
