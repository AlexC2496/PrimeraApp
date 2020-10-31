package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity3_Registrarse extends AppCompatActivity {

    TextView Opciones;
    Spinner combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3__registrarse);
        Opciones= (TextView) findViewById(R.id.etiSeleccion);
        combo= (Spinner) findViewById(R.id.spinnerOpciones);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.combo, android.R.layout.simple_spinner_item);
        combo.setAdapter(adapter);
    }

    public void Siguiente(View view) {
        Intent siguiente = new Intent(this, MainActivity4Principal.class);
        startActivity(siguiente);
    }

    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}