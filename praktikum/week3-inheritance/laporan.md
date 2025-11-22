# Laporan Praktikum Minggu 3 (Inheritance)
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
- Mahasiswa memahami konsep inheritance (pewarisan class) dalam OOP.
- Mahasiswa mampu membuat superclass dan subclass untuk produk pertanian.
- Mahasiswa dapat menambahkan atribut khusus di subclass dan menampilkan data produk melalui inheritance.
---

## Dasar Teori

1. Inheritance memungkinkan subclass mewarisi atribut dan method dari superclass, sehingga kode lebih reusable.
2. Superclass adalah class induk yang mendefinisikan atribut/method umum, misal Produk.
3. Subclass adalah class turunan yang mewarisi atribut/method dan dapat menambahkan atribut/method baru, misal Benih, Pupuk, AlatPertanian.
4. super digunakan untuk memanggil konstruktor atau method dari superclass.
Inheritance membuat kode lebih terstruktur dan mudah dikembangkan dibanding membuat class terpisah tanpa hubungan.
---

## Langkah Praktikum
1. Membuat class Produk.java sebagai superclass dengan atribut kode, nama, harga, stok dan method getter/setter.
Membuat subclass:
 - Benih.java → atribut tambahan varietas.
 - Pupuk.java → atribut tambahan jenis.
 - AlatPertanian.java → atribut tambahan material dan keterangan.

2. Membuat MainInheritance.java untuk instansiasi objek dari masing-masing subclass dan menampilkan data produk.
3. Menambahkan CreditBy.print() untuk menampilkan identitas mahasiswa.
4. Commit kode dengan pesan: week3-inheritance.
---

## Kode Program
 
   untuk produk.java dan creditBy.java, masih sama dengan week 2
```java
// Benih.java
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

```
```java
// Pupuk.java

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
}
```
```java
//Alat pertanian.java

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
```
```java
//MainInheritance.java

package com.upb.agripos;

import com.upb.agripos.Model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // Membuat objek dari setiap subclass
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64", " benih pilihan dan dijamin tumbuih");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea","pupuk dengan kualitas super A+++");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja" ,"bukan sekedar cangkul biasa tapi luar biasa");

       b.deskripsi();
        p.deskripsi();
        a.deskripsi();

        // Menampilkan identitas mahasiswa
        CreditBy.print("240202827", "Ade Miko");
    }
}
```
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week3-inheritance/screenshots/image.png)
)
---

## Analisis
- Kode berjalan dengan benar menampilkan semua produk dari subclass masing-masing.
- Pendekatan inheritance membuat kode lebih terstruktur dibanding minggu sebelumnya yang hanya menggunakan class tunggal Produk.
- Kendala awal muncul saat memanggil constructor AlatPertanian karena jumlah parameter tidak sesuai, diperbaiki dengan memisahkan material dan keterangan menggunakan koma. 
---

## Kesimpulan

Dengan menggunakan konsep inheritance, class Produk dapat dimanfaatkan kembali oleh berbagai subclass tanpa perlu menduplikasi kode yang sama, sehingga lebih efisien. Subclass seperti Benih, Pupuk, dan AlatPertanian mampu menambahkan atribut khusus sesuai kebutuhan masing-masing, seperti varietas untuk benih, jenis untuk pupuk, dan material serta keterangan untuk alat pertanian. Pendekatan ini membuat program menjadi lebih terstruktur, mudah dikembangkan, dan lebih fleksibel untuk penambahan fitur atau kategori produk di masa mendatang.


## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?
Jawaban: Kode lebih reusable, terstruktur, dan mudah dikembangkan.

2. Bagaimana cara subclass memanggil konstruktor superclass?
Jawaban: Dengan menggunakan keyword super di constructor subclass.

3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.
Jawaban: Bibit Tanaman, Herbisida, Traktor Mini.
