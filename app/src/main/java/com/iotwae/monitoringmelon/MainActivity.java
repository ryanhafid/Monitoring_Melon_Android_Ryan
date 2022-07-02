package com.iotwae.monitoringmelon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iotwae.monitoringmelon.pembibitan.HomePembibitan;
import com.iotwae.monitoringmelon.pertumbuhan.HomePertumbuhan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView pembibitanCard, pertumbuhanCard, panduanCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Komponen
        pembibitanCard = (CardView) findViewById(R.id.pembibitan);
        pertumbuhanCard = (CardView) findViewById(R.id.pertumbuhan);
        panduanCard = (CardView) findViewById(R.id.panduan);

        pembibitanCard.setOnClickListener(this);
        pertumbuhanCard.setOnClickListener(this);
        panduanCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.pembibitan:
                intent = new Intent(this, HomePembibitan.class);
                startActivity(intent);
                break;
            case R.id.pertumbuhan:
                intent = new Intent(this, HomePertumbuhan.class);
                startActivity(intent);
                break;
            case R.id.panduan:
                intent = new Intent(this, PanduanActivity.class);
                startActivity(intent);
                break;

        }

    }
}