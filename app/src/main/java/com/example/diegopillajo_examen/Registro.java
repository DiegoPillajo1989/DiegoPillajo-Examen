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
    TextView pago;
    TextView cuota;
    EditText monto;
    TextView nombre;
    TextView apellido;
    TextView direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cajaRecibir=findViewById(R.id.tvBienvenido);
        pago=findViewById(R.id.tVPago);
        cuota=findViewById(R.id.tVCouta);
        monto = findViewById(R.id.eTMonto);
        nombre = findViewById(R.id.eTNombre);
        //Recibo el usuario
        loginusuario = getIntent().getExtras();
        String datoRecibido = loginusuario.getString("datoEnviado");
        cajaRecibir.setText(datoRecibido);
        //

    }
  /*  public void Calcular(View view) {
        if (monto.getText().toString().equals(""))
        {
            Toast.makeText(this,"Monto Incorrecto", Toast.LENGTH_LONG).show();
        }
        else {
            double montoI, resultado, impuesto, pagos, coutas;
            montoI = Double.parseDouble(monto.getText().toString());
            resultado = 1800 - montoI;
            impuesto = resultado * 0.05;
            pagos = resultado + impuesto;
            pago.setText(Double.toString(pagos));
            coutas = pagos / 3;
            cuota.setText(Double.toString(coutas));
        }
    }*/
    public void Guardar(View view) {
       /* if (monto.getText().toString().equals("") || pago.getText().toString().equals("") || cuota.getText().toString().equals(""))
        {
            Toast.makeText(this,"Monto Incorrecto", Toast.LENGTH_LONG).show();
        }
        else
        {*/
            Intent intentDatos = new Intent (Registro.this,Encuesta.class);
            intentDatos.putExtra("usuario",cajaRecibir.getText().toString());
            intentDatos.putExtra("nombre",nombre.getText().toString());
            intentDatos.putExtra("total",pago.getText().toString());
            startActivity(intentDatos);
            Toast.makeText(this,"Elemento Guardado con Éxito", Toast.LENGTH_LONG).show();
        /*}*/
    }
    public void Limpiar(View view) {
        nombre.setText("");
        apellido.setText("");

        pago.setText("");
        monto.setText("");
        cuota.setText("");
    }
}
