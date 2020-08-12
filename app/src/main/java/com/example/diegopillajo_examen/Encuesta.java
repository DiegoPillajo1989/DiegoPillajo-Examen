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

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

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
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        eTInfo = findViewById(R.id.eTInfo);
        btn_selecionar = (Button)findViewById(R.id.btnEnviar);
        rBSi = (RadioButton)findViewById(R.id.rbSi);
        rBNo = (RadioButton)findViewById(R.id.rbNo);
        cBFutbol = (CheckBox) findViewById(R.id.cbFutbol);
        cBBasquet  = (CheckBox) findViewById(R.id.cbBasquet);
        cBArtes  = (CheckBox) findViewById(R.id.cbArtes);
        tVSeleccionadoc = (TextView) findViewById(R.id.ResChek);
        tVSeleccionador = (TextView) findViewById(R.id.ResRadio);
        iniciarfireBase();
    }
    private void iniciarfireBase()
    {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
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
               cad+="Un amigo ";
            }
            if (cBBasquet.isChecked())
            {
                cad+="Facebook ";
            }
            if (cBArtes.isChecked())
            {
                cad+="Otros ";
            }
            tVSeleccionadoc.setText(cad);

            String info = eTInfo.getText().toString();
            String medio =cad;
            String satisfaccion =tVSeleccionador.getText().toString();
            encuestaDat e =  new encuestaDat();
            e.setCod(UUID.randomUUID().toString());
            e.setInformacion(info);
            e.setMediop(cad);
            e.setSatisfaccionf(satisfaccion);
            databaseReference.child("Encuesta").child(e.getCod()).setValue(e);
            Toast.makeText(this,"Gracias por Calificarnos", Toast.LENGTH_LONG).show();
            //Paso de Intent
            Intent intentinicio = new Intent (Encuesta.this,inicio.class);
            startActivity(intentinicio);







    }

}
