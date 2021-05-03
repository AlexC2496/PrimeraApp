package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class terminos extends AppCompatActivity {

    RadioButton rb1;
    CheckBox ck1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);
        rb1= (RadioButton) findViewById(R.id.rButton1);
        ck1=(CheckBox) findViewById(R.id.ckBox1);
    }


    public void onclick(View view){
        if(view.getId()==R.id.btn9){
            validar();
        }
    }

    private void validar() {
        if(rb1.isChecked() && ck1.isChecked()){
            Intent volver = new Intent(this, Activity3_Registrarse.class);
            startActivity(volver);
        }else
        {
            Toast.makeText(terminos.this, "Falta marcar la casillas", Toast.LENGTH_SHORT).show();
        }


    }


}