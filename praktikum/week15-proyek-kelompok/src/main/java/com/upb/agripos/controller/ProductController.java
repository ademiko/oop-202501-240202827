package com.upb.agripos.controller;

import java.util.List;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductView;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ProductController {
    private final ProductService service;
    private final ProductView view;

    public ProductController(ProductService service, ProductView view) {
        this.service = service;
        this.view = view;

        // 1. Ambil data dari database saat aplikasi dibuka
        refreshTable();

        // 2. Pasang aksi untuk tombol Simpan
        view.btnSave.setOnAction(e -> handleSave());

        // 3. Pasang aksi untuk tombol Hapus
        view.btnDelete.setOnAction(e -> handleDelete());
    }

    private void handleSave() {
        try {
            // Validasi format angka sebelum dikirim ke Service
            String code = view.txtCode.getText();
            String name = view.txtName.getText();
            String category = view.txtCategory.getText();
            
            if (code.isEmpty() || name.isEmpty()) {
                throw new Exception("Kode dan Nama produk tidak boleh kosong!");
            }

            double price = Double.parseDouble(view.txtPrice.getText());
            int stock = Integer.parseInt(view.txtStock.getText());

            Product p = new Product(code, name, category, price, stock);
            
            // Panggil Service untuk simpan ke database
            service.addProduct(p); 

            // Jika berhasil:
            refreshTable();
            clearForm();
            showPopup(Alert.AlertType.INFORMATION, "Sukses", "Produk '" + name + "' berhasil disimpan!");

        } catch (NumberFormatException ex) {
            showPopup(Alert.AlertType.ERROR, "Kesalahan Input", "Harga dan Stok harus berupa angka murni!");
        } catch (Exception ex) {
            // Menampilkan pesan error asli dari PostgreSQL atau Service
            showPopup(Alert.AlertType.ERROR, "Gagal Menyimpan", ex.getMessage());
        }
    }

    private void handleDelete() {
        Product selected = view.table.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            showPopup(Alert.AlertType.WARNING, "Peringatan", "Pilih dulu produk di tabel yang ingin dihapus!");
            return;
        }

        // Konfirmasi sebelum hapus (Safety First!)
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Hapus produk " + selected.getName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            try {
                service.deleteProduct(selected.getCode());
                refreshTable();
                showPopup(Alert.AlertType.INFORMATION, "Terhapus", "Produk telah dihapus dari database.");
            } catch (Exception ex) {
                showPopup(Alert.AlertType.ERROR, "Gagal Menghapus", ex.getMessage());
            }
        }
    }

    private void refreshTable() {
        try {
            List<Product> data = service.getAllProducts();
            view.table.setItems(FXCollections.observableArrayList(data));
        } catch (Exception e) {
            showPopup(Alert.AlertType.ERROR, "Database Error", "Gagal mengambil data: " + e.getMessage());
        }
    }

    private void clearForm() {
        view.txtCode.clear();
        view.txtName.clear();
        view.txtCategory.clear();
        view.txtPrice.clear();
        view.txtStock.clear();
    }

    // Helper untuk memudahkan membuat popup Alert
    private void showPopup(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}