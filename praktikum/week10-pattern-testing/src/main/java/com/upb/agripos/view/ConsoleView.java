package com.upb.agripos.view;

public class ConsoleView {
    public void printProductDetails(String code, String name) {
        System.out.println("\n=== VIEW: TAMPILAN PRODUK ===");
        System.out.println("ID Produk   : " + code);
        System.out.println("Nama Produk : " + name);
        System.out.println("=============================");
    }
}