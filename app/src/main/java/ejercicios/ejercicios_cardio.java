package ejercicios;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.primeraapp.R;

public class ejercicios_cardio extends AppCompatActivity {

    WebView webView;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_cardio);

        videoView = (VideoView) findViewById(R.id.wv_cardio);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.video));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}