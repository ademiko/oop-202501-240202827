package com.upb.agripos.controller;

import java.util.ArrayList;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.TransactionView;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;

public class TransactionController {
    private final CartService cartService;
    private final ProductService productService;
    private final TransactionView view;

    public TransactionController(CartService cs, ProductService ps, TransactionView v) {
        this.cartService = cs;
        this.productService = ps;
        this.view = v;

        initialize();
    }

    private void initialize() {
        // 1. Tampilkan Data Produk & Fitur Cari
        FilteredList<Product> filteredData = new FilteredList<>(productService.getSharedProducts(), p -> true);
        view.getProductTable().setItems(filteredData);

        view.getTxtSearch().textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(p -> {
                if (newVal == null || newVal.isEmpty()) return true;
                return p.getName().toLowerCase().contains(newVal.toLowerCase()) || 
                       p.getCode().toLowerCase().contains(newVal.toLowerCase());
            });
        });

        // 2. Tombol Tambah ke Keranjang
        view.getBtnTambah().setOnAction(e -> handleTambahKeKeranjang());

        // 3. Listener untuk Diskon (Update total saat radio button diklik)
        view.getRbUmum().setOnAction(e -> hitungTotal());
        view.getRbPetani().setOnAction(e -> hitungTotal());

        // 4. Tombol Bayar
        view.getBtnBayar().setOnAction(e -> handleBayar());
    }

    private void handleTambahKeKeranjang() {
        Product selected = view.getProductTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Pilih produk terlebih dahulu!").show();
            return;
        }

        // Cek stok sebelum masuk keranjang
        if (selected.getStock() > 0) {
            Product inCart = view.getCartTable().getItems().stream()
                .filter(p -> p.getCode().equals(selected.getCode()))
                .findFirst().orElse(null);

            if (inCart != null) {
                // Jika barang sama sudah ada, naikkan jumlahnya
                inCart.setQuantity(inCart.getQuantity() + 1);
                view.getCartTable().refresh(); 
            } else {
                // Jika baru, set jumlah 1 dan tambahkan ke tabel bawah
                selected.setQuantity(1);
                view.getCartTable().getItems().add(selected);
            }
            hitungTotal();
        } else {
            new Alert(Alert.AlertType.ERROR, "Stok produk ini habis!").show();
        }
    }

    private void hitungTotal() {
        double subtotal = view.getCartTable().getItems().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity()).sum();
        
        double totalAkhir = subtotal;

        // Logika Diskon
        if (view.getRbUmum().isSelected()) {
            totalAkhir = subtotal * 0.9; // Diskon 10%
        } else if (view.getRbPetani().isSelected()) {
            totalAkhir = Math.max(0, subtotal - 5000); // Potongan Rp 5.000
        }

        view.getLblTotal().setText(String.format("Total: Rp %,.0f", totalAkhir));
    }

    private void handleBayar() {
        if (view.getCartTable().getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Keranjang masih kosong!").show();
            return;
        }

        try {
            // Ambil total dari label (hilangkan teks "Total: Rp " dan tanda koma)
            double total = Double.parseDouble(view.getLblTotal().getText()
                    .replace("Total: Rp ", "").replace(",", ""));
            
            String metode = view.getCbMetodeBayar().getValue();

            // 1. Simpan ke Database via CartService
            cartService.processTransaction(total, new ArrayList<>(view.getCartTable().getItems()), metode);

            // 2. Refresh data stok di semua tab
            productService.refreshSharedData();

            // 3. Bersihkan Keranjang
            view.getCartTable().getItems().clear();
            hitungTotal();

            new Alert(Alert.AlertType.INFORMATION, "Transaksi Berhasil! Stok telah diperbarui.").show();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Gagal memproses transaksi: " + ex.getMessage()).show();
            ex.printStackTrace();
        }
    }
}