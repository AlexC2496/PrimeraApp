package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity4Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_principal);
    }
    public void Siguiente(View view) {
        Intent siguiente = new Intent(this, MainActivity5Musculacion.class);
        startActivity(siguiente);
    }
    public void SiguienteCardio(View view) {
        Intent siguienteCardio = new Intent(this, MainActivity6Cardio.class);
        startActivity(siguienteCardio);
    }
}