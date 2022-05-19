package com.iotwae.monitoringmelon.pembibitan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.iotwae.monitoringmelon.R;

public class PembibitanActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembibitan);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        //        Agar tampilan awalnya ke fragment Home
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomePembibitanFragment()).commit();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.home_navigation:
                        selectedFragment = new HomePembibitanFragment();
                        break;
                    case R.id.grafik_navigation:
                        selectedFragment = new GrafikPembibitanFragment();
                        break;
                    case R.id.histori_navigation:
                        selectedFragment = new HistoriPembibitanFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });

        }
}