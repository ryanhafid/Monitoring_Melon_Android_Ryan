package com.iotwae.monitoringmelon.pembibitan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.iotwae.monitoringmelon.R;
import com.iotwae.monitoringmelon.pembibitan.API.APIRequestData;
import com.iotwae.monitoringmelon.pembibitan.API.RetroServer;
import com.iotwae.monitoringmelon.pembibitan.Adapter.AdapterData;
import com.iotwae.monitoringmelon.pembibitan.Model.DataModel;
import com.iotwae.monitoringmelon.pembibitan.Model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePembibitan extends AppCompatActivity {

    private RecyclerView rvData;
    private AdapterData adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    Handler mHandler;
    private TextView tvdatetime;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembibitan);
        this.mHandler = new Handler();

        //findviewkan
        tvdatetime = findViewById(R.id.datetime);
        rvData = findViewById(R.id.recycledata);
        srlData = findViewById(R.id.swp_data);
        pbData = findViewById(R.id.pb_data);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);      //recyce akan discroll ke atas ke bawah
        rvData.setLayoutManager(lmData);
        pbData.setVisibility(View.VISIBLE);

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {                                  // settingan buat refresh swipe layoutnya
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true); //jdi refresshnya jln
//                  retrieveData();                // dipanggil
                srlData.setRefreshing(false);  //false
            }
        });
        
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home_navigation:
                        break ;
                    case R.id.grafik_navigation:
                        startActivity(new Intent(getApplicationContext(), GrafikPembibitan.class));
                        overridePendingTransition(0,0);
                        break ;
                    case R.id.histori_navigation:
                        startActivity(new Intent(getApplicationContext(), HistoriPembibitan.class));
                        overridePendingTransition(0,0);
                        break ;
                }
                return false;
            }
        });
    }
    @Override
    protected void onResume() { //supaya data tidak terpaused dan onresume sudah bisa mengcover kebutuhan di oncreate
        super.onResume();
        m_runnable.run();
//        retrieveData();
    }
    public void retrieveData() { // method adapter
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);                          //memanggil APIRequestDta yang sudah dibuat
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponseModel>() { //fungsi retrofit
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {                    //menagkap kalo sudah berhasil atau mampu menghub server
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                String datetime = response.body().getDatetime();

                tvdatetime.setText(datetime);                                                                       //deklas tvdatetime bisa beda asal string pake datetime ssi db

//                Toast.makeText(MainActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adData = new AdapterData(HomePembibitan.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE); //pb akan hilang ketika data sudah ada
            }


            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) { // kalo gagal menghub server
//                Toast.makeText(HomePembibitan.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });

    }

    private final Runnable m_runnable = new Runnable() {
        @Override
        public void run() {
            retrieveData();
//            Toast.makeText(MainActivity.this, "cek", Toast.LENGTH_SHORT).show();
            HomePembibitan.this.mHandler.postDelayed(m_runnable, 5000); //realtime data dari db ke cardview .. waktu berapa detik hingga di upload di cardview android
        }
    };
}