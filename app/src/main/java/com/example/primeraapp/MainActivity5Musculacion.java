package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity5Musculacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5_musculacion);
    }

    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity4Principal.class);
        startActivity(atras);
    }

    public void SiguienteCardio(View view) {
        Intent siguienteCardio = new Intent(this, MainActivity_Ejercicios.class);
        startActivity(siguienteCardio);
    }
}