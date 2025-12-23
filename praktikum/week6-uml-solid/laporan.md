# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Desain Arsitektur Sistem dengan UML dan Prinsip SOLID

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu mengidentifikasi kebutuhan sistem ke dalam diagram UML.
2. Mahasiswa mampu menggambar UML Class Diagram dengan relasi antar class yang tepat.
3. Mahasiswa mampu menjelaskan prinsip desain OOP (SOLID).
4. Mahasiswa mampu menerapkan minimal dua prinsip SOLID dalam kode program.

Dasar Teori
---

1. Unified Modeling Language (UML): Sekumpulan diagram standar yang digunakan untuk memvisualisakan struktur dan perilaku sistem.
2. SOLID Principles: Lima prinsip desain kelas (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) untuk menciptakan kode yang adaptif dan mudah dipelihara.
3. Behavioral & Structural Diagrams: Penggunaan Activity/Sequence untuk alur kerja dan Class Diagram untuk struktur data sistem.
---

## Langkah Praktikum
1. Pemetaan Kebutuhan: Mengidentifikasi aktor dan use case utama untuk sistem Agri-POS.
2. Perancangan Alur Kerja: Menyusun Activity Diagram untuk proses checkout dan Sequence Diagram untuk mekanisme refund.
3. Arsitektur Objek: Membangun Class Diagram dengan menerapkan pola desain yang mendukung prinsip SOLID.
---
## CONTOH kode 
```java
// Abstraksi untuk strategi diskon
public interface IDiscountStrategy {
    double calculate(double originalPrice);
}

// Implementasi diskon member
public class MemberDiscount implements IDiscountStrategy {
    @Override
    public double calculate(double originalPrice) {
        return originalPrice * 0.90; // Diskon 10%
    }
}

// Kelas Sale yang menerima strategi apa pun tanpa perlu diubah kodenya
public class Sale {
    private IDiscountStrategy discountStrategy;

    public void setDiscountStrategy(IDiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    public double getTotal(double price) {
        return discountStrategy.calculate(price);
    }
}

```

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots//praktikum/week6-uml-solid/screenshots/RLJ1Rjim3BqRy3yGEMpf8Vs0eGZgfSCmD5k2UksEWsRJYM1PyYGggp7itoVPacDxyyKK-VZu95AwDyGqsKQaYS2AW1HeVZeJb5nZGLp9D645AGrPsV1nySWCrt6HsnRrXHirUbOviGe-sfB9qmgO2MQQf8bdTQP0M6KdpJV7GmvVy9KsUxoWWBmpXCqAVgS9kEyjx9H6-AgPD2p8yNvh.png)
)
---

## Analisis
* Analisis Penerapan Prinsip SOLID
Dalam desain sistem Agri-POS ini, prinsip Single Responsibility Principle (SRP) diterapkan pada pemisahan kelas Product dan ProductBatch. Kelas Product hanya bertanggung jawab menyimpan informasi dasar produk, sedangkan ProductBatch mengelola detail stok spesifik berdasarkan tanggal kadaluwarsa. Untuk prinsip Open/Closed Principle (OCP), sistem menggunakan interface IDiscountStrategy sehingga aturan diskon baru (seperti diskon grosir atau kupon) dapat ditambahkan tanpa mengubah logika pada kelas Sale.

* Prinsip Liskov Substitution Principle (LSP) dipenuhi melalui penggunaan interface IPaymentMethod, di mana objek CashPayment atau QRISPayment dapat menggantikan peran interface tersebut dalam proses checkout secara konsisten. Selanjutnya, Interface Segregation Principle (ISP) diterapkan dengan memisahkan ICustomerObserver dari interface transaksi lainnya, memastikan kelas yang hanya perlu memantau poin loyalitas tidak dipaksa bergantung pada metode transaksi yang tidak relevan. Terakhir, Dependency Inversion Principle (DIP) terlihat pada kelas Sale yang bergantung pada abstraksi IPaymentMethod dan IDiscountStrategy, bukan pada implementasi konkretnya, sehingga mempermudah proses testing dan penggantian modul.

*NTraceability (Keterhubungan Sistem)
Kebutuhan fungsional Manajemen Produk direalisasikan melalui Use Case "Kelola Batch & Expiry Date", yang secara struktural diimplementasikan oleh kelas Product dan ProductBatch dalam Class Diagram. Fitur Transaksi Penjualan dipetakan ke dalam Use Case "Checkout (Split Payment)" dan dijabarkan alurnya secara mendetail dalam Activity Diagram proses checkout, yang melibatkan kelas Sale dan LineItem.

* Untuk Metode Pembayaran, sistem mendukung kebutuhan ini melalui alur Sequence Diagram proses pembayaran, yang direalisasikan oleh interface IPaymentMethod beserta turunannya seperti CashPayment dan SplitPayment. Fitur Diskon dan Loyalty dikelola melalui Use Case "Kelola Member & Loyalty" yang terhubung dengan interface IDiscountStrategy dan LoyaltyService. Terakhir, kebutuhan Approval/Void (fitur next-level) direalisasikan melalui Use Case "Void Transaksi" dan diperinci dalam Sequence Diagram Refund yang melibatkan aktor Supervisor serta kelas AuthService.

## Kesimpulan
---
Desain sistem Agri-POS ini telah mengintegrasikan kebutuhan fungsional ke dalam empat diagram UML secara konsisten. Penerapan prinsip SOLID, terutama pada modul pembayaran dan diskon, memastikan sistem memiliki fleksibilitas tinggi untuk penambahan fitur di masa depan tanpa harus melakukan perombakan besar pada kode inti.


## Quiz
(1. Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.
   **Jawaban:** Agregasi adalah hubungan "memiliki" yang lemah (misal: Sale memiliki Customer, jika transaksi dihapus, data customer tetap ada). Komposisi adalah hubungan yang kuat (misal: Product memiliki ProductBatch, jika produk dihapus, data batch-nya tidak bisa berdiri sendiri dan harus ikut dihapus).

2. Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?
   **Jawaban:** Memungkinkan pengembang menambah fitur baru hanya dengan menambah kelas baru (extension), sehingga mengurangi risiko bug pada fitur lama yang sudah stabil karena kodenya tidak dimodifikasi.

3. Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.  
   **Jawaban:** DIP memungkinkan penggunaan Mock Objects. Karena kelas utama bergantung pada interface, kita bisa memasukkan objek tiruan saat unit testing tanpa perlu menjalankan sistem database atau payment gateway yang asli.
   )
