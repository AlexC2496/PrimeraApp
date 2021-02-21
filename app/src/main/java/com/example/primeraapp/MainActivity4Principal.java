package com.example.primeraapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4Principal extends AppCompatActivity {
    public static final String user="names";
    TextView txtUser;

    ImageView profile;
    Button btnGallery;

    private static final int REQUEST_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_principal);
        txtUser=(TextView)findViewById(R.id.principal);

        btnGallery = findViewById(R.id.btnGallery);
        profile = findViewById(R.id.perfil);


        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(MainActivity4Principal.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        openGallery();
                    }else{
                        ActivityCompat.requestPermissions(MainActivity4Principal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                }else{
                    openGallery();
                }
            }
        });
    }

    @Override
    public void onActivityResult (int requestcode, int resultCode, @Nullable Intent data){
        if(requestcode == REQUEST_IMAGE_GALLERY){
            if(resultCode == Activity.RESULT_OK && data != null){
                Uri photo = data.getData();
                profile.setImageURI(photo);
            }else{
                Toast.makeText(this, "No se recogió ninguna foto de la galeria",Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestcode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult (int requestcode, @NonNull String[] permissions, @NonNull int [] grantResults){
        if (requestcode == REQUEST_PERMISSION_CODE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else{
                Toast.makeText(this, "Necesitas habilitar los permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestcode, permissions, grantResults);
    }

    private void openGallery()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }



    public void Siguiente(View view) {
        Intent siguiente = new Intent(this, MainActivity5Musculacion.class);
        startActivity(siguiente);
    }

    public void SiguienteCardio(View view) {
        Intent siguientec = new Intent(this, ejercicios_cardio.class);
        startActivity(siguientec);
    }

    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

}