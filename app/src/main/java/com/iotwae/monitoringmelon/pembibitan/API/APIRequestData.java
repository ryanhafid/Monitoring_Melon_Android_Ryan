package com.iotwae.monitoringmelon.pembibitan.API;

import com.iotwae.monitoringmelon.pembibitan.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("/android/pembibitan/retrieve.php") //Pak Eko
//    @GET("/smartgreenhouse/retrieve.php") //Kelas
    Call<ResponseModel> ardRetrieveData();
    //nama methode utk interface data retrieve.php (yg dipanggil adlh url retrieve.php dimana punya
    // kode pesan dan data yg ketiganya itu sudah didftrin di responsemodel)
}

