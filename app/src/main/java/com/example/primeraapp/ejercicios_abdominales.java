package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ejercicios_abdominales extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_abdominales);

        webView= (WebView) findViewById(R.id.wv_abdominales);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.youtube.com/watch?v=zjrH2fLnh1I&ab_channel=EntrenaconSergioPeinado");
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return true;
    }
    public boolean onOptionsSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.item2) {
            Intent atras = new Intent(this, MainActivity.class);
            startActivity(atras);
            Toast.makeText(this, "Go back", Toast.LENGTH_SHORT).show();
        }return super.onOptionsItemSelected(item);
    }
}