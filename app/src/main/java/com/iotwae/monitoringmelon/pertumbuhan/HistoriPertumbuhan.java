package com.iotwae.monitoringmelon.pertumbuhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.iotwae.monitoringmelon.R;
import com.iotwae.monitoringmelon.pertumbuhan.API.APIRequestHistory;
import com.iotwae.monitoringmelon.pertumbuhan.API.RetroServer;
import com.iotwae.monitoringmelon.pertumbuhan.Adapter.AdapterHistory;
import com.iotwae.monitoringmelon.pertumbuhan.GrafikPertumbuhan;
import com.iotwae.monitoringmelon.pertumbuhan.HistoriPertumbuhan;
import com.iotwae.monitoringmelon.pertumbuhan.HomePertumbuhan;
import com.iotwae.monitoringmelon.pertumbuhan.Model.HistoryModel;
import com.iotwae.monitoringmelon.pertumbuhan.Model.ResponseHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriPertumbuhan extends AppCompatActivity {

    EditText dateText1, dateText2;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<HistoryModel> listData = new ArrayList<HistoryModel>();
    //    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    long startTime = 0;
    long endtime = 0;
    TextView tv_nodata;
    Button buttonFilter, buttonFilterasc, buttonFilterdesc;
    String outputDate;
    //    DatePickerDialog.OnDateSetListener setListener;
    SimpleDateFormat formattgl = new SimpleDateFormat("d-M-yyyy");

    FloatingActionButton floatSort, floatUp, floatDown;
    Float translationYaxis = 100f;
    Boolean menuOpen = false;
    OvershootInterpolator interpolator  = new OvershootInterpolator();

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_pertumbuhan);

//        Bottom Navigasi
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.histori_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home_navigation:
                        startActivity(new Intent(getApplicationContext(), HomePertumbuhan.class));
                        overridePendingTransition(0,0);
                        finish();
                    case R.id.grafik_navigation:
                        startActivity(new Intent(getApplicationContext(), GrafikPertumbuhan.class));
                        overridePendingTransition(0,0);
                        finish();
                    case R.id.histori_navigation:
                        break ;
                }
                return false;
            }
        });


        //data history
        rvData = findViewById(R.id.recycledata);
//        srlData = findViewById(R.id.swp_data);
        pbData = findViewById(R.id.pb_data);

        tv_nodata = findViewById(R.id.nodata);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //recyce akan discroll ke atas ke bawah
        rvData.setLayoutManager(lmData);

        //filter data
        buttonFilter = findViewById(R.id.button_filter);

        dateText1 = findViewById(R.id.date1);
        dateText2 = findViewById(R.id.date2);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        outputDate = formattgl.format(Calendar.getInstance().getTime());

        dateText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        HistoriPertumbuhan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date =  day+"-"+month+"-"+year;
                        dateText1.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        dateText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        HistoriPertumbuhan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date =  day+"-"+month+"-"+year;
                        dateText2.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = String.valueOf(dateText1.getText());
                String endDate = String.valueOf(dateText2.getText());
                Log.d("tgl", startDate);
                retrieveData(startDate, endDate);
//                startTime = System.currentTimeMillis(); //waktu pengambilan data dri aplikasi ke server
//                Log.d("delay", String.valueOf(startTime)); //muncul hasil waktu pertama pengambila di debug(d)
                showMenu();
            }
        });

    }
    private void showMenu() {
        floatSort = findViewById(R.id.updown_float);
        floatUp = findViewById(R.id.up_float);
        floatDown = findViewById(R.id.down_float);

        floatUp.setAlpha(0f);
        floatDown.setAlpha(0f);

        floatUp.setTranslationY(translationYaxis);
        floatDown.setTranslationY(translationYaxis);

        floatSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuOpen) {
                    CloseMenu();
                }else {
                    OpenMenu();
                }
            }
        });

        floatUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listData, new Comparator<HistoryModel>() {
                    @Override
                    public int compare(HistoryModel o1, HistoryModel o2) {
                        return o1.getWaktu().compareTo(o2.getWaktu());
                    }
                });
                adData.notifyDataSetChanged();
            }
        });

        floatDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(listData);
                adData.notifyDataSetChanged();
            }
        });
    }
    private void OpenMenu() {
        menuOpen = ! menuOpen;
        floatSort.setImageResource(R.drawable.close_float);
        floatUp.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        floatDown.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();

    }
    private void CloseMenu() {
        menuOpen = ! menuOpen;
        floatSort.setImageResource(R.drawable.updown);
        floatUp.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        floatDown.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }
    //filter history data
    @Override
    public void onResume() { //supaya data tidak terpaused dan onresume sudah bisa mengcover kebutuhan di oncreate
        super.onResume();
        Log.d("tgl", outputDate);
//        retrieveData(outputDate, outputDate); //onresume sudah bisa mengcover kebutuhan di oncreate termasuk ketika ada penghapusan di db maka di android akan hilang krn terhapus dan ketika ada penambahan data di db maka di android akan terupdate dengan otomatis
    }
    private void retrieveData(String date1, String date2) {
        pbData.setVisibility(View.VISIBLE); //pb akan hidup ketika proses retrieve dimulai / pb akan tampil

        APIRequestHistory ardData = RetroServer.konekRetrofit().create(APIRequestHistory.class); //memanggil api yg sudah dibuat dan menghub clas interface ke retrofitnya
        Call<ResponseHistory> tampilData = ardData.ardRetrieveFilter(date1, date2); //antrikan perintah

        tampilData.enqueue(new Callback<ResponseHistory>() { //fungsi retrofit
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) { //menagkap kalo sudah berhasil
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                // delay/waktu dari pengambilan data dri server sampe muncul di filter, history aplikasi android
//                endtime = System.currentTimeMillis();
//                Log.d("delay", String.valueOf(endtime));
//
//                // total delay muncul pop up
//                String delay = String.valueOf(endtime - startTime);
//
//                //jika start tidak sama dengan 0 maka tidak akan muncul pop up delay
//                if (startTime != 0) Toast.makeText(Filter.this, "Delay : " +delay+ " ms" , Toast.LENGTH_LONG).show();


                if (kode == 1) {
                    tv_nodata.setVisibility(View.GONE);
//                    srlData.setVisibility(View.VISIBLE);

                    listData = response.body().getData();
//                    Collections.reverse(listData); //data card terbaru yang muncul diatas

                    adData = new AdapterHistory(HistoriPertumbuhan.this, listData);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();

                }
                else {
                    tv_nodata.setVisibility(View.VISIBLE); //jika tidak ada data maka ket "data tidak tersedia' muncul
//                    srlData.setVisibility(View.INVISIBLE);
                }
                pbData.setVisibility(View.INVISIBLE); //pb akan hilang ketika data sudah ada
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) { //kalo gagal
//                Toast.makeText(Filter.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show(); //// untuk mengecek apakah terhubung atau tidak ke server

                pbData.setVisibility(View.INVISIBLE);
            }
        });

    }
}