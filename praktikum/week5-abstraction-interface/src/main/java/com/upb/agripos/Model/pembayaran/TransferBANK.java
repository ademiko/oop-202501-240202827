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
