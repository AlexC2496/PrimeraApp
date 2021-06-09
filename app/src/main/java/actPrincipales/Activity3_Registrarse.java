package actPrincipales;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primeraapp.R;
import com.example.primeraapp.Usuario;
import com.example.primeraapp.terminos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class Activity3_Registrarse<firebaseAuth> extends AppCompatActivity implements View.OnClickListener {

    TextView Opciones;
    Spinner combo;
    private EditText TextEmail;
    private EditText TextPassword;
    private EditText TextName;
    private EditText textTelefono;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    private TextView seleccionDireccion;
    private TextView Textsexo;
    private TextView opciones;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3__registrarse);

        //Creacion del spinner
        Opciones = (TextView) findViewById(R.id.opciones);
        combo = (Spinner) findViewById(R.id.spinnerOpciones);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo, android.R.layout.simple_spinner_item);
        combo.setAdapter(adapter);
        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Opciones.setText(adapterView.getItemAtPosition(i).toString()); //Se mostrará en pantalla la opcion elegida por el usuario
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

        seleccionDireccion = (TextView) findViewById(R.id.terminos1);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //Registro de usuario
    private void registrarUsuario() {
        //Obtener los datos del usuario
        final String email = TextEmail.getText().toString().trim();
        final String password = TextPassword.getText().toString().trim();
        final String nombre = TextName.getText().toString().trim();
        final String telefono = textTelefono.getText().toString().trim();
        final String sexo = Textsexo.getText().toString().trim();


        //Verificamos que los datos no estan vacios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Falta ingresar el nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(this, "Falta ingresar el telefono", Toast.LENGTH_SHORT).show();
            return;
        }

      

        progressDialog.setMessage("Realizando registro de usuario..."); //Menaje que se muestra mientras el usuario se esta registrando.
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Usuario usuario = new Usuario(nombre,email,telefono,sexo,password); //Creacion de un onnjeto de tipo usuario


                            FirebaseDatabase.getInstance().getReference("Usuario")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Activity3_Registrarse.this, "Se ha registrado el usuario con el email: " + TextEmail.getText(), Toast.LENGTH_SHORT).show();

                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(Activity3_Registrarse.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                                            Intent inicio = new Intent(getApplication(), Activity2_Login.class);
                                        } else {
                                            Toast.makeText(Activity3_Registrarse.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                                        }
                                        progressDialog.dismiss();
                                    }
                                }

                            });

                        }
                    }
                });
    }


    //Boton para volver atras
    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
    public void politica(View view) {
        Intent politicas = new Intent(this, terminos.class);
        startActivity(politicas);
    }


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        //Invocamos al metodo de registro de usuario
    public void onClick(View view) {
            registrarUsuario();

        }
}