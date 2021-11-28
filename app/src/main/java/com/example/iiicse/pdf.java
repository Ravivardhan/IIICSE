package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class pdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Intent i=getIntent();

        Bundle b=i.getExtras();
        String pdf_url=String.valueOf(b.get("link"));
        String by=String.valueOf(b.get("by"));
        Toast.makeText(this, "uploaded by "+by, Toast.LENGTH_SHORT).show();

        WebView webView=(WebView)findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(pdf_url);
    }
}