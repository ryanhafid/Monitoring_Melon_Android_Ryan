package com.iotwae.monitoringmelon.pertumbuhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iotwae.monitoringmelon.R;

public class GrafikPHPertumbuhan extends AppCompatActivity {
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_phpertumbuhan);
        webView1 = findViewById(R.id.WV_tbh_ph);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://melon.iotwae.com/android/chart/tbh-chart-ph.php");

        WebSettings webSettings = webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        webView1.goBack();
    }
}