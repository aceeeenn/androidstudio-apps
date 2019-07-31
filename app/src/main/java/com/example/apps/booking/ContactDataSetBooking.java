package com.example.apps.booking;

import java.util.Date;

public class ContactDataSetBooking {

    String id, lapangan, alamat, nohp, harga;


    public ContactDataSetBooking() {
    }

    public ContactDataSetBooking(String id, String lapangan, String alamat, String nohp, String harga) {
        this.id = id;
        this.lapangan = lapangan;
        this.alamat = alamat;
        this.nohp = nohp;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLapangan() {
        return lapangan;
    }

    public void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

}
