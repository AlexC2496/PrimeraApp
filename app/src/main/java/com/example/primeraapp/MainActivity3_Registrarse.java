package com.example.primeraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity3_Registrarse<firebaseAuth> extends AppCompatActivity implements View.OnClickListener {

    TextView Opciones;
    Spinner combo;
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3__registrarse);
        Opciones = (TextView) findViewById(R.id.etiSeleccion);
        combo = (Spinner) findViewById(R.id.spinnerOpciones);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo, android.R.layout.simple_spinner_item);
        combo.setAdapter(adapter);

        //Inicializamos objeto
        firebaseAuth = FirebaseAuth.getInstance();

        //Referecnias
        TextEmail = (EditText) findViewById(R.id.email);
        TextPassword = (EditText) findViewById(R.id.password);

        btnRegistrar = (Button) findViewById(R.id.btnAceptar);
        progressDialog = new ProgressDialog(this);

        btnRegistrar.setOnClickListener(this);
    }
    
    private void registrarUsuario() {
        //Obtener el email y la contraseña
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que los datos no estan vacios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro de usuario...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity3_Registrarse.this, "Se ha registrado el usuario con el email: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(MainActivity3_Registrarse.this,"El usuario ya existe", Toast.LENGTH_LONG).show();
                                    Intent inicio = new Intent(getApplication(),MainActivity3_Registrarse.class);
                            }else {
                                Toast.makeText(MainActivity3_Registrarse.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }



    @Override
    public void onClick(View view) {
        registrarUsuario();

    }



    /*public void Siguiente(View view) {
        Intent siguiente = new Intent(this, MainActivity4Principal.class);
        startActivity(siguiente);
    }

    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }/*

     */
}