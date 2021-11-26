package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class mat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);


        Intent i=getIntent();

        Bundle b=i.getExtras();
        String pdf_url=String.valueOf(b.get("link"));

        WebView webView=(WebView)findViewById(R.id.webview2);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(pdf_url);
    }
}