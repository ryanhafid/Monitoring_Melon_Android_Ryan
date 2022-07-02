package com.iotwae.monitoringmelon.pertumbuhan.API;

import com.iotwae.monitoringmelon.pertumbuhan.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("/android/pertumbuhan/retrieve.php")
    Call<ResponseModel> ardRetrieveData();
    //nama methode utk interface data retrieve.php (yg dipanggil adlh url retrieve.php dimana punya
    // kode pesan dan data yg ketiganya itu sudah didftrin di responsemodel)
}
