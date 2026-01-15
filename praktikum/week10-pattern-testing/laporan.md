# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [Tuliskan judul topik, misalnya "Class dan Object"]

## Identitas
- Nama  : [Nama Mahasiswa]
- NIM   : [NIM Mahasiswa]
- Kelas : [Kelas]

---

## Tujuan
1. Mahasiswa mampu mengimplementasikan arsitektur Model-View-Controller (MVC) untuk memisahkan logika bisnis, data, dan tampilan.
2. Mahasiswa memahami dan dapat mempraktikkan Unit Testing menggunakan framework JUnit 5.
3. Mahasiswa mampu mengelola dependensi proyek Java menggunakan Maven.
---

## Dasar Teori
1. Model-View-Controller (MVC): Pola desain yang membagi aplikasi menjadi tiga komponen utama: Model (Data), View (Antarmuka), dan Controller (Logika Penghubung).
2. Unit Testing: Proses pengujian bagian terkecil dari kode (seperti sebuah method) untuk memastikan fungsinya berjalan sesuai harapan secara terisolasi.
3. JUnit: Framework testing standar untuk bahasa pemrograman Java yang memudahkan pembuatan dan eksekusi tes otomatis.
4. Maven: Tool manajemen proyek yang digunakan untuk mengelola pustaka (library) eksternal dan proses build aplikasi.)

---

## Langkah Praktikum
(Tuliskan Langkah-langkah dalam prakrikum, contoh:
1. Konfigurasi Maven: Menambahkan dependensi JUnit 5 pada file pom.xml agar fitur pengujian dapat digunakan.
2. Strukturisasi Folder: Membuat package terpisah (model, view, controller, config) untuk menerapkan prinsip Separation of Concerns.
3. Pengembangan Kode: Membuat kelas Product, ConsoleView, dan ProductController sesuai pola MVC.
4. Pembuatan Unit Test: Menyusun file ProductTest.java di folder src/test/java untuk menguji kebenaran atribut produk.
5. Eksekusi Pengujian: Menjalankan perintah mvn test melalui terminal untuk memvalidasi seluruh kode.
6. Eksekusi Aplikasi: Menjalankan kelas AppMVC untuk melihat hasil tampilan program di terminal.

## Kode Program
(Product.java 

```java
package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
```
ConsoleView.java
```java 
package com.upb.agripos.view;

import com.upb.agripos.model.Product;

public class ConsoleView {
    public void displayProductDetails(Product product) {
        System.out.println("\n=== VIEW: TAMPILAN PRODUK ===");
        System.out.println("ID Produk   : " + product.getCode());
        System.out.println("Nama Produk : " + product.getName());
        System.out.println("Harga       : Rp " + product.getPrice());
        System.out.println("=============================");
    }
}

```
ProductController.java
```java 
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private Product model;
    private ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.displayProductDetails(model);
    }
}
```
AppMVC.java
```java 
package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;
import com.upb.agripos.controller.ProductController;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Ade Miko - 240202827 (Week 10)");

        // 1. Inisialisasi Model (Data)
        Product model = new Product("P001", "Benih Jagung Hibrida", 75000);

        // 2. Inisialisasi View (Tampilan)
        ConsoleView view = new ConsoleView();

        // 3. Inisialisasi Controller
        ProductController controller = new ProductController(model, view);

        // 4. Jalankan Tampilan
        controller.updateView();
    }
}
```
ProductTest.java
```java 
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.upb.agripos.model.Product;

public class ProductTest {
    @Test
    public void testProductGetters() {
        Product p = new Product("A01", "Pupuk Cair", 50000);
        assertEquals("A01", p.getCode());
        assertEquals("Pupuk Cair", p.getName());
    }
}
```
)
---

## Hasil Eksekusi
(maven test   
![maven test](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week10-pattern-testing/screenshots/Screenshot%202026-01-15%20141411.png)
)
output 
![test run code](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week10-pattern-testing/screenshots/Screenshot%202026-01-15%20141452.png) 

---

## Analisis
(
- Alur Kerja: Kode berjalan dengan memisahkan peran masing-masing kelas. Controller mengambil data dari Model (Product) lalu memerintahkan View (ConsoleView) untuk menampilkannya. Hal ini membuat kode lebih rapi dibandingkan jika semua logika digabung dalam satu kelas Main.
- Perbedaan: Dibandingkan minggu-minggu sebelumnya (seperti Week 7), minggu ini fokus pada arsitektur dan kualitas kode melalui testing otomatis, bukan hanya sekadar fungsionalitas koleksi data.
- Kendala & Solusi: Terdapat kendala "Missing POM" karena salah direktori terminal dan konflik package dengan Week 7. Solusinya adalah berpindah ke folder praktikum sebelum menjalankan mvn dan merapikan identitas package agar unik untuk setiap minggunya.
)
---

## Kesimpulan
Dengan menerapkan pola MVC, aplikasi menjadi lebih modular dan mudah dikembangkan di masa depan. Penggunaan Unit Testing sangat membantu dalam mendeteksi kesalahan secara dini, sehingga menjamin aplikasi berjalan stabil meskipun dilakukan perubahan kode.
---

## Quiz
( 
1. Mengapa constructor pada Singleton harus bersifat private?

Mencegah Instansiasi Luar: Constructor harus bersifat private untuk memastikan bahwa kelas lain tidak dapat membuat objek baru menggunakan kata kunci new.
Kontrol Akses Tunggal: Dengan membatasi akses, kelas tersebut memiliki kontrol penuh untuk hanya membuat satu instansi saja melalui sebuah static method (biasanya bernama getInstance()). Jika bersifat public, maka prinsip "Singleton" (objek tunggal) akan gagal karena siapa pun bisa membuat objek baru sebanyak mungkin.

2. Jelaskan manfaat pemisahan Model, View, dan Controller. Pemisahan ini dikenal dengan prinsip Separation of Concerns (SoC), manfaatnya meliputi:

Kemudahan Maintenance: Jika ada perubahan pada tampilan (UI), kita hanya perlu mengubah bagian View tanpa menyentuh logika bisnis di Model.
Pengembangan Paralel: Dalam tim besar, satu orang bisa fokus mengerjakan database (Model), sementara yang lain mengerjakan desain antarmuka (View) secara bersamaan.
Reusabilitas Kode: Satu Model yang sama dapat digunakan untuk berbagai jenis View (misalnya tampilan di aplikasi mobile dan tampilan di website).
Kemudahan Testing: Logika aplikasi di Controller menjadi lebih mudah diuji secara mandiri karena tidak terikat langsung dengan elemen tampilan.

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?

Deteksi Dini Bug: Menemukan kesalahan logika saat kode masih dalam tahap pengembangan, sebelum sistem menjadi terlalu kompleks atau sampai ke tangan pengguna.
Dokumentasi Perilaku Kode: Unit test memberikan gambaran nyata tentang bagaimana sebuah method seharusnya bekerja dengan berbagai input tertentu.
Keamanan Saat Refactoring: Saat kita mengubah kode agar lebih efisien, unit test berfungsi sebagai "jaring pengaman". Jika hasil test tetap hijau (SUCCESS), berarti perubahan tersebut tidak merusak fungsi yang sudah ada.
Efisiensi Biaya: Memperbaiki bug di tahap awal jauh lebih murah dan cepat dibandingkan memperbaiki bug yang sudah ditemukan setelah aplikasi dirilis.

4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?

Race Condition (Thread-Safety): Pada aplikasi yang diakses banyak pengguna sekaligus (multithreading), Singleton yang salah bisa menyebabkan terciptanya dua objek secara bersamaan karena dua proses mengaksesnya di detik yang sama.
Kebocoran Memori (Memory Leak): Jika Singleton menyimpan referensi ke objek besar secara statis tanpa pengelolaan yang benar, memori tersebut tidak akan bisa dibersihkan oleh Garbage Collector.
Sulit untuk Diuji (Testing Difficulties): Singleton menciptakan "global state" yang sulit di-reset antar pengujian, sehingga satu unit test bisa memengaruhi hasil unit test lainnya.
Ketergantungan Tersembunyi: Kode yang terlalu sering memanggil Singleton menjadi sulit dipahami keterkaitannya, yang bertentangan dengan prinsip desain kode yang bersih.
