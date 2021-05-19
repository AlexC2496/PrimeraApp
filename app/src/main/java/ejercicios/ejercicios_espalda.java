package ejercicios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.primeraapp.R;

public class ejercicios_espalda extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_espalda);


        webView= (WebView) findViewById(R.id.wv_espalda);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com/watch?v=42Ufgq1oeq4&ab_channel=EntrenaconSergioPeinado");
    }
}