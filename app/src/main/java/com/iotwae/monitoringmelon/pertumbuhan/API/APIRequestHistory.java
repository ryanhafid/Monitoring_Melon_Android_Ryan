package com.iotwae.monitoringmelon.pertumbuhan.API;

import com.iotwae.monitoringmelon.pertumbuhan.Model.ResponseHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIRequestHistory {
    @GET("/android/pertumbuhan/retrieve_history.php") //API untuk data history
    Call<ResponseHistory> ardRetrieveData();

    @GET("/android/pertumbuhan/api_history.php") //API untuk data filter
    Call<ResponseHistory> ardRetrieveFilter(@Query("startDate") String startDate,
                                            @Query("endDate") String endDate);

}
