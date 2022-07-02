package com.iotwae.monitoringmelon.pembibitan;

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

public class GrafikPembibitan extends AppCompatActivity implements View.OnClickListener {
    private CardView temp_udr_card, hum_udr_card, temp_tnh_card, hum_tnh_card, light_card;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_pembibitan);

//        Bottom Navigasi
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.grafik_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_navigation:
                        startActivity(new Intent(getApplicationContext(), HomePembibitan.class));
                        overridePendingTransition(0, 0);
                        finish();
                    case R.id.grafik_navigation:
                        break;
                    case R.id.histori_navigation:
                        startActivity(new Intent(getApplicationContext(), HistoriPembibitan.class));
                        overridePendingTransition(0, 0);
                        finish();
                }
                return false;
            }
        });

        //        Komponen
        temp_udr_card = (CardView) findViewById(R.id.suhu_udr);
        hum_udr_card = (CardView) findViewById(R.id.kelembapan_udr);
        temp_tnh_card = (CardView) findViewById(R.id.suhu_tnh);
        hum_tnh_card = (CardView) findViewById(R.id.kelembapan_tnh);
        light_card = (CardView) findViewById(R.id.cahaya);

        temp_udr_card.setOnClickListener(this);
        hum_udr_card.setOnClickListener(this);
        temp_tnh_card.setOnClickListener(this);
        hum_tnh_card.setOnClickListener(this);
        light_card.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.suhu_udr:
                intent = new Intent(this, GrafikSuhuUdrPembibitan.class);
                startActivity(intent);
                break;
            case R.id.kelembapan_udr:
                intent = new Intent(this, GrafikKelembapanUdrPembibitan.class);
                startActivity(intent);
                break;
            case R.id.suhu_tnh:
                intent = new Intent(this, GrafikSuhuTnhPembibitan.class);
                startActivity(intent);
                break;
            case R.id.kelembapan_tnh:
                intent = new Intent(this, GrafikKelembapanTnhPembibitan.class);
                startActivity(intent);
                break;
            case R.id.cahaya:
                intent = new Intent(this, GrafikCahayaPembibitan.class);
                startActivity(intent);
                break;
        }

    }

}
