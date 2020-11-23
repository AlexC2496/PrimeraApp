package com.example.primeraapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3_Registrarse<firebaseAuth> extends AppCompatActivity implements View.OnClickListener {

    TextView Opciones;
    Spinner combo;
    private EditText TextEmail;
    private EditText TextPassword;
    private EditText TextName;
    private EditText textTelefono;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    private CheckBox seleccionDireccion;
    private TextView Textsexo;


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    TextView opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3__registrarse);
        Opciones = (TextView) findViewById(R.id.opciones);
        combo = (Spinner) findViewById(R.id.spinnerOpciones);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo, android.R.layout.simple_spinner_item);
        combo.setAdapter(adapter);
        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getApplicationContext(),"Seleccion: "+adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
                Opciones.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Inicializamos objeto
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        //Referecnias
        TextEmail = (EditText) findViewById(R.id.email);
        TextPassword = (EditText) findViewById(R.id.password);
        TextName = (EditText) findViewById(R.id.nombre);
        textTelefono = (EditText) findViewById(R.id.telefono);
        Textsexo =(TextView) findViewById(R.id.opciones);

        btnRegistrar = (Button) findViewById(R.id.btnAceptar);
        progressDialog = new ProgressDialog(this);


        btnRegistrar.setOnClickListener(this);

        seleccionDireccion = (CheckBox) findViewById(R.id.terminos1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void registrarUsuario() {
        //Obtener el email y la contraseña
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();
        final String nombre = TextName.getText().toString().trim();
        final String telefono = textTelefono.getText().toString().trim();
        final String sexo = Textsexo.getText().toString().trim();


        //Verificamos que los datos no estan vacios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Falta ingresar el nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(this, "Falta ingresar el telefono", Toast.LENGTH_LONG).show();
            return;
        }



        progressDialog.setMessage("Realizando registro de usuario...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Usuario usuario = new Usuario(nombre,email,telefono,sexo);


                            FirebaseDatabase.getInstance().getReference("Usuario")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity3_Registrarse.this, "Se ha registrado el usuario con el email: " + TextEmail.getText(), Toast.LENGTH_LONG).show();

                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(MainActivity3_Registrarse.this, "El usuario ya existe", Toast.LENGTH_LONG).show();
                                            Intent inicio = new Intent(getApplication(), MainActivity2_Login.class);
                                        } else {
                                            Toast.makeText(MainActivity3_Registrarse.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                                        }
                                        progressDialog.dismiss();
                                    }
                                }

                            });

                        }
                    }
                });
    }



    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
    public void onClick(View view) {
            registrarUsuario();

        }

}