# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Collections dan Implementasi Keranjang Belanja

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa memahami perbedaan antara struktur data List, Map, dan Set dalam Java.
2. Mahasiswa mampu mengimplementasikan ArrayList untuk menyimpan objek secara dinamis.
3. Mahasiswa mampu menggunakan HashMap untuk mengelola data dengan pasangan key-value (produk dan kuantitas).
4. Mahasiswa mampu menerapkan HashSet untuk menjaga keunikan data pelanggan.
---

## Dasar Teori

1. Collections Framework: Kumpulan antarmuka (interface) dan kelas untuk menyimpan serta memanipulasi grup objek secara dinamis.
2. ArrayList (List): Struktur data yang menyimpan elemen secara berurutan dan memperbolehkan adanya duplikasi data.
3. HashMap (Map): Struktur data yang menyimpan pasangan kunci (key) dan nilai (value). Sangat efisien untuk memetakan produk ke jumlah stok/kuantitas.
4. HashSet (Set): Struktur data yang menjamin tidak ada elemen duplikat di dalamnya; sangat berguna untuk validasi data unik.
---

## Langkah Praktikum
1. Membuat kelas Product sebagai model data utama dengan encapsulation.
2. Mengimplementasikan logika keranjang belanja pada kelas ShoppingCart (ArrayList) dan ShoppingCartMap (HashMap).
3. Membuat fitur tambahan: pencarian kode barang, perhitungan pajak PPN 11%, dan fitur pengosongan keranjang.
4. Membuat kelas CustomerList menggunakan HashSet untuk mengelola daftar pelanggan unik.
5. Menjalankan program melalui kelas MainCart untuk memverifikasi semua fitur berjalan dengan benar.
---

## Kode Program
(code utama : ShoppingCartMap, dan MainCart)

```java
package com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
    }

    // Latihan 2: Cari Produk berdasarkan Kode
    public Product findProductByCode(String code) {
        for (Product p : items) {
            if (p.getCode().equalsIgnoreCase(code)) {
                return p;
            }
        }
        return null;
    }

    // Latihan 3: Kosongkan Keranjang
    public void emptyCart() {
        items.clear();
        System.out.println("Keranjang ArrayList telah dikosongkan.");
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (ArrayList): " + items.size() + " item.");
    }
}

```
```java 
package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Nama : Ade Miko | NIM : 2420202827");
        System.out.println("====================================");

        Product p1 = new Product("P01", "Benih Padi", 50000);
        Product p2 = new Product("P02", "Pupuk Cair", 30000);

        // Test ArrayList & Search
        ShoppingCart listCart = new ShoppingCart();
        listCart.addProduct(p1);
        Product search = listCart.findProductByCode("P01");
        System.out.println("Hasil Cari P01: " + (search != null ? search.getName() : "Tidak ada"));

        // Test Map & Tax
        ShoppingCartMap mapCart = new ShoppingCartMap();
        mapCart.addProduct(p1);
        mapCart.addProduct(p1);
        mapCart.addProduct(p2);
        mapCart.printCart();
        System.out.println("Total + Pajak 11%: Rp" + mapCart.getTotalWithTax(0.11));

        // Test Set (Customer)
        CustomerList cList = new CustomerList();
        cList.addCustomer("Ade Miko");
        cList.addCustomer("Ade Miko"); // Akan ditolak
        cList.printAll();

        // Test Clear
        mapCart.emptyCart();
        System.out.println("Setelah dikosongkan, Total: Rp" + mapCart.getTotal());
    }
}
```

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week7-koleksi-keranjang/screenshots/Screenshot%202026-01-09%20211024.png)
)
---

## Analisis
(
- Cara Kerja Kode: Program bekerja dengan menyimpan objek Product ke dalam koleksi. Pada ArrayList, setiap item disimpan sebagai entitas terpisah. Namun pada HashMap, produk digunakan sebagai key dan integer digunakan sebagai value untuk menyimpan jumlah (quantity), sehingga data lebih terorganisir.
- Perbedaan Pendekatan: Berbeda dengan minggu lalu yang fokus pada Inheritance dan Interface, minggu ini fokus pada cara menyimpan dan mengelola banyak objek sekaligus secara efisien di memori.
- Kendala: Kendala yang dihadapi adalah memastikan objek produk yang sama tidak dianggap berbeda oleh HashMap. Solusinya adalah melakukan override pada method equals() dan hashCode() di kelas Product.
---

## Kesimpulan
Dengan menggunakan Java Collections, pengelolaan data dalam sistem Agri-POS menjadi lebih dinamis dan kuat. Penggunaan Map mempermudah pengelolaan kuantitas belanja, sementara Set sangat efektif untuk memastikan integritas data pelanggan agar tidak terjadi duplikasi.
---

## Quiz
(
1. Jelaskan perbedaan mendasar antara List, Map, dan Set.
List menyimpan data secara berurutan dan boleh duplikat. Map menyimpan pasangan kunci-nilai (key-value) dengan kunci yang unik. Set menyimpan data unik tanpa urutan tertentu.
2. Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?
Karena ArrayList mudah digunakan untuk menambah item baru di akhir daftar dan mendukung akses data berdasarkan urutan masuk (index).
3. Bagaimana struktur Set mencegah duplikasi data?
Set (seperti HashSet) menggunakan mekanisme hashing. Saat data dimasukkan, sistem mengecek nilai hash dan method equals(). Jika data sudah ada, data baru tidak akan ditambahkan.
4. Kapan sebaiknya menggunakan Map dibandingkan List?.
Sebaiknya menggunakan Map saat kita perlu memetakan satu data ke data lain (seperti produk ke jumlahnya) atau saat membutuhkan pencarian cepat berdasarkan kunci unik tanpa melakukan perulangan pada seluruh data.
 )
