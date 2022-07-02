package com.iotwae.monitoringmelon.pertumbuhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.iotwae.monitoringmelon.R;

public class GrafikPertumbuhan extends AppCompatActivity implements View.OnClickListener {
    private CardView temp_udr_card, hum_udr_card, temp_tnh_card, hum_tnh_card, ph_card, light_card;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_pertumbuhan);

        //        Bottom Navigasi
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.grafik_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_navigation:
                        startActivity(new Intent(getApplicationContext(), HomePertumbuhan.class));
                        overridePendingTransition(0, 0);
                        finish();
                    case R.id.grafik_navigation:
                        break;
                    case R.id.histori_navigation:
                        startActivity(new Intent(getApplicationContext(), HistoriPertumbuhan.class));
                        overridePendingTransition(0, 0);
                        finish();
                }
                return false;
            }
        });

        //        Komponen
        temp_udr_card = (CardView) findViewById(R.id.tbh_suhu_udr);
        hum_udr_card = (CardView) findViewById(R.id.tbh_kelembapan_udr);
        temp_tnh_card = (CardView) findViewById(R.id.tbh_suhu_tnh);
        hum_tnh_card = (CardView) findViewById(R.id.tbh_kelembapan_tnh);
        ph_card = (CardView) findViewById(R.id.tbh_ph);
        light_card = (CardView) findViewById(R.id.tbh_cahaya);

        temp_udr_card.setOnClickListener(this);
        hum_udr_card.setOnClickListener(this);
        temp_tnh_card.setOnClickListener(this);
        hum_tnh_card.setOnClickListener(this);
        ph_card.setOnClickListener(this);
        light_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.tbh_suhu_udr:
                intent = new Intent(this, GrafikSuhuUdrPertumbuhan.class);
                startActivity(intent);
                break;
            case R.id.tbh_kelembapan_udr:
                intent = new Intent(this, GrafikKelembapanUdrPertumbuhan.class);
                startActivity(intent);
                break;
            case R.id.tbh_suhu_tnh:
                intent = new Intent(this, GrafikSuhuTnhPertumbuhan.class);
                startActivity(intent);
                break;
            case R.id.tbh_kelembapan_tnh:
                intent = new Intent(this, GrafikKelembapanTnhPertumbuhan.class);
                startActivity(intent);
                break;
            case R.id.tbh_ph:
                intent = new Intent(this, GrafikPHPertumbuhan.class);
                startActivity(intent);
                break;
            case R.id.tbh_cahaya:
                intent = new Intent(this, GrafikCahayaPertumbuhan.class);
                startActivity(intent);
                break;
        }

    }

}
