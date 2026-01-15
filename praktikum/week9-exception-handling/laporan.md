# Laporan Praktikum Minggu 9
Topik: Exception Handling, Custom Exception, dan Penerapan Design Pattern

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu mengimplementasikan mekanisme penanganan kesalahan menggunakan try-catch-finally.
2. Mahasiswa mampu membuat dan menggunakan Custom Exception untuk validasi logika bisnis Agri-POS.
3. Mahasiswa mampu menerapkan Singleton Design Pattern untuk efisiensi manajemen layanan.
4. Mahasiswa mampu memastikan integritas data (stok gudang) melalui validasi pengecualian.
---

## Dasar Teori
1. Exception Handling: Mekanisme untuk menangani kondisi tidak normal (runtime error) agar program tidak berhenti secara mendadak.
2. Custom Exception: Kelas pengecualian yang dibuat spesifik untuk kebutuhan aplikasi (contoh: InsufficientStockException).
3. Try-Catch-Finally: Blok kode untuk mencoba proses berisiko (try), menangkap kesalahan (catch), dan menjalankan pembersihan sistem (finally).
4. Singleton Pattern: Pola desain yang memastikan sebuah kelas hanya memiliki satu instansi global untuk menghemat penggunaan memori.
---

## Langkah Praktikum
1. Coding Model: Memperbarui kelas Product agar memiliki atribut stock untuk mendukung pengecekan ketersediaan barang.
2. Coding Exception: Membuat tiga kelas Custom Exception: InvalidQuantityException, ProductNotFoundException, dan InsufficientStockException.
3. Coding Logic: Menerapkan kata kunci throws dan throw pada metode di kelas ShoppingCart untuk melakukan validasi.
4. Singleton Implementation: Membuat ProductService sebagai pusat pencatatan log aktivitas sistem.
5. Execution: Menjalankan skenario "Happy Flow" di MainExceptionDemo di mana semua input data valid (jumlah positif dan stok mencukupi).
---

## Kode Program

```java
File: InvalidQuantityException.java


package com.upb.agripos;
public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
File: ProductNotFoundException.java

package com.upb.agripos;
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}
File: InsufficientStockException.java

package com.upb.agripos;
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}
```
```java 
2. File Model & Service (Logika Pendukung)
File: Product.java


package com.upb.agripos;
public class Product {
    private final String code, name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code; this.name = name; this.price = price; this.stock = stock;
    }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public void reduceStock(int qty) { this.stock -= qty; }
}
File: ProductService.java (Singleton Pattern)


package com.upb.agripos;
public class ProductService {
    private static ProductService instance;
    private ProductService() {}
    public static ProductService getInstance() {
        if (instance == null) instance = new ProductService();
        return instance;
    }
    public void log(String msg) { System.out.println("[LOG] " + msg); }
}
```
```java
3. File Utama 
File: ShoppingCart.java

package com.upb.agripos;
import java.util.*;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) throw new InvalidQuantityException("Jumlah harus > 0!");
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) throw new ProductNotFoundException("Produk tidak ada!");
        items.remove(p);
    }

    public void checkout() throws InsufficientStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getKey().getStock() < entry.getValue()) {
                throw new InsufficientStockException("Stok " + entry.getKey().getName() + " tidak cukup!");
            }
        }
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }
}
4. File Running: MainExceptionDemo.java

package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am Ade Miko - 2420202827 (Week 9)");
        System.out.println("===========================================");

        ProductService logger = ProductService.getInstance();
        ShoppingCart cart = new ShoppingCart();
        Product pupuk = new Product("P01", "Pupuk Organik", 25000.0, 10);

        try {
            logger.log("Menambah 5 pupuk ke keranjang...");
            cart.addProduct(pupuk, 5);

            logger.log("Melakukan proses checkout...");
            cart.checkout();

            System.out.println("\n>>> TRANSAKSI BERHASIL! <<<");
            System.out.println("Sisa stok gudang: " + pupuk.getStock());

        } catch (Exception e) {
            System.err.println("Terjadi Kesalahan: " + e.getMessage());
        } finally {
            System.out.println("[Finally] Sistem selesai memproses.");
        }
    }
}
```
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![valid](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week9-exception-handling/screenshots/Screenshot%202026-01-09%20215652.png) 
![valid](https://github.com/ademiko/oop-202501-240202827/blob/main/praktikum/week9-exception-handling/screenshots/Screenshot%202026-01-09%20215451.png) 
)
---

## Analisis
(
- Alur Kerja: Pada skenario valid ini, program menjalankan seluruh blok try tanpa pernah masuk ke blok catch. Hal ini dikarenakan data yang dimasukkan sudah memenuhi kriteria (jumlah > 0 dan jumlah beli < stok gudang).
- Perbedaan Pendekatan: Dibandingkan dengan skenario gagal sebelumnya, pendekatan valid ini membuktikan bahwa jika logika bisnis terpenuhi, pengecualian (exception) tidak akan dilempar, namun sistem keamanan tetap "siaga" di latar belakang.
- Kendala & Solusi: Kendala utama adalah sinkronisasi antara jumlah di keranjang belanja dengan jumlah stok di gudang. Solusinya adalah melakukan perulangan validasi stok sebelum benar-benar mengurangi nilai stok asli di objek Product.
---

## Kesimpulan
Penerapan Exception Handling memberikan perlindungan ganda pada aplikasi Agri-POS. Selain membuat aplikasi lebih stabil terhadap kesalahan input, penggunaan Custom Exception memudahkan pengembang dalam melakukan debugging karena pesan kesalahan yang muncul sangat spesifik sesuai dengan kasus yang terjadi di lagit add pangan.
---

## Quiz
(
1. Jelaskan perbedaan error dan exception.
Error adalah kegagalan sistem yang fatal (seperti StackOverflow), sedangkan Exception adalah kesalahan logika yang bisa diantisipasi (seperti Division by Zero).
2. Apa fungsi finally dalam blok try–catch–finally?
Blok kode yang akan selalu dijalankan untuk memastikan proses pembersihan (seperti menutup koneksi) tetap terjadi meski ada error.
3. Mengapa custom exception diperlukan?
Agar aplikasi memiliki identitas kesalahan yang jelas (misal: stok habis lebih jelas daripada sekadar ArithmeticException).
4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.
Validasi saat kartu member kadaluwarsa atau saat koneksi ke Payment Gateway terputus.
