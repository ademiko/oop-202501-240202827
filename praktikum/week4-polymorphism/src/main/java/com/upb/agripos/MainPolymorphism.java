package com.upb.agripos;

import com.upb.agripos.Model.AlatPertanian;
import com.upb.agripos.Model.Produk;
import com.upb.agripos.Model.Pupuk;
import com.upb.agripos.Model.ObatHama;
import com.upb.agripos.Model.Benih;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
      public static void main(String[] args) {
        Produk[] daftarProduk = {
            new Benih("BNH-OO1", "Benih Padi IRD64", 25000, 100, "IR64","benih pilihan dan dijamin tumbuh"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea","pupuk dengan kualitas super A+++"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja", "bukan sekedar cangkul biasa tapi luar biasa"),
            new ObatHama("OBT-301", "Obat Hama Serangga", 50000, 20, "Serangga")
        };
// dynamic binding untuk menampilkan info produk
        for (Produk p : daftarProduk) {
        System.out.println(p.getInfo());
        }
        // Menampilkan identitas mahasiswa
        CreditBy.print("240202827", "Ade Miko");
    
}
}
