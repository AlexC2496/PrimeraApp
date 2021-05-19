package actPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.primeraapp.R;

public class MainActivity_Ejercicios extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicios_pecho);

      webView= (WebView) findViewById(R.id.wv_test);

      WebSettings  webSettings = webView.getSettings();
      webSettings.setJavaScriptEnabled(true);

      webView.setWebViewClient(new WebViewClient());
      webView.loadUrl("https://www.youtube.com/watch?v=LBDqQA8AVD4&ab_channel=EntrenaconSergioPeinado");


    }
}