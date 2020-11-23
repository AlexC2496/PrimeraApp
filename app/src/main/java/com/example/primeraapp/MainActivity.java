package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void Registrar (View view)
        {
            Intent registrar = new Intent (this, MainActivity3_Registrarse.class);
            startActivity(registrar);
        }
        public void Inicio (View view)
        {
        Intent iniciar = new Intent (this, MainActivity2_Login.class);
        startActivity(iniciar);
        }

    public void Mapa (View view) {
        Intent mapa = new Intent(this, MapsActivity.class);
        startActivity(mapa);
    }

    }
