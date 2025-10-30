package com.upb.agripos.Model;

public class Pupuk extends Produk {
    private String jenis;
    private String keterangan;

    public Pupuk(String kode, String nama, int harga, int stok, String jenis, String keterangan) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
        this.keterangan = keterangan;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public void deskripsi() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Pupuk: " + getNama() + " | jenis " + jenis + " | Harga: Rp" + getHarga() + " | Stok: " + getStok() );
        System.out.println(" | Keterangan: " + keterangan);
         System.out.println("----------------------------------------------------------");
    }

    @Override
    public String getInfo() {
    return "Pupuk - " + super.getInfo() +
           ", Jenis: " + jenis ;
}

}
