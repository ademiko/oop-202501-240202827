# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [Tuliskan judul topik, misalnya "Class dan Object"]

## Identitas
- Nama  : [Nama Mahasiswa]
- NIM   : [NIM Mahasiswa]
- Kelas : [Kelas]

---

## Tujuan
1. Mahasiswa mampu memahami dan mengimplementasikan konsep event-driven programming menggunakan JavaFX.
2. Mahasiswa dapat membangun antarmuka grafis (GUI) untuk form input data produk Agri-POS.
3. Mahasiswa mampu mengintegrasikan layer GUI dengan modul backend (Service & DAO) sesuai prinsip MVC dan SOLID.
4. Mahasiswa dapat memastikan konsistensi antara desain UML (Bab 6) dengan implementasi kode.
---

## Dasar Teori
1. Event-Driven Programming: Paradigma pemrograman di mana alur program ditentukan oleh peristiwa (events) seperti klik mouse, penekanan tombol, atau input pengguna.
2. JavaFX: Library Java yang digunakan untuk membangun Rich Internet Applications dengan antarmuka grafis yang modern.
3. Arsitektur MVC: Pemisahan aplikasi menjadi tiga komponen utama: Model (data), View (tampilan), dan Controller (logika penghubung).
4. Enkapsulasi & DIP (Dependency Inversion Principle): Menjamin bahwa View tidak mengakses Database/DAO secara langsung, melainkan melalui Service layer untuk menjaga keterkaitan yang longgar (loose coupling).

---

## Langkah Praktikum
1. Setup Project: Memastikan library JavaFX dan driver PostgreSQL sudah terkonfigurasi pada project (menggunakan hasil Pertemuan 11).
2. Pembuatan View: Membuat kelas ProductFormView yang berisi komponen TextField (Kode, Nama, Harga, Stok) dan Button.
3. Implementasi Event Handling: Menambahkan setOnAction pada tombol "Tambah Produk" untuk mengambil data dari input field.
4. Integrasi Service: Memanggil ProductService di dalam controller/handler untuk meneruskan data ke ProductDAO.
5. Update UI: Menambahkan logika untuk menampilkan produk yang baru diinput ke dalam ListView.
6. Running & Testing: Menjalankan aplikasi dan melakukan pengujian input data untuk memastikan data tersimpan di DB dan muncul di UI.

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Inisialisasi Database (JDBC)
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", "postgres", "1312"
            );
            
            // 2. Inisialisasi Layer (DAO -> Service -> View -> Controller)
            ProductDAO dao = new ProductDAOImpl(conn);
            ProductService service = new ProductService(dao);
            ProductFormView view = new ProductFormView();
            
            // Menghubungkan semuanya melalui Controller
            new ProductController(service, view);

            // 3. Menampilkan Stage
            Scene scene = new Scene(view, 450, 550);
            primaryStage.setTitle("Agri-POS Sistem Manajemen Produk");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Gagal memulai aplikasi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
[![Screenshot hasil](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week12-gui-dasar/screenshots/Screenshot%202026-01-20%20102808.png) )
)
---

## Analisis
(
-Analisis
- Mekanisme Kode: Kode berjalan dengan mendengarkan action event pada tombol. Saat tombol diklik, program melakukan validasi tipe data sederhana, mengirimkan objek ke ProductService, yang kemudian diteruskan ke ProductDAO untuk dieksekusi ke PostgreSQL menggunakan JDBC.
- Perbedaan dengan Minggu Sebelumnya: Jika minggu lalu interaksi dilakukan melalui terminal (CLI), minggu ini interaksi menggunakan GUI yang lebih user-friendly. Logika bisnis tetap sama karena menggunakan kembali (reuse) kelas Service dan DAO yang sudah ada.
- Kendala: Kesulitan dalam sinkronisasi antara Thread GUI dan proses database.
- Solusi: Memastikan koneksi database sudah terbuka sebelum aplikasi JavaFX di-launch.
---

## Kesimpulan
(Dengan implementasi JavaFX, aplikasi Agri-POS kini memiliki antarmuka yang lebih profesional. Penggunaan arsitektur berlapis (MVC) memudahkan proses pengembangan karena logika database terpisah dari logika tampilan, sesuai dengan prinsip SOLID yang telah dirancang pada Bab 6.)

---

## Quiz
-
