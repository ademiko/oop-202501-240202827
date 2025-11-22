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