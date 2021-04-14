package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

public class ejercicios_cardio extends AppCompatActivity {

    WebView webView;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios_cardio);

        videoView = (VideoView) findViewById(R.id.wv_cardio);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.video));
        videoView.start();
    }
}