package com.example.primeraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2_RecuperarPassword extends AppCompatActivity {

    private Button contrasena2;
    private EditText email;
    private String emailRestablecer = "";

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__recuperar_password);

        email = (EditText) findViewById(R.id.email4);
        contrasena2 = (Button) findViewById(R.id.btnRestablecer);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        contrasena2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailRestablecer = email.getText().toString();
                if (emailRestablecer.isEmpty()) {
                    progressDialog.setMessage("Espere..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    resetPassword();
                }else {
                    Toast.makeText(MainActivity2_RecuperarPassword.this,"Debe ingresar el email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword() {
        firebaseAuth.sendPasswordResetEmail(emailRestablecer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Toast.makeText(MainActivity2_RecuperarPassword.this,"Se ha enviado el correo correctamente",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2_RecuperarPassword.this,"No se pudo enviar correctamente",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}