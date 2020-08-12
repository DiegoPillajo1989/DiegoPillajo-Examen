package com.example.diegopillajo_examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Registro extends AppCompatActivity {
    TextView nombre;
    TextView apellido;
    TextView direccion;
    TextView telefono;
    private ImageView img;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.eTNombre);
        apellido = findViewById(R.id.eTApellido);
        direccion = findViewById(R.id.eTDireccion);
        telefono = findViewById(R.id.eTTelefono);
        iniciarfireBase();
        img =findViewById(R.id.iWFoto);
        if (ContextCompat.checkSelfPermission(Registro.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Registro.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Registro.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }
    }
    private void iniciarfireBase()
    {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }
  /*  private void registrar (){
        android.app.AlertDialog.Builder builder= new android.app.AlertDialog.Builder(Registro.this);
        View view=getLayoutInflater().inflate(R.layout.activity_registro, null);
        Button guardar = (Button)view.findViewById(R.id.button3);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String nombr = nombre.getText().toString();
                String apellid =apellido.getText().toString();
                String direccio = direccion.getText().toString();
                String telefon = telefono.getText().toString();
                String pag = pago.getText().toString();
                usuario p =  new usuario();
                p.setCod(UUID.randomUUID().toString());
                p.setNombre(nombr);
                p.setApellido(apellid);
                p.setDireccion(direccio);
                p.setTelefono(telefon);
                p.setTotal(pag);
                databaseReference.child("Usuarios").child(p.getCod()).setValue(p);
                dialog.dismiss();
            }
        });
    }*/



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
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex)
            {
                Toast.makeText(this,"Imagen no Capturada", Toast.LENGTH_LONG).show();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
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
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
    }

    public void Guardar(View view) {
        String nombr = nombre.getText().toString();
        String apellid =apellido.getText().toString();
        String direccio = direccion.getText().toString();
        String telefon = telefono.getText().toString();
        usuario p =  new usuario();
        p.setCod(UUID.randomUUID().toString());
        p.setNombre(nombr);
        p.setApellido(apellid);
        p.setDireccion(direccio);
        p.setTelefono(telefon);
        databaseReference.child("Usuarios").child(p.getCod()).setValue(p);
        Toast.makeText(this,"Elemento Guardado con Éxito", Toast.LENGTH_LONG).show();
        //Paso de Intent
        Intent intentinicio = new Intent (Registro.this,inicio.class);
        startActivity(intentinicio);

    }
    public void Limpiar(View view) {
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
    }

}
