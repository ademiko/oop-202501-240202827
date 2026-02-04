package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.AdminProductView;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AdminProductController {
    private final ProductService productService;
    private final AdminProductView view;

    public AdminProductController(ProductService ps, AdminProductView v) {
        this.productService = ps;
        this.view = v;

        // 1. Menghubungkan tabel dengan data yang sinkron
        view.getAdminTable().setItems(productService.getSharedProducts());

        // 2. LOGIKA TOMBOL SIMPAN (Tetap seperti semula)
        view.getBtnSimpan().setOnAction(e -> {
            try {
                String kode = view.getTxtKode().getText();
                String nama = view.getTxtNama().getText();
                String kategori = view.getTxtKategori().getText();
                double harga = Double.parseDouble(view.getTxtHarga().getText());
                int stok = Integer.parseInt(view.getTxtStok().getText());

                Product p = new Product(kode, nama, kategori, harga, stok);
                productService.addProduct(p); 

                productService.refreshSharedData();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Data Berhasil Diperbarui!").show();
            } catch (Exception ex) {
                showError("Gagal menyimpan: " + ex.getMessage());
            }
        });

        // 3. FITUR BARU: LOGIKA TOMBOL HAPUS
        view.getBtnHapus().setOnAction(e -> {
            // Mengambil barang yang dipilih dari tabel
            Product selected = view.getAdminTable().getSelectionModel().getSelectedItem();
            
            if (selected == null) {
                showError("Pilih produk di tabel terlebih dahulu!");
                return;
            }

            // Konfirmasi sebelum hapus
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Hapus produk " + selected.getName() + "?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        productService.deleteProduct(selected.getCode()); // Hapus di DB
                        productService.refreshSharedData();              // Sinkronkan UI
                        new Alert(Alert.AlertType.INFORMATION, "Data Berhasil Dihapus!").show();
                    } catch (Exception ex) {
                        showError("Gagal menghapus: " + ex.getMessage());
                    }
                }
            });
        });
    }

    private void clearFields() {
        view.getTxtKode().clear();
        view.getTxtNama().clear();
        view.getTxtKategori().clear();
        view.getTxtHarga().clear();
        view.getTxtStok().clear();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }
}