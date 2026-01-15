package com.upb.agripos.week7;

import java.util.HashSet;

public class CustomerList {
    private final HashSet<String> customers = new HashSet<>();

    public void addCustomer(String name) {
        if (customers.add(name)) {
            System.out.println("Berhasil menambah pelanggan: " + name);
        } else {
            System.out.println("Gagal! Pelanggan '" + name + "' sudah terdaftar.");
        }
    }

    public void printAll() {
        System.out.println("Daftar Pelanggan Unik: " + customers);
    }
}
