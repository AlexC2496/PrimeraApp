package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__login);
    }

    public void Inicio(View view) {
        Intent inicio = new Intent(this, MainActivity4Principal.class);
        startActivity(inicio);
    }

    public void VolverAtras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}