package com.iotwae.monitoringmelon.pertumbuhan.Model;

import java.util.List;

public class ResponseHistory {
    private int kode;
    private String pesan;
    private List<HistoryModel> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<HistoryModel> getData() {
        return data;
    }

    public void setData(List<HistoryModel> data) {
        this.data = data;
    }
}

