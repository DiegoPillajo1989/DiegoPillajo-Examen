package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Encuesta extends AppCompatActivity {
    TextView tvBienvenido;
    TextView tvnombre;
    TextView tvmonto;
    TextView tVSeleccionadoc;
    TextView tVSeleccionador;
    EditText eTInfo;
    RadioButton rBSi,rBNo;
    CheckBox cBFutbol, cBBasquet, cBArtes;
    Button btn_selecionar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        recibirDatos ();
        eTInfo = findViewById(R.id.eTInfo);
        btn_selecionar = (Button)findViewById(R.id.btnEnviar);
        rBSi = (RadioButton)findViewById(R.id.rbSi);
        rBNo = (RadioButton)findViewById(R.id.rbNo);
        cBFutbol = (CheckBox) findViewById(R.id.cbFutbol);
        cBBasquet  = (CheckBox) findViewById(R.id.cbBasquet);
        cBArtes  = (CheckBox) findViewById(R.id.cbArtes);
        tVSeleccionadoc = (TextView) findViewById(R.id.ResChek);
        tVSeleccionador = (TextView) findViewById(R.id.ResRadio);
    }
    private void recibirDatos ()
    {
        Bundle extras = getIntent().getExtras();
        String Usuario = extras.getString("usuario");
        String Nombre = extras.getString("nombre");
        String total = extras.getString("total");
        tvBienvenido = (TextView) findViewById(R.id.tvBienvenido);
        tvBienvenido.setText(Usuario);
        tvnombre=(TextView) findViewById(R.id.tVnombre);
        tvnombre.setText(Nombre);
        tvmonto=(TextView) findViewById(R.id.tVmonto);
        tvmonto.setText(total);
    }

    public void Enviar(View view) {
        String cad = " ";
            if (rBSi.isChecked())
            {
                tVSeleccionador.setText(rBSi.getText());
            }else if (rBNo.isChecked())
            {
                tVSeleccionador.setText(rBNo.getText());
            }

            if (cBFutbol.isChecked())
            {
               cad+="Futbol ";
            }
            if (cBBasquet.isChecked())
            {
                cad+="Básquet ";
            }
            if (cBArtes.isChecked())
            {
                cad+="Artes ";
            }
            tVSeleccionadoc.setText(cad);

            Intent intentDatos = new Intent (Encuesta.this,Resumen.class);
            intentDatos.putExtra("usuario",tvBienvenido.getText().toString());
            intentDatos.putExtra("nombre",tvnombre.getText().toString());
            intentDatos.putExtra("total",tvmonto.getText().toString());
            intentDatos.putExtra("info",eTInfo.getText().toString());
            intentDatos.putExtra("deportes",cad);
            intentDatos.putExtra("idioma",tVSeleccionador.getText().toString());
            Toast.makeText(getApplicationContext(),cad,Toast.LENGTH_SHORT).show();
            startActivity(intentDatos);
            //Toast.makeText(this,"Encuesta llenada con con Éxito", Toast.LENGTH_LONG).show();
    }
}
