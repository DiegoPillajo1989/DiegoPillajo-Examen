package com.example.diegopillajo_examen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class agenda extends AppCompatActivity {
    TextView Marca;
    TextView Modelo;
    TextView Tipo;
    TextView Descripcion;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Marca = findViewById(R.id.eTMarca);
        Modelo = findViewById(R.id.etModelo);
        Tipo = findViewById(R.id.eTTipo);
        Descripcion = findViewById(R.id.eTDescripcion);
        iniciarfireBase();
    }
    private void iniciarfireBase()
    {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }

    public void Guardar(View view) {
        String marca = Marca.getText().toString();
        String modelo =Modelo.getText().toString();
        String tipo = Tipo.getText().toString();
        String descripcion = Descripcion.getText().toString();
        agendaDat a =  new agendaDat();
        a.setCod(UUID.randomUUID().toString());
        a.setMarca(marca);
        a.setModelo(modelo);
        a.setTipo(tipo);
        a.setDescipcion(descripcion);
        databaseReference.child("Agendas").child(a.getCod()).setValue(a);
        Toast.makeText(this,"Asistencia Registrada", Toast.LENGTH_LONG).show();
        //Paso de Intent
        Intent intentinicio = new Intent (agenda.this,inicio.class);
        startActivity(intentinicio);

    }
    public void Limpiar(View view) {
        Marca.setText("");
        Modelo.setText("");
        Tipo.setText("");
        Descripcion.setText("");
    }



}