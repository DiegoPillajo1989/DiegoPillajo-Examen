package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;

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
}
