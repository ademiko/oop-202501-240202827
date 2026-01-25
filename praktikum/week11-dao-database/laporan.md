# Laporan Praktikum Minggu 11
Topik: Data Access Object (DAO) dan CRUD Database dengan JDBC

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Menjelaskan dan menerapkan konsep Data Access Object (DAO) dalam pengembangan aplikasi berbasis Object-Oriented Programming (OOP).
2. Membangun koneksi antara aplikasi Java dengan basis data relasional menggunakan Java Database Connectivity (JDBC).
3. Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) secara menyeluruh pada sistem basis data.
4. Mengintegrasikan pola desain DAO dengan arsitektur aplikasi OOP guna mencapai low coupling dan high cohesion.
---

## Dasar Teori
1. DAO (Data Access Object): Pola desain yang berfungsi sebagai perantara antara logika bisnis aplikasi dengan mekanisme penyimpanan data (database) guna mengurangi ketergantungan antar komponen (low coupling).
2. JDBC (Java Database Connectivity): API standar Java yang menyediakan metode untuk menghubungkan aplikasi dengan database relasional melalui komponen DriverManager, Connection, dan Statement.
3. PreparedStatement: Komponen JDBC yang digunakan untuk mengeksekusi kueri SQL berparameter secara efisien dan aman dari risiko keamanan seperti SQL Injection.
4. PostgreSQL: Sistem manajemen basis data relasional (RDBMS) yang digunakan sebagai media penyimpanan permanen data produk dalam praktikum ini.
---

## Langkah Praktikum
1. Persiapan Database: Membuat database bernama agripos dan tabel products melalui query tool pada pgAdmin atau terminal PostgreSQL.
2. Konfigurasi Project: Menyiapkan struktur direktori yang memisahkan folder model untuk entitas, dao untuk akses data, dan MainDAOTest untuk pengujian.
3. Implementasi Kelas:
   - Membuat class Product sebagai objek model data.
   - Membuat interface ProductDAO untuk mendefinisikan kontrak fungsi CRUD.
   - Mengimplementasikan interface tersebut ke dalam class ProductDAOImpl menggunakan logika JDBC.
4. Pengujian: Membuat kelas MainDAOTest untuk melakukan inisialisasi koneksi database dan memanggil metode DAO guna memastikan operasi CRUD berjalan benar.
---

## Kode Program
1. Implementasi CRUD (ProductDAOImpl.java)
```Java
// Bagian utama dari implementasi DAO menggunakan JDBC
@Override
public void insert(Product p) throws Exception {
    String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, p.getCode());
        ps.setString(2, p.getName());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getStock());
        ps.executeUpdate(); // Menjalankan perintah Insert
    }
}

@Override
public void update(Product p) throws Exception {
    String sql = "UPDATE products SET name=?, price=?, stock=? WHERE code=?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, p.getName());
        ps.setDouble(2, p.getPrice());
        ps.setInt(3, p.getStock());
        ps.setString(4, p.getCode());
        ps.executeUpdate(); // Menjalankan perintah Update
    }
}
```
2. Integrasi dan Pengujian (MainDAOTest.java)

```Java
public static void main(String[] args) throws Exception {
    Connection conn = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/agripos", "postgres", "1234"
    );

    ProductDAO dao = new ProductDAOImpl(conn);

    // Menambah produk baru (Create)
    dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));

    // Mengubah nama produk (Update)
    dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));

    // Menghapus produk (Delete)
    dao.delete("P01");

    conn.close();
}
```
HASIL 
(maven test   
![maven test](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week10-pattern-testing/screenshots/Screenshot%202026-01-15%20141411.png)
)

## Analisis
(
- Alur Kode: Kode menggunakan DriverManager untuk membuka gerbang koneksi ke database agripos di PostgreSQL. Objek ProductDAOImpl kemudian menggunakan perintah SQL terenkapsulasi sehingga di metode main() kita hanya perlu memanggil fungsi sederhana seperti .insert() atau .update().
- Perbedaan: Berbeda dengan minggu sebelumnya yang hanya menyimpan data di memori (RAM), minggu ini data benar-benar tersimpan di dalam tabel PostgreSQL secara permanen.
- Kendala: Masalah yang sering dihadapi adalah pengaturan password database yang salah di DriverManager. Solusinya adalah memastikan password di kode ("1234") sesuai dengan password saat instalasi PostgreSQL.
---

## Kesimpulan
(Implementasi pola DAO pada praktikum ini berhasil memisahkan logika database dari aplikasi utama, membuat program lebih terstruktur, dan memastikan data produk tersimpan dengan aman di PostgreSQL menggunakan JDBC.*)

---

## Quiz
