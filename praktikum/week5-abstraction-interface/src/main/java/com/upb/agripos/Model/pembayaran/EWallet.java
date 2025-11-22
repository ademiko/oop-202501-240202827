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