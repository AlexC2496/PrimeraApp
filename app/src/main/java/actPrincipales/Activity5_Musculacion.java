package actPrincipales;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primeraapp.R;
import ejercicios.ejercicios_abdominales;
import ejercicios.ejercicios_brazo;
import ejercicios.ejercicios_espalda;
import ejercicios.ejercicios_hombro;

public class Activity5_Musculacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5_musculacion);
    }

    public void Atras(View view) {
        Intent atras = new Intent(this, Activity_Principal.class);
        startActivity(atras);
    }

    public void SiguientePecho(View view) {
        Intent siguientePecho = new Intent(this, MainActivity_Ejercicios.class);
        startActivity(siguientePecho);
    }

    public void SiguienteHombro(View view) {
        Intent siguientehombro = new Intent(this, ejercicios_hombro.class);
        startActivity(siguientehombro);
    }

    public void SiguienteEspalda(View view) {
        Intent siguienteEspalda = new Intent(this, ejercicios_espalda.class);
        startActivity(siguienteEspalda);
    }

    public void SiguienteAbdominales(View view) {
        Intent siguienteAbdominales = new Intent(this, ejercicios_abdominales.class);
        startActivity(siguienteAbdominales);
    }

    public void SiguienteBrazo(View view) {
        Intent siguienteBrazo = new Intent(this, ejercicios_brazo.class);
        startActivity(siguienteBrazo);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
         if(id == R.id.item2){
            Intent atras = new Intent(this, Activity_Principal.class);
            startActivity(atras);
            Toast.makeText(this,"Go back",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}