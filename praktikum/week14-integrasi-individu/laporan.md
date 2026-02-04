# Laporan Praktikum Minggu 14
Topik:Integrasi OOP, Database, dan GUI (Agri-POS)

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu mengintegrasikan seluruh konsep OOP (Bab 1–5) ke dalam satu arsitektur aplikasi yang utuh.
2. Mahasiswa dapat mengimplementasikan rancangan UML dan prinsip SOLID (Bab 6) menjadi kode program yang terstruktur (MVC).
3. Mahasiswa mampu menghubungkan modul backend (DAO + JDBC) dengan antarmuka grafis JavaFX (Event-Driven).
4. Mahasiswa dapat menerapkan validasi data menggunakan Exception Handling serta melakukan pengujian logika melalui Unit Testing.
---

## Dasar Teori 
1. Arsitektur Berlapis (Layered Architecture): Pemisahan tanggung jawab kode menjadi Model (data), DAO (akses DB), Service (logika bisnis), dan View (tampilan).
2. Data Persistence: Kemampuan aplikasi untuk menyimpan data secara permanen ke dalam PostgreSQL menggunakan JDBC sehingga data tidak hilang saat aplikasi ditutup.
3. Java Collections dalam Keranjang: Penggunaan List atau Map untuk mengelola item belanja sementara secara dinamis di dalam memori.
4. Dependency Inversion Principle (DIP): Prinsip yang memastikan komponen tingkat tinggi (View) tidak bergantung pada komponen tingkat rendah (DAO), melainkan pada abstraksi (Service)..)

---

## Langkah Praktikum
1. Integrasi Komponen: Menggabungkan file Product.java, ProductDAO.java, dan ProductService.java ke dalam satu package proyek.
2. Pembuatan Fitur Keranjang: Mengimplementasikan kelas Cart dan CartItem untuk menyimpan daftar belanja pengguna menggunakan ArrayList.
3. Setup JavaFX UI: Membangun PosView yang terdiri dari TableView untuk daftar produk dan panel kontrol untuk transaksi.
4. Implementasi Exception: Membuat blok try-catch untuk menangani error saat input harga, stok, atau saat koneksi database terputus.
5. Running & Testing: Menjalankan aplikasi, melakukan transaksi simulasi, dan memverifikasi logika hitung total menggunakan JUnit.

---

## Kode Program

```java
// Logika utama pada Controller untuk proses Tambah ke Keranjang
public void handleAddToCart() {
    try {
        // Ambil produk yang dipilih dari TableView
        Product selected = tableProduct.getSelectionModel().getSelectedItem();
        if (selected == null) throw new Exception("Pilih produk di tabel terlebih dahulu!");

        int qty = Integer.parseInt(txtQuantity.getText());
        
        // Menambahkan ke keranjang belanja (Collections - Bab 7)
        cartService.addToCart(selected, qty);
        
        // Update Ringkasan UI (Bab 13)
        lblTotalHarga.setText("Total: Rp " + cartService.getTotalAmount());
        
    } catch (NumberFormatException e) {
        showErrorDialog("Input Jumlah harus berupa angka!");
    } catch (Exception e) {
        showErrorDialog(e.getMessage());
    }
}
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week14-integrasi-individu/screenshots/Screenshot%202026-01-20%20120951.png
)
![Screenshot hasil](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week14-integrasi-individu/screenshots/Screenshot%202026-01-20%20121201.png)
---

## Analisis
(
- Alur Kerja Kode: Program memulai dengan memanggil data dari PostgreSQL. TableView berfungsi sebagai viewer data. Ketika tombol ditekan, Lambda Expression memicu Controller untuk memproses data melalui Service. Jika input tidak valid, Custom Exception akan menangkapnya dan menampilkan pesan error tanpa menghentikan program.
- Perbedaan Pendekatan: Berbeda dengan minggu sebelumnya, pendekatan kali ini berfokus pada traceability; setiap aksi di GUI harus selaras dengan Sequence Diagram yang telah dirancang di Bab 6.
- Kendala & Solusi: Kendala muncul saat sinkronisasi stok antara database dan keranjang. Solusinya adalah dengan menambahkan validasi stok di CartService sebelum produk berhasil dimasukkan ke daftar belanja.
---

## Kesimpulan
Dengan menyelesaikan integrasi Bab 14, aplikasi Agri-POS kini telah memiliki standar arsitektur yang profesional. Penggunaan pola Service-DAO memudahkan pengembangan fitur di masa depan, sementara penggunaan JavaFX memberikan pengalaman pengguna yang jauh lebih interaktif dan responsif dibandingkan CLI.

---

## Quiz
