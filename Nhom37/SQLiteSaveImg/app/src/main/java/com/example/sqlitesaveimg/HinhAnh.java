package com.example.sqlitesaveimg;

public class HinhAnh {
    private byte[] hinh;
    private int id;

    public HinhAnh( int id,byte[] hinh) {
        this.hinh = hinh;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
