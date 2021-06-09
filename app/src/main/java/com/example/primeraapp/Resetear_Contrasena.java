package com.example.primeraapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import actPrincipales.Activity2_Login;
import actPrincipales.Activity_Principal;

public class Resetear_Contrasena extends AppCompatActivity {
    private EditText email;
    private Button recuperar;
    private ProgressDialog mDialog;
    FirebaseAuth auth;
    private String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetear__contrasena);

        auth= FirebaseAuth.getInstance();
        email = findViewById(R.id.gmail);
        recuperar = findViewById(R.id.btnRecuperar);
        mDialog = new ProgressDialog(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getRecuperar();
    }

    private void getRecuperar() {
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 correo = email.getText().toString().trim();
                if(!correo.isEmpty()){
                    mDialog.setMessage("Espere un momento..");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    getEnviarCorreo();
                }else{
                    Toast.makeText(Resetear_Contrasena.this, "El correo no se pudo enviar correctamente", Toast.LENGTH_SHORT).show();
                    Intent inicio = new Intent(getApplication(), Activity_Principal.class);
                }
            }
        });
    }
    private void getEnviarCorreo(){
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Resetear_Contrasena.this, "Por favor revise su correo para restarurar contraseña. ", Toast.LENGTH_SHORT).show();
                    Intent inicio = new Intent(Resetear_Contrasena.this, Activity2_Login.class);
                    startActivity(inicio);
                    finish();
                }else {
                    Toast.makeText(Resetear_Contrasena.this, "El correo no se ha podido enviar correctamente ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}