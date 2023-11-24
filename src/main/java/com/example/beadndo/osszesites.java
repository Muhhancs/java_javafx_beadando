package com.example.beadndo;

public class osszesites {

    public String pizzanev;
    public String kategorianev;
    public boolean vegetarianus;
    public int az;
    public int darab;
    public String felvetel;
    public String kiszallitas;
    public int ar;

    public osszesites(String pizzanev, String kategorianev, boolean vegetarianus, int az, int darab, String felvetel, String kiszallitas, int ar) {
        this.pizzanev = pizzanev;
        this.kategorianev = kategorianev;
        this.vegetarianus = vegetarianus;
        this.az = az;
        this.darab = darab;
        this.felvetel = felvetel;
        this.kiszallitas = kiszallitas;
        this.ar = ar;
    }

    public String getPizzanev() {
        return pizzanev;
    }

    public String getKategorianev() {
        return kategorianev;
    }

    public boolean isVegetarianus() {
        return vegetarianus;
    }

    public int getAz() {
        return az;
    }

    public int getDarab() {
        return darab;
    }

    public String getFelvetel() {
        return felvetel;
    }

    public String getKiszallitas() {
        return kiszallitas;
    }

    public int getAr() {
        return ar;
    }
}
