# Laporan Praktikum Minggu 13
Topik: GUI Lanjutan JavaFX (TableView dan Lambda Expression)
## Identitas
- Nama  : Ade miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu menampilkan data secara terstruktur menggunakan komponen TableView di JavaFX.
2. Mahasiswa dapat mengimplementasikan Lambda Expression untuk menyederhanakan penulisan event handler.
3. Mahasiswa mampu menghubungkan GUI dengan database PostgreSQL secara penuh (CRUD) melalui layer Service dan DAO.
4. Mahasiswa dapat merealisasikan alur "Hapus Produk" dan "Lihat Daftar" sesuai dengan desain UML Bab 6.
---

## Dasar Teori
1. TableView: Komponen JavaFX yang digunakan untuk menampilkan koleksi data dalam bentuk tabel (baris dan kolom) yang mendukung sinkronisasi data secara otomatis melalui ObservableList.
2. Lambda Expression: Fitur Java yang memungkinkan penulisan fungsi anonim secara ringkas, sangat efektif digunakan untuk menangani event tombol tanpa perlu membuat inner class yang panjang.
3. ObservableList: Jenis list khusus dalam JavaFX yang memberitahu komponen UI (seperti TableView) setiap kali ada perubahan data (tambah/hapus), sehingga tampilan otomatis diperbarui.
4. Data Persistence: Proses sinkronisasi antara data yang terlihat di layar dengan data yang tersimpan secara permanen di database PostgreSQL menggunakan JDBC.
---

## Langkah Praktikum
1. Refactoring UI: Mengganti komponen ListView atau TextArea dari minggu sebelumnya menjadi TableView<Product>.
2. Konfigurasi Kolom: Menentukan kolom tabel (Code, Name, Price, Stock) dan menghubungkannya dengan atribut yang ada pada model Product.
3. Implementasi Lambda: Mengganti semua anonymous class pada tombol Add dan Delete menggunakan sintaks lambda (e -> { ... }).
4. Fungsi Load Data: Membuat metode loadData() yang memanggil productService.findAll() untuk menarik data terbaru dari database setiap kali aplikasi dimulai atau terjadi perubahan data.
5. Fitur Hapus Produk: Menambahkan logika untuk mendeteksi baris mana yang dipilih pengguna di tabel, lalu menghapusnya dari database menggunakan ID/Kode Produk.

---

## Kode Program
```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agripos", "postgres", "1312");
            ProductDAO dao = new ProductDAOImpl(conn);
            ProductService service = new ProductService(dao);
            ProductTableView view = new ProductTableView();
            new ProductController(service, view);

            stage.setScene(new Scene(view, 500, 600));
            stage.setTitle("Agri-POS Advanced GUI");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) { launch(args); }
}
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week13-gui-lanjutan/screenshots/Screenshot%202026-01-20%20111436.png)
)
---

## Analisis
(
- Mekanisme TableView: Penggunaan TableView jauh lebih efisien dibanding ListView karena data terpetakan langsung ke atribut objek melalui PropertyValueFactory.
- Keunggulan Lambda: Kode menjadi lebih bersih (clean code) dan mudah dibaca. Penanganan event btnAdd dan btnDelete kini hanya membutuhkan beberapa baris kode.
- Traceability Bab 6: Alur penghapusan data telah mengikuti Sequence Diagram di mana View mengirim permintaan ke Controller/Service, lalu Service memerintahkan DAO untuk melakukan DELETE pada database PostgreSQL.
- Kendala: Saat data dihapus di DB, tabel terkadang tidak langsung berubah.
- Solusi: Memastikan fungsi loadData() dipanggil kembali di akhir setiap aksi (tambah/hapus) untuk menjamin sinkronisasi data.
---

## Kesimpulan
(Praktikum minggu ke-13 berhasil mengintegrasikan seluruh komponen Agri-POS. Dengan TableView, aplikasi kini memiliki standar antarmuka yang profesional. Penggunaan Lambda Expression dan Service-DAO pattern memastikan aplikasi tetap mengikuti prinsip SOLID (terutama Dependency Inversion), sehingga aplikasi siap untuk tahap integrasi akhir atau UAS.*)

---

## Quiz
