package com.example.primeraapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Resetear_Contrasena extends AppCompatActivity {
    private TextView info;
    private EditText et_emailReset;
    private String email , auxInfo;
    private Button bt_resetPasswd;
    private FirebaseAuth mAuth;
    View view;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetear__contrasena);

        //Asignacion de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Asignacio de ProgressDialog
        mDialog = new ProgressDialog(this);

        //Asignacion de TextView
        info = findViewById(R.id.textView_infoResetPasswd);
        //Asigacion de EditText
        et_emailReset = findViewById(R.id.Text_EmailResetPassword);
        //Asignacion de Button
        bt_resetPasswd = findViewById(R.id.button_reiniciarContraseña);
        auxInfo = info.getText().toString();
    }

    public void RestablecerPassword(View view){

        email = et_emailReset.getText().toString();

        if(!email.isEmpty()){

            mDialog.setMessage("Espere un momento...");
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
            ReiniciarPassword();

        }else{
            Toast.makeText(this, "Debes ingresar un email", Toast.LENGTH_SHORT).show();
        }

    }


    private void ReiniciarPassword() {

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    info.setText(auxInfo +" "+email);
                    info.setVisibility(view.VISIBLE);

                    et_emailReset.setVisibility(view.INVISIBLE);
                    bt_resetPasswd.setVisibility(view.INVISIBLE);

                }else{
                    Toast.makeText(Resetear_Contrasena.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }
        });

    }

}