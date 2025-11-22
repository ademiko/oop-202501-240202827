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