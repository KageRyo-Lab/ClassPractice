package com.coderyo.d20231221;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView= (WebView) findViewById(R.id.webView);
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true);//啟用JavaScript
        webView.setWebViewClient(new WebViewClient());//使頁面導航保持在WebView中
        webView.loadUrl("https://www.yahoo.com.tw");
    }


}