package com.iotwae.monitoringmelon.pembibitan.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetroServer {
    private static final String baseURL = "http://melon.iotwae.com"; //Pak Eko
//    private static final String baseURL = "http://ryanfhmnf.telkom4b.com"; //Kelas
    private static Retrofit retro;

    public static Retrofit konekRetrofit() {
        if (retro == null) {                                                //kalo belum terjalin koneksi maka retro akan dihub/dibangun ke baseurl
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())     //library yng akan mengconvert data dalam bentuk JSON untuk bisa dikenali di dlm android
                    .build();
        }
        return retro;
    }
}
