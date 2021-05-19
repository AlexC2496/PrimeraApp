package actPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.example.primeraapp.MapsActivity;
import com.example.primeraapp.R;

public class MainActivity extends AppCompatActivity {

    private MenuItem item;
    Button play_pause;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_pause = (Button)findViewById(R.id.play_pause);
        mp = MediaPlayer.create(this,R.raw.cancion);
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                    Toast.makeText(MainActivity.this,"Pause",Toast.LENGTH_SHORT).show();
                }else{
                    mp.start();
                    play_pause.setBackgroundResource(R.drawable.pause);
                    Toast.makeText(MainActivity.this,"Play",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        public void Registrar (View view)
        {
            Intent registrar = new Intent (this, Activity3_Registrarse.class);
            startActivity(registrar);
        }
        public void Inicio (View view)
        {
        Intent iniciar = new Intent (this, Activity2_Login.class);
        startActivity(iniciar);
        }

    public void Mapa (View view) {
        Intent mapa = new Intent(this, MapsActivity.class);
        startActivity(mapa);
    }


    //Metodo para asignar funciones
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.item1){
            Toast.makeText(this,"Opcion1",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.item2){
            Toast.makeText(this,"Opcion2",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    }
