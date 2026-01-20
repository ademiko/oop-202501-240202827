package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.scene.control.Alert;

public class ProductController {
    private ProductService service;
    private ProductFormView view;

    public ProductController(ProductService service, ProductFormView view) {
        this.service = service;
        this.view = view;
        initControl();
    }

    private void initControl() {
        view.btnAdd.setOnAction(event -> handleAddProduct());
    }

    private void handleAddProduct() {
        try {
            String code = view.txtCode.getText();
            String name = view.txtName.getText();
            double price = Double.parseDouble(view.txtPrice.getText());
            int stock = Integer.parseInt(view.txtStock.getText());

            Product p = new Product(code, name);
            
            // Panggil Service (Bukan DAO Langsung!)
            service.addProduct(p);

            // Update UI
            view.listView.getItems().add(p.getCode() + " - " + p.getName() + " [Rp" + p.getPrice() + "]");
            
            // Bersihkan Form
            view.txtCode.clear();
            view.txtName.clear();
            view.txtPrice.clear();
            view.txtStock.clear();

            new Alert(Alert.AlertType.INFORMATION, "Produk berhasil ditambahkan!").show();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Harga dan Stok harus berupa angka!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }
}