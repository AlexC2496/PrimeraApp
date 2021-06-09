package actPrincipales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
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
    NotificationCompat.Builder notificacion;
    private Button boton;
    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int idUnica = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDayNight();
        boton =(Button) findViewById(R.id.button5);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPendingIntent();
                createNotificationChannel();
                createNotification();
            }
        });

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
    public void setDayNight(){
        SharedPreferences sp = getSharedPreferences("SP", this.MODE_PRIVATE);
       int theme = sp.getInt("Theme", 1);
        if(theme==0){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    private void setPendingIntent(){
        Intent intent = new Intent(this,Activity2_Login.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Activity2_Login.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_directions_run_24);
        builder.setContentTitle("Hola");
        builder.setContentText("Bienvenido a la aplicacion de ACFIT");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(idUnica, builder.build());
    }

        public void Registrar (View view)
        {
            Intent registrar = new Intent (this, Activity3_Registrarse.class);
            startActivity(registrar);
        }
        /*public void Inicio (View view)
        {
        Intent iniciar = new Intent (this, Activity2_Login.class);
        startActivity(iniciar);
        }*/

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
