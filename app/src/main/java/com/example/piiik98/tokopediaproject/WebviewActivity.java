package com.example.piiik98.tokopediaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {

    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        url = getIntent().getExtras().getString("webURL");
        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl(url);

    }
}
