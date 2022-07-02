package com.iotwae.monitoringmelon.pembibitan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iotwae.monitoringmelon.R;

public class GrafikCahayaPembibitan extends AppCompatActivity {
    WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_cahaya_pembibitan);
        webView1 = findViewById(R.id.WV_cahaya);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://melon.iotwae.com/android/chart/chart-light.php");

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