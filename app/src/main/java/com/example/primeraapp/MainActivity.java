package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void Registrar (View view)
        {
            Intent registrar = new Intent (this, Activity3_Registrarse.class);
            startActivity(registrar);
        }
        public void Inicio (View view)
        {
        Intent iniciar = new Intent (this, Activity2_Login.class);
        startActivity(iniciar);
        }

    public void Mapa (View view) {
        Intent mapa = new Intent(this, MapsActivity.class);
        startActivity(mapa);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return true;
    }
    //Metodo para asignar funciones
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.item1){
            Toast.makeText(this,"Opcion1",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.item2){
            Toast.makeText(this,"Opcion2",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    }
