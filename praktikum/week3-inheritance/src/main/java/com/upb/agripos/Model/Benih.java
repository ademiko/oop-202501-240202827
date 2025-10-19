package com.upb.agripos.Model;

public class Benih extends Produk{
    private String varietas;
    private String keterangan;

    public Benih(String kode, String nama, double harga, int stok, String varietas, String keterangan) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
        this.keterangan = keterangan;

    }
    
    public String getVarietas() { return varietas; }
     public void setVarietas(String varietas) { this.varietas = varietas; }

    public String getKeterangan() { return keterangan; }
     public void setKeterangan(String keterangan) { this.keterangan = keterangan; }


     public void deskripsi() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Benih: " + getNama() + " | Varietas: " + varietas + " | Harga: Rp" + getHarga() + " | Stok: " + getStok() );
        System.out.println(" | Keterangan: " + keterangan);
         System.out.println("----------------------------------------------------------");
    }
}
