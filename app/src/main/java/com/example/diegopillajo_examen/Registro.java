package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    TextView cajaRecibir;
    Bundle loginusuario;
    TextView nombre;
    TextView apellido;
    TextView direccion;
    TextView telefono;
    TextView pago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cajaRecibir=findViewById(R.id.tvBienvenido);
        nombre = findViewById(R.id.eTNombre);
        apellido = findViewById(R.id.eTApellido);
        direccion = findViewById(R.id.eTDireccion);
        telefono = findViewById(R.id.eTTelefono);
        pago = findViewById(R.id.eTPago);
        //Recibo el usuario
        loginusuario = getIntent().getExtras();
        String datoRecibido = loginusuario.getString("datoEnviado");
        cajaRecibir.setText(datoRecibido);
    }
    public void Guardar(View view) {
            Intent intentDatos = new Intent (Registro.this,Encuesta.class);
            intentDatos.putExtra("usuario",cajaRecibir.getText().toString());
            intentDatos.putExtra("nombre",nombre.getText().toString());
            intentDatos.putExtra("apellido",apellido.getText().toString());
            intentDatos.putExtra("direccion",direccion.getText().toString());
            intentDatos.putExtra("telefono",telefono.getText().toString());
            intentDatos.putExtra("total",pago.getText().toString());
            startActivity(intentDatos);
            Toast.makeText(this,"Elemento Guardado con Éxito", Toast.LENGTH_LONG).show();
    }
    public void Limpiar(View view) {
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
        pago.setText("");

    }
}
