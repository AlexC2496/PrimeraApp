package com.example.primeraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Activity2_Login extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail2;
    private EditText TextPassword2;
    private Button btnInicio;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private GoogleSignInApi mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__login);

        //Inicializamos objeto
        firebaseAuth = FirebaseAuth.getInstance();

        //Referecnias
        TextEmail2 = (EditText) findViewById(R.id.email2);
        TextPassword2 = (EditText) findViewById(R.id.password2);
        btnInicio = (Button) findViewById(R.id.btnInicio);
        progressDialog = new ProgressDialog(this);
        btnInicio.setOnClickListener(this);


    }


    private void iniciarrUsuario() {
        //Obtener el email y la contraseña
        final String email = TextEmail2.getText().toString().trim();
        String password = TextPassword2.getText().toString().trim();

        //Verificamos que los datos no estan vacios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(Activity2_Login.this, "Bienvenido: " + TextEmail2.getText(), Toast.LENGTH_LONG).show();
                            Intent inicio = new Intent(getApplication(), Activity_Principal.class);
                            inicio.putExtra(Activity_Principal.user, user);
                            startActivity(inicio);


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(Activity2_Login.this, "El usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Activity2_Login.this, "El usuario no existe ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });




    }

    @Override
    public void onClick(View view) {
        iniciarrUsuario();
    }


    public void VolverAtras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

}

