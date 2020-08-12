package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {
TextView tvBienvenido;
TextView tvnombre;
TextView tvmonto;
TextView tvInfo;
TextView tVidioma;
TextView tVdeportes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        recibirDatos();
    }
    private void recibirDatos ()
    {
        Bundle extras = getIntent().getExtras();
        String Nombre = extras.getString("nombre");
        String total = extras.getString("total");
        String info = extras.getString("info");
        String deporte = extras.getString("deportes");
        String idioma = extras.getString("idioma");
        tvnombre=(TextView) findViewById(R.id.tVnombre);
        tvnombre.setText(Nombre);
        tvmonto=(TextView) findViewById(R.id.tVmonto);
        tvmonto.setText(total);
        tvInfo=(TextView) findViewById(R.id.tvInfo);
        tvInfo.setText(info);
        tVdeportes=(TextView) findViewById(R.id.tVdeportes);
        tVdeportes.setText(deporte);
        tVidioma=(TextView) findViewById(R.id.tVidioma);
        tVidioma.setText(idioma);



    }

}
