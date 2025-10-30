# Laporan Praktikum Minggu 4 
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : ade miko
- NIM   : 2420202827
- Kelas : 3IKRA

---

## Tujuan
1. Mengimplementasikan method overloading pada class Produk.
2. Mengimplementasikan method overriding pada subclass Benih, Pupuk, dan AlatPertanian.
3. Menunjukkan proses dynamic binding menggunakan array Produk[] yang berisi berbagai objek subclass.
4. Menampilkan hasil perbedaan keluaran dari tiap objek melalui method getInfo().

---

## Dasar Teori

1. Polymorphism memungkinkan objek berbeda merespons method yang sama dengan cara berbeda.
2. Overloading digunakan untuk membuat beberapa method dengan nama sama namun parameter berbeda.
3. Overriding digunakan agar subclass dapat mengganti perilaku method dari superclass.
4. Dynamic Binding membuat Java memilih method yang sesuai saat runtime berdasarkan objek sebenarnya.
5. Konsep ini membantu program menjadi lebih fleksibel, efisien, dan mudah dikembangkan.

---

## Langkah Praktikum

1. Membuat class Produk dan menambahkan dua method tambahStok() (overloading).
2. Menambahkan method getInfo() pada Produk dan melakukan override di subclass Benih, Pupuk, dan AlatPertanian.
3. Membuat class MainPolymorphism yang mendemonstrasikan dynamic binding melalui array Produk[].
4. Menjalankan program untuk menampilkan informasi produk sesuai jenis objeknya.
---

## Kode Program

prouk.java 
```java
package com.upb.agripos.Model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

     public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + "), Harga: " + harga + ", Stok: " + stok;
    }
}
```
alatpertanian.java
```java
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

    @Override
    public String getInfo() {
        return "Alat Pertanian - " + super.getInfo() +
           ", Material: " + material ;
       
           
        }
  

    }

```
benih.java
```java
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


    @Override
    public String getInfo() {
    return "Benih - " + super.getInfo() +
           ", Varietas: " + varietas ;
}

   

}

```
obathama.java
```java
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
```
pupuk.java
```java
package com.upb.agripos.Model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

     public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + "), Harga: " + harga + ", Stok: " + stok;
    }
}
```
creditBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {

    
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```
mainplymorphism.java
```java
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

```
---

## Hasil Eksekusi

![Screenshot hasil](/praktikum/week4-polymorphism/screenshots/week%204.png)

---

## Analisis

- tambahStok() menunjukkan konsep overloading, karena terdapat dua versi method dengan parameter berbeda.
- getInfo() pada masing-masing subclass menunjukkan overriding, di mana setiap subclass memberikan detail tambahan sesuai jenis produk.
- for (Produk p : daftarProduk) menunjukkan dynamic binding, di mana method yang dipanggil tergantung tipe objek aktual saat runtime.
- Program berjalan sesuai tujuan: menampilkan informasi berbeda meski dipanggil dengan tipe referensi yang sama (Produk).
---

## Kesimpulan
(Praktikum ini berhasil menunjukkan penerapan polymorphism melalui overloading, overriding, dan dynamic binding.
Setiap subclass (Benih, Pupuk, AlatPertanian) mampu menampilkan informasi yang berbeda tanpa perlu mengubah struktur class Produk.
Dengan konsep ini, sistem Agri-POS menjadi lebih fleksibel, efisien, dan mudah diperluas untuk jenis produk baru.
)
---

## Quiz
(1. Apa perbedaan overloading dan overriding?
Jawaban: Overloading adalah method dengan nama sama namun parameter berbeda; overriding adalah mengganti implementasi method dari superclass pada subclass.

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?
Jawaban: Java menentukan method berdasarkan tipe objek aktual saat runtime, bukan berdasarkan tipe referensi variabelnya.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.
Jawaban: Dalam POS toko elektronik, class Barang bisa dioverride oleh subclass Laptop, Smartphone, dan Aksesoris, masing-masing menampilkan informasi khusus seperti spesifikasi atau kapasitas baterai.)
