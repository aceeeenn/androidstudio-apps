package com.example.apps.booking;

import java.util.Date;

public class ContactDataSetBooking {

    String id, lapangan, alamat, nohp, jam, tanggal;



    public ContactDataSetBooking (){}

    public ContactDataSetBooking(String id, String lapangan, String alamat, String nohp, String tanggal) {
        this.id = id;
        this.lapangan = lapangan;
        this.alamat = alamat;
        this.nohp = nohp;
        this.tanggal = tanggal;
        this.jam = jam;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

}
