# Laporan Praktikum Minggu 15
Topik: Proyek Kelompok (Desain Sistem + Implementasi Terintegrasi + Testing + Dokumentasi)
## Identitas
- Nama  : [Nama Mahasiswa]
- NIM   : [NIM Mahasiswa]
- Kelas : [Kelas]

---

## Tujuan
Tujuan dari proyek akhir ini adalah:
1. Mahasiswa mampu berkolaborasi dalam tim menggunakan Git untuk membangun aplikasi terintegrasi.
2. Mahasiswa mampu mendesain sistem lengkap menggunakan UML (Use Case, Class, Sequence Diagram).
3. Mahasiswa dapat mengimplementasikan arsitektur aplikasi yang rapi (Layered Architecture) menerapkan prinsip SOLID dan DIP.
4. Mahasiswa mampu melakukan pengujian (Unit Testing & Manual Testing) serta menyusun dokumentasi teknis yang lengkap.
---

## Dasar Teori
1. Layered Architecture (MVC Variation): Aplikasi dibagi menjadi layer terpisah: View (JavaFX) untuk antarmuka, Controller untuk jembatan logika UI, Service untuk logika bisnis (business logic), dan DAO (Data Access Object) untuk akses database. Hal ini menjaga prinsip Separation of Concerns.
2. SOLID & Dependency Inversion Principle (DIP): Modul tingkat tinggi (Service) tidak boleh bergantung pada modul tingkat rendah (Implementasi DAO) secara langsung, melainkan melalui abstraksi (Interface). Ini memudahkan maintenance dan testing.
3. JDBC & PreparedStatement: Koneksi Java ke PostgreSQL dilakukan menggunakan JDBC. Penggunaan PreparedStatement wajib untuk mencegah SQL Injection dan meningkatkan performa query.
4. Design Pattern:
- Strategy Pattern: Digunakan pada metode pembayaran (Tunai/E-Wallet) agar sistem mematuhi Open/Closed Principle (mudah ditambah tanpa mengubah - kode inti).
- Singleton: Digunakan pada koneksi database untuk memastikan hanya ada satu instance koneksi yang berjalan.
5. Unit Testing: Pengujian level kode (menggunakan JUnit) untuk memastikan logika bisnis (Service/Model) berjalan benar sebelum diintegrasikan ke UI.

---

## Langkah Praktikum
1. Perancangan Sistem:
- Membuat Requirement (FR-1 s.d. FR-5) dan Optional FR (misal: Diskon).
- Merancang Database (ERD) dan skema PostgreSQL.
- Membuat desain UML (Use Case, Class Diagram, dan Sequence Diagram).
2. Setup Proyek & Kolaborasi:
- Inisialisasi repositori Git dan pembagian branch fitur per anggota.
- Setup struktur direktori Maven/Gradle sesuai standar com.upb.agripos.
3. Implementasi Backend (DAO & Service):
- Membuat Interface DAO dan implementasinya (JDBC).
- Membuat Service class untuk logika bisnis (validasi stok, hitung total).
4. Implementasi Frontend (JavaFX):
- Membuat FXML dan Controller.
- Menghubungkan Controller dengan Service.
5. Testing & Integrasi:
- Membuat Unit Test untuk ProductService dan CartService.
- Melakukan Manual Testing sesuai Test Case.
---

## Analisis
(
Implementasi kode pada proyek Agri-POS ini dibangun di atas arsitektur layered yang secara tegas memisahkan tanggung jawab antara antarmuka, logika bisnis, dan akses data. Alur eksekusi program dimulai dari interaksi pengguna pada antarmuka JavaFX yang ditangkap oleh Controller. Berbeda dengan pendekatan prosedural, Controller di sini tidak memproses data secara langsung, melainkan mendelegasikan tugas tersebut ke layer Service. Di layer Service inilah validasi logika bisnis—seperti pengecekan ketersediaan stok sebelum transaksi atau penerapan algoritma diskon—dieksekusi. Setelah lolos validasi, data diteruskan ke layer Data Access Object (DAO) yang bertugas melakukan komunikasi low-level dengan database PostgreSQL menggunakan JDBC dan PreparedStatement. Struktur ini memastikan bahwa perubahan pada satu layer (misalnya mengganti database) tidak akan merusak layer lainnya.

Perbedaan fundamental pada praktikum minggu ini dibandingkan minggu-minggu sebelumnya terletak pada penerapan standar Software Engineering yang lebih ketat, khususnya prinsip SOLID dan Dependency Inversion Principle (DIP). Jika pada modul sebelumnya fokus utama hanya pada fungsionalitas CRUD agar "asal jalan", pada proyek akhir ini kami mewajibkan penggunaan Interface sebagai kontrak antara layer Service dan DAO, serta penerapan Design Pattern seperti Strategy Pattern untuk metode pembayaran. Selain itu, aspek pengujian (testing) menjadi prioritas baru; kami tidak hanya mengandalkan uji coba manual lewat GUI, tetapi juga mengimplementasikan Unit Testing menggunakan JUnit untuk memverifikasi logika bisnis secara otomatis sebelum kode digabungkan.

Selama proses pengembangan tim, kendala teknis terbesar yang kami hadapi adalah konflik penggabungan kode (merge conflict) di Git, terutama saat beberapa anggota menyunting file FXML atau Controller utama secara bersamaan. Untuk mengatasinya, kami mengubah strategi kerja dengan memecah fitur menjadi branch yang lebih spesifik dan melakukan sinkronisasi (pull/merge) lebih sering untuk meminimalisir tumpukan konflik. Tantangan lain muncul pada penanganan error; pesan kesalahan asli (stack trace) dari SQL terlalu teknis untuk ditampilkan kepada pengguna. Solusi yang kami terapkan adalah membungkus SQLException ke dalam Custom Domain Exception di layer Service, sehingga pesan yang sampai ke antarmuka pengguna menjadi lebih informatif, ramah, dan aman tanpa mengekspos struktur internal database.  
)
---

## Kesimpulan
(Proyek Agri-POS berhasil mengintegrasikan modul-modul terpisah menjadi satu sistem utuh. Dengan menggunakan prinsip SOLID dan arsitektur layer, aplikasi menjadi lebih modular dan mudah diuji (testable). Penggunaan JavaFX untuk UI dan PostgreSQL untuk database telah memenuhi standar aplikasi desktop modern berskala kecil-menengah. Kerja sama tim melalui Git juga berjalan efektif meski terdapat beberapa konflik yang dapat diselesaikan..*)

---
