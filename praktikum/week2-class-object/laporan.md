# Laporan Praktikum Minggu 2 (class dan object)
Topik: [Praktikum 2: Class dan Object ]

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
- Memahami dan menjelaskan konsep dasar OOP, yaitu Class, Object, Atribut, dan Method. 
- Menerapkan prinsip enkapsulasi dengan menggunakan access modifier private serta method getter dan setter untuk melindungi data.
- Mengimplementasikan Class Produk secara konkret untuk merepresentasikan data produk pertanian.
- Mendemonstrasikan pembuatan object (instansiasi) dari Class Produk dan menampilkan datanya di console. 
---

## Dasar Teori
1. Class adalah sebuah blueprint atau cetak biru yang mendefinisikan atribut (data) dan method (perilaku) dari sebuah objek.
2. Object adalah instansiasi atau wujud nyata dari sebuah class. Satu class dapat digunakan untuk membuat banyak object.
3. Enkapsulasi adalah mekanisme untuk menyembunyikan detail implementasi dari sebuah objek dan hanya memperlihatkan fungsionalitas yang diperlukan. Ini dicapai dengan membuat atribut bersifat private dan menyediakan akses melalui method public (getter dan setter).

---

## Langkah Praktikum
1. Persiapan & Penulisan Kode: Membuat struktur direktori sesuai panduan dan membuat tiga file Java:
   - Produk.java di dalam package com.upb.agripos.model.
   - CreditBy.java di dalam package com.upb.agripos.util.
   - MainProduk.java di dalam package com.upb.agripos.
2. Eksekusi Program: Menjalankan file MainProduk.java sebagai program utama untuk melihat output dari instansiasi tiga objek produk dan informasi kredit.
3. Dokumentasi: Mengambil screenshot dari hasil eksekusi program dan menyimpannya sebagai hasil.png di dalam folder screenshots.
4. Version Control: Melakukan commit ke repositori Git dengan pesan commit: week2-class-object.
---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

produk.java
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

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
}
```
CreditBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {

    
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```
MainProduk.java
```java
package com.upb.agripos;

import com.upb.agripos.Model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

         System.out.println("Menambah stok Benih Padi IR64 sebanyak 25");
        p1.tambahStok(25);
        System.out.println("Stok baru: " + p1.getStok());

        System.out.println("Mengurangi stok Pupuk Urea sebanyak 18");
        p2.kurangiStok(18);
        System.out.println("Stok baru: " + p2.getStok());

        System.out.println("mengurangi stok cangkul 20");
        p3.kurangiStok(20);
        System.out.println("Stok baru: " + p2.getStok());        



        // Tampilkan identitas mahasiswa
        CreditBy.print("240202827", "Ade Miko");
    }
}
```
---

## Hasil Eksekusi
(
![Screenshot hasil](/praktikum/week2-class-object/screenshots/Screenshot%202025-10-08%20143729.png)
)
---

## Analisis
- Cara Kerja Kode: Program utama (MainProduk.java) membuat tiga object (p1, p2, p3) berdasarkan class Produk. Setiap object diinisialisasi dengan data yang berbeda melalui constructor. Kemudian, program memanggil method getter (contoh: p1.getNama()) pada setiap object untuk mengambil nilainya dan menampilkannya ke console.
- Perbedaan dengan Minggu Sebelumnya: Praktikum minggu ini memperkenalkan paradigma baru, yaitu Object-Oriented Programming (OOP). Jika sebelumnya program mungkin bersifat prosedural (langkah demi langkah dalam satu alur), sekarang program diorganisir ke dalam class dan object yang saling berinteraksi. Ini adalah perubahan fundamental dalam cara menstrukturkan kode.
- Kendala:Saat pertama kali membuat file, saya meletakkan Produk.java tidak di dalam sub-folder model, melainkan sejajar dengan MainProduk.java. Meskipun kode package com.upb.agripos.model; sudah benar tertulis di dalam file, struktur folder fisik yang salah menyebabkan error.
---

## Kesimpulan
Dengan menggunakan class dan object, program untuk mengelola data produk pertanian menjadi lebih terstruktur, mudah dibaca, dan mudah dikembangkan di kemudian hari. Konsep enkapsulasi juga berhasil diterapkan untuk melindungi data produk agar tetap valid.
---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?  
   **Jawaban:** Untuk enkapsulasi (pembungkusan data). Ini mencegah data d

2. Apa fungsi getter dan setter dalam enkapsulasi?  
   **Jawaban:** 
   - Getter (Akses Baca) : Fungsinya untuk mengambil atau membaca nilai dari sebuah atribut private. Method ini memungkinkan bagian lain dari program untuk mengetahui nilai suatu data tanpa bisa mengubahnya. Contoh: getNama().

   - Setter (Akses Tulis) : Fungsinya untuk mengubah atau menetapkan nilai baru ke sebuah atribut private. Di sinilah kita bisa menempatkan logika validasi untuk memastikan nilai yang masuk akal sebelum atribut diubah. Contoh: setHarga(25000).


3. Bagaimana cara class `Produk` mendukung pengembangan aplikasi POS yang lebih kompleks?  
   **Jawaban:** Class Produk berfungsi sebagai cetakan (blueprint) yang bisa dipakai ulang (reusable) untuk membuat banyak objek produk. Ini membuat kode lebih terstruktur dan mudah dikembangkan (scalable) saat ada penambahan fitur baru.
