package com.upb.agripos.Model;

public class AlatPertanian extends Produk{
    private  String material;
    private  String keterangan;

    public AlatPertanian(String kode, String nama, int harga, int stok, String material, String keterangan){
        super(kode, nama, harga, stok);
        this.material = material;
        this.keterangan = keterangan;

    }
    
    public String getMaterial() { return material; }
    public void setMateral(String material) { this.material = material; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String ketrangan, String keterangan) { this.keterangan = keterangan; }

     public void deskripsi() {
        System.out.println("----------------------------------------------------------");
        System.out.println("alat: " + getNama() + " | material: " + getMaterial() +"| Harga: Rp" + getHarga() + " | Stok: " + getStok() );
        System.out.println(" | Keterangan: " + keterangan);
         System.out.println("----------------------------------------------------------");
    }
}
