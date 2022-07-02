package com.iotwae.monitoringmelon.pembibitan.API;

import com.iotwae.monitoringmelon.pembibitan.Model.ResponseHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIRequestHistory {
    @GET("/android/pembibitan/retrieve_history.php") //API untuk data history (Pak Eko)
//    @GET("/smartgreenhouse/retrieve_history.php") //API untuk data history (Kelas)
    Call<ResponseHistory> ardRetrieveData();

    @GET("/android/pembibitan/api_history.php") //API untuk data filter (Pak Eko)
//    @GET("/smartgreenhouse/api_history.php") //API untuk data filter (Kelas)
    Call<ResponseHistory> ardRetrieveFilter(@Query("startDate") String startDate,
                                            @Query("endDate") String endDate);

}
