package com.upb.agripos.Model;

public class ObatHama extends Produk {
    private String kandungan;

    public ObatHama(String kode, String nama, double harga, int stok, String kandungan) {
        super(kode, nama, harga, stok);
        this.kandungan = kandungan;
    }

    @Override
    public String getInfo() {
        return "Obat Hama - " + super.getInfo() + ", Kandungan: " + kandungan;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }
}