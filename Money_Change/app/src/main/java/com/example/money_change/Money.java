package com.example.money_change;

public class Money {
    public Money(String tien, String tientetat) {
        this.tien = tien;
        this.tientetat = tientetat;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }

    public String getTientetat() {
        return tientetat;
    }

    public void setTientetat(String tientetat) {
        this.tientetat = tientetat;
    }

    String tien,tientetat;
}
