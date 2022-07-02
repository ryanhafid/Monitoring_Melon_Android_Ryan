package com.iotwae.monitoringmelon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 2000;

    ImageView ive_splash;
    TextView tv_splash1;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);


        ive_splash = findViewById(R.id.ive_splash);
        tv_splash1 = findViewById(R.id.tv_splash1);


        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        ive_splash.setAnimation(top);
        tv_splash1.setAnimation(bottom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected()) {
                    new AlertDialog.Builder(SplashScreen.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .setTitle("Tidak Ada Koneksi Internet")
                            .setMessage("Silakan periksa koneksi internet anda dan coba lagi!")
                            .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    System.exit(0);
                                }
                            }).show();
                } else {
                    Toast.makeText(SplashScreen.this, "Terhubung Dengan Internet", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                }
            }
        }, SPLASH_SCREEN);
    }
    private boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}