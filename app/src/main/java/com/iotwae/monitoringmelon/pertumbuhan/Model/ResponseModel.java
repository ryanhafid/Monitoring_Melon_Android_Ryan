package com.iotwae.monitoringmelon.pertumbuhan.Model;

import java.util.List;

public class ResponseModel {
    private int kode;
    private String pesan;
    private String datetime;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }

    private List <DataModel> data ;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}

