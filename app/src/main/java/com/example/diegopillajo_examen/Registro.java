package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro extends AppCompatActivity {
    TextView cajaRecibir;
    Bundle loginusuario;
    TextView nombre;
    TextView apellido;
    TextView direccion;
    TextView telefono;
    TextView pago;
    ImageView img;

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
        img = findViewById(R.id.iWFoto);
        if (ContextCompat.checkSelfPermission(Registro.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Registro.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Registro.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }
    }

    String currentPhotoPath;
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg",storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;
    public void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
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
