package com.iotwae.monitoringmelon.pembibitan.Model;

public class HistoryModel {
    private int id;
    private String waktu, temp_udr, hum_udr, temp_tnh, hum_tnh, cahaya;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTemp_udr() {
        return temp_udr;
    }

    public void setTemp_udr(String temp_udr) {
        this.temp_udr = temp_udr;
    }

    public String getHum_udr() {
        return hum_udr;
    }

    public void setHum_udr(String hum_udr) {
        this.hum_udr = hum_udr;
    }

    public String getTemp_tnh() {
        return temp_tnh;
    }

    public void setTemp_tnh(String temp_tnh) {
        this.temp_tnh = temp_tnh;
    }

    public String getHum_tnh() {
        return hum_tnh;
    }

    public void setHum_tnh(String hum_tnh) {
        this.hum_tnh = hum_tnh;
    }

    public String getCahaya() {
        return cahaya;
    }

    public void setCahaya(String cahaya) {
        this.cahaya = cahaya;
    }
}