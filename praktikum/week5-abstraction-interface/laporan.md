# Laporan Praktikum Minggu 5 
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : Ade Miko
- NIM   : 240202827
- Kelas : 3IKRA

---

## Tujuan
1. Mampu menjelaskan perbedaan abstract class dan interface.
2. Mendesain abstract class dengan method abstrak.
3. Membuat dan mengimplementasikan interface.
4. Menerapkan multiple inheritance melalui interface.
---

## Dasar Teori
 
1. Abstract Class: Kelas yang tidak bisa diinstansiasi dan berfungsi sebagai kerangka dasar. Di sini, Pembayaran adalah abstract class karena memiliki method abstrak biaya() dan prosesPembayaran() yang implementasinya berbeda di setiap jenis pembayaran.
2. Interface: Kontrak yang berisi method tanpa body. Interface memungkinkan Java melakukan simulasi multiple inheritance. Di sini digunakan Validatable (untuk validasi logika) dan Receiptable (untuk mencetak struk).

---

## Langkah Praktikum
(Tuliskan Langkah-langkah dalam prakrikum, contoh:
1. Abstract Class – Pembayaran
- Buat Pembayaran (abstract) dengan field invoiceNo, total dan method:
   - double biaya() (abstrak) → biaya tambahan (fee).
   - boolean prosesPembayaran() (abstrak) → mengembalikan status berhasil/gagal.
   - double totalBayar() (konkrit) → return total + biaya();.

2.  Subclass Konkret
Cash → biaya = 0, proses = selalu berhasil jika tunai >= totalBayar().
EWallet → biaya = 1.5% dari total; proses = membutuhkan validasi.

3. Interface
Validatable → boolean validasi(); (contoh: OTP).
Receiptable → String cetakStruk();

4. Multiple Inheritance via Interface
EWallet mengimplementasikan dua interface: Validatable, Receiptable.
Cash setidaknya mengimplementasikan Receiptable.

5. Main Class
Buat MainAbstraction.java untuk mendemonstrasikan pemakaian Pembayaran (polimorfik).
Tampilkan hasil proses dan struk. Di akhir, panggil CreditBy.print("[NIM]", "[Nama]")
---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  
receiptable 
```java
// Contoh
package com.upb.agripos.Model.kontrak;

public interface Receiptable {
    String cetakStruk();
}

```
validatable
```java
package com.upb.agripos.Model.kontrak;

public interface Validatable {
    boolean validasi(); // misal validasi OTP/ PIN
}
```
cash
```java
package com.upb.agripos.Model.pembayaran;

import com.upb.agripos.Model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // sederhana: cukup uang tunai
    }

    @Override
    public String cetakStruk() {
        double total = totalBayar();
        double selisih = tunai - total;
        String status;
        String detail;
        if (selisih < 0) {
            status = "GAGAL (Kekurangan)";
            detail = String.format("KEKURANGAN: %,.2f", Math.abs(selisih));
        } else if (selisih > 0) {
            status = "LUNAS";
            detail = String.format("KEMBALI: %,.2f", selisih);
        } else {
            status = "LUNAS";
            detail = "TIDAK ADA KEMBALI";
        }

        return "\n--- STRUK PEMBAYARAN TUNAI ---\n" +
               "Invoice No: " + invoiceNo + "\n" +
               "Total Bayar (incl. fee): " + String.format("%,.2f", total) + "\n" +
               "Uang Diterima: " + String.format("%,.2f", tunai) + "\n" +
               detail + "\n" +
               "STATUS: " + status + "\n" +
               "-----------------------------";
    }
}
```
ewallet
```java 
package com.upb.agripos.Model.pembayaran;

import com.upb.agripos.Model.kontrak.Validatable;
import com.upb.agripos.Model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp; 
    private boolean isProcessed = false; //  untuk Status
    private boolean statusPembayaran = false; //  untuk Hasil Proses


    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% fee
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6; // contoh validasi sederhana
    }

    @Override
    public boolean prosesPembayaran() {
         if (!isProcessed) {
            this.statusPembayaran = validasi(); // Jalankan validasi dan simpan hasilnya
            this.isProcessed = true; // Tandai bahwa proses telah dijalankan
         }
            return this.statusPembayaran; // Kembalikan hasil proses pembayaran
    }

    @Override
    public String cetakStruk() {
        // pastikan proses pembayaran/validasi dijalankan sebelum mencetak struk
        boolean berhasil = prosesPembayaran();
        String status = berhasil ? "BERHASIL" : "GAGAL (Validasi Gagal)";
        // mask OTP sedikit untuk keamanan saat menampilkan di struk
        String otpDisplay = (otp == null) ? "null" : (otp.length() > 3 ? otp.substring(0,3) + "***" : otp);
        String pesanValidasi = "-> Mencoba Validasi OTP (" + otpDisplay + ")... " + (berhasil ? "BERHASIL" : "GAGAL (OTP Invalid)");

        double diterima = berhasil ? total : 0.0; // jika berhasil, anggap pembayaran dilakukan sebesar total; jika gagal, belum dibayar
        double selisih = diterima - total;
        String ket;
        if (selisih < 0) {
            ket = String.format("KEKURANGAN: %,.2f", Math.abs(selisih));
        } else if (selisih > 0) {
            ket = String.format("KEMBALI: %,.2f", selisih);
        } else {
            ket = "LUNAS";
        }

        return "\n--- STRUK PEMBAYARAN E-WALLET ---\n" +
                 pesanValidasi + "\n" +
               "Invoice No: " + invoiceNo + "\n" +
               "Total Belanja: " + String.format("%,.2f", total) + "\n" +
               "Akun E-Wallet: " + akun + "\n" +
               "Biaya (Fee 1.5%): " + String.format("%,.2f", biaya()) + "\n" +
               "**TOTAL BAYAR**: " + String.format("%,.2f", totalBayar()) + "\n" +
               "Uang Diterima: " + String.format("%,.2f", diterima) + "\n" +
               ket + "\n" +
               "STATUS: " + status + "\n" +
               "\n---------------------------------";

    }
}
```

pembayaran 
```java
package com.upb.agripos.Model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // fee/biaya tambahan
    public abstract boolean prosesPembayaran();   // proses spesifik tiap metode

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
```
transferbank
```java
package com.upb.agripos.Model.pembayaran;

import com.upb.agripos.Model.kontrak.Receiptable;
import com.upb.agripos.Model.kontrak.Validatable;
import com.upb.agripos.Model.pembayaran.Pembayaran;

public class TransferBANK extends Pembayaran implements Validatable, Receiptable {
    private final double BIAYA_TETAP = 3500.00;
    private String kodeBank;
    private boolean isProcessed = false; // Status apakah proses pembayaran sudah dijalankan
    private boolean statusPembayaran = false; // Hasil dari proses/validasi yang tersimpan


    public TransferBANK(String invoiceNo, double total, String kodeBank) {
        super(invoiceNo, total);
        this.kodeBank = kodeBank;
    }

    @Override
    public double biaya() {
        return BIAYA_TETAP; // Biaya tetap Rp3.500,00
    }

    @Override
    public boolean validasi() {
        // Contoh validasi: kode bank harus 3 digit angka
        boolean valid = kodeBank != null && kodeBank.matches("\\d{3}");
        // validasi sekarang tidak mencetak; cetakStruk() akan menampilkan hasil validasi bersama struk
        return valid; 
    }

    @Override
    public boolean prosesPembayaran() {
        if (!isProcessed) {
            this.statusPembayaran = validasi(); // Jalankan validasi dan simpan hasilnya
            this.isProcessed = true;
        }
        return this.statusPembayaran;
    }

    @Override
    public String cetakStruk() {
        // Panggilan prosesPembayaran() di sini akan menjalankan validasi
     boolean berhasil = prosesPembayaran();
     String status = berhasil ? "BERHASIL" : "GAGAL (Validasi Gagal)";
     // buat pesan validasi yang akan ditampilkan bersama struk
     String pesanValidasi = "-> Mencoba Validasi Kode Bank (" + kodeBank + ")... " + (berhasil ? "BERHASIL" : "GAGAL (Kode Invalid)");

     double diterima = berhasil ? total : 0.0;
     double selisih = diterima - total;
     String ket;
     if (selisih < 0) {
         ket = String.format("KEKURANGAN: %,.2f", Math.abs(selisih));
     } else if (selisih > 0) {
         ket = String.format("KEMBALI: %,.2f", selisih);
     } else {
         ket = "LUNAS";
     }

     return "\n--- STRUK PEMBAYARAN TRANSFER BANK ---\n" +
        pesanValidasi + "\n" +
         "Invoice No: " + invoiceNo + "\n" +
         "Kode Bank: " + kodeBank + "\n" +
         "Total Belanja: " + String.format("%,.2f", total) + "\n" +
         "Biaya (Fee Tetap): " + String.format("%,.2f", biaya()) + "\n" +
         "**TOTAL BAYAR**: " + String.format("%,.2f", totalBayar()) + "\n" +
         "Uang Diterima: " + String.format("%,.2f", diterima) + "\n" +
         ket + "\n" +
         "STATUS: " + status + "\n" +
         "----------------------------------------";
    }
}
```
mainabstraction
```java
package com.upb.agripos;

import com.upb.agripos.Model.pembayaran.*;
import com.upb.agripos.Model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran cashInvalid = new Cash("INV-001", 100000, 90000); // Uang tidak cukup
        cash.prosesPembayaran();
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");
        ew.prosesPembayaran(); // Memanggil Validasi
        Pembayaran ewInvalid = new EWallet("INV-003", 150000, "user@ewallet", "123"); // OTP tidak valid
        ewInvalid.prosesPembayaran(); // Memanggil Validasi
        Pembayaran bankTransfer = new TransferBANK("INV-004", 200000, "123");
        bankTransfer.prosesPembayaran(); // Memanggil Validasi
        Pembayaran bankTransferValid = new TransferBANK("INV-005", 200000, "abc");
        bankTransferValid.prosesPembayaran(); // Memanggil Validasi
        

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) cashInvalid).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) ewInvalid).cetakStruk());
        System.out.println(((Receiptable) bankTransfer).cetakStruk());
        System.out.println(((Receiptable) bankTransferValid).cetakStruk());

    CreditBy.print("240202827", "Ade Miko");
    }
}
```


---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot] (/praktikum/week5-abstraction-interface/screenshots/Screenshot%202025-11-22%20194928.png )
![Sreenshot] (/praktikum/week5-abstraction-interface/screenshots/Screenshot%202025-11-22%20194951.png)
![Sreenshot] (/praktikum/week5-abstraction-interface/screenshots/Screenshot%202025-11-22%20195007.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Kendala yang dihadapi dan cara mengatasinya.  
)
---

## Kesimpulan
(penggunaan abstract class (Pembayaran) sangat efektif untuk membangun kerangka dasar sistem yang menghindari duplikasi kode pada atribut umum seperti nomor invoice dan total belanja, sekaligus mewajibkan subclass mendefinisikan perilaku spesifiknya sendiri. Selain itu, penggunaan interface (Validatable, Receiptable) memberikan fleksibilitas tinggi dalam mendefinisikan kemampuan kelas tanpa terikat pewarisan tunggal, sehingga memungkinkan simulasi multiple inheritance seperti pada EWallet dan TransferBank. Gabungan kedua konsep ini menciptakan polimorfisme yang membuat kode pada Main Class menjadi lebih bersih, konsisten, dan mudah dikembangkan di masa depan tanpa perlu merombak logika dasar yang sudah ada.)

---

## Quiz
(
1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface!
- Abstract Class digunakan ketika kita memiliki hubungan "IS-A" (adalah bagian dari) dan ingin mewariskan state (variabel/field) serta perilaku umum (method konkrit) ke kelas anak, tetapi ada sebagian perilaku yang harus didefinisikan ulang (abstract method). Contoh: Pembayaran punya field total yang sama untuk semua, tapi cara hitung biaya() beda.
- Interface digunakan untuk mendefinisikan kemampuan ("CAN-DO") atau kontrak yang bisa diterapkan pada kelas apa saja tanpa memandang hierarki keturunan. Interface tidak menyimpan state (kecuali konstanta). Contoh: Validatable, bisa dipakai oleh Pembayaran atau mungkin nanti oleh LoginUser.

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?
- Java tidak mendukung multiple inheritance via Class (extends A, B) untuk menghindari Diamond Problem (kebingungan jika Class A dan B punya method yang sama, mana yang harus diambil?).
- Dengan Interface, kita hanya mewarisi deklarasi method (kontrak), bukan implementasinya (sebelum Java 8). Jadi, kelas pengimplementasilah yang menentukan cara kerjanya, sehingga tidak ada ambiguitas.

3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang interface?
- Abstract Class (Pembayaran): Karena semua jenis pembayaran pasti punya invoiceNo dan total belanja. Ini adalah shared code yang sebaiknya tidak ditulis ulang di setiap subclass.
- Interface (Validatable, Receiptable): Karena tidak semua pembayaran butuh validasi (Cash tidak butuh), dan mungkin ada objek selain pembayaran yang butuh dicetak struknya. Interface memberikan fleksibilitas untuk "menempelkan" fitur ini hanya pada kelas yang butuh.  )
