package actPrincipales;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primeraapp.ActivityEjercicio;
import com.example.primeraapp.AdminSQLiteOpenHelper;
import com.example.primeraapp.R;
import com.example.primeraapp.RecyckerViewCardView;
import com.example.primeraapp.Usuario;
import com.example.primeraapp.listElement;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import ejercicios.ejercicios_cardio;

public class Activity_Principal extends AppCompatActivity {
    public static final String user="names";
    TextView txtUser;

    ImageView profile;
    Button btnGallery;
    Button btnAct;
    Usuario usuario;
    private Button cerrarSesion;
    FirebaseUser frebaseUser;
    FirebaseAuth firebaseAuth;
    private SharedPreferences prefs;
    private static final int REQUEST_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_principal);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);



        txtUser=(TextView)findViewById(R.id.principal3);
        String user = getIntent().getStringExtra("names");
        txtUser.setText(user);
        btnGallery = findViewById(R.id.btnGallery);
        profile = findViewById(R.id.perfil);
        cerrarSesion = (Button) findViewById(R.id.item3);

        final AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(getApplicationContext());

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(Activity_Principal.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        subirFoto();
                    }else{
                        ActivityCompat.requestPermissions(Activity_Principal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                }else{
                    subirFoto();
                }
            }
        });

    }

    private void logOut() {
        Intent intent = new Intent(this,Activity2_Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void removeSharedPreferences(){
        prefs.edit().clear().apply();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item1){
            Intent siguiente2 = new Intent(this, Activity5_Musculacion.class);
            startActivity(siguiente2);
            Toast.makeText(this,"Go to exsercise",Toast.LENGTH_SHORT).show();
        } else if(id == R.id.item2) {
            Intent atra2s = new Intent(this, MainActivity.class);
            startActivity(atra2s);
            Toast.makeText(this, "Go back", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item3){
            logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onActivityResult (int requestcode, int resultCode, @Nullable Intent data){
        if(requestcode == REQUEST_IMAGE_GALLERY){
            if(resultCode == Activity.RESULT_OK && data != null){
                Uri photo = data.getData();
                profile.setImageURI(photo);
            }else{
                Toast.makeText(this, "No se recogió ninguna foto de la galeria",Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestcode, resultCode, data);
    }*/
    @Override
    public void onRequestPermissionsResult (int requestcode, @NonNull String[] permissions, @NonNull int [] grantResults){
        if (requestcode == REQUEST_PERMISSION_CODE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                subirFoto();
            }else{
                Toast.makeText(this, "Necesitas habilitar los permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestcode, permissions, grantResults);
    }

    /*private void openGallery()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }*/



    public void Siguiente(View view) {
        Intent siguiente = new Intent(this, ActivityEjercicio.class);
        startActivity(siguiente);
    }

    public void SiguienteCardio(View view) {
        Intent siguientec = new Intent(this, ejercicios_cardio.class);
        startActivity(siguientec);
    }

    public void Atras(View view) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

    public void siguienteVideos(View view) {
        Intent siguienteVideos = new Intent(this, Activity5_Musculacion.class);
        startActivity(siguienteVideos);
    }





    Dialog dialogSubirFoto;
    final  int CODIGO_RESPUESTA_GALERIA = 3;
    Uri uri;
    ImageView imgFotoPerfil;
    private void subirFoto(){
        dialogSubirFoto = new Dialog(Activity_Principal.this, android.R.style.Theme_Dialog);
        dialogSubirFoto.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSubirFoto.setContentView(R.layout.subir_foto);
        imgFotoPerfil = (ImageView)dialogSubirFoto.findViewById(R.id.imgPerfil);
        Button btnEliminar = (Button)dialogSubirFoto.findViewById(R.id.btnEliminar);
        Button btnGaleria = (Button)dialogSubirFoto.findViewById(R.id.btnGuardar);
        Button btnCancelar = (Button)dialogSubirFoto.findViewById(R.id.btnCancel);

        Picasso.get().load (usuario.getFoto_perfil()).into (imgFotoPerfil);
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CODIGO_RESPUESTA_GALERIA);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSubirFoto.dismiss();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storageReference.child("Perfil").child(FirebaseAuth.getInstance().getCurrentUser().getUid() + ".jpg").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        imgFotoPerfil.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.perona));
                    }
                });
            }
            });
        dialogSubirFoto.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogSubirFoto.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogSubirFoto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogSubirFoto.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode ==RESULT_OK){
            if (data!=null){
                uri = data.getData();
                try {
                    imgFotoPerfil.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri));
                    UploadTask uploadTask;
                    final StorageReference reference = storageReference.child("Perfil").child(FirebaseAuth.getInstance().getCurrentUser().getUid()+".jpg");
                    uploadTask = reference.putFile(uri);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful())
                                throw task.getException();
                            return reference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()){
                                Map<String, Object> map = new HashMap<>();
                                map.put("foto_perfil", task.getResult().toString());
                                FirebaseFirestore.getInstance().collection("Usuario").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(),"Imagen Actualizada",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                }catch (Exception e){
                    Log.e("Error",""+e.toString());
                }

            }
        }
    }

}