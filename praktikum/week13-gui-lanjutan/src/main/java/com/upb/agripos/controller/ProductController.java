package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class ProductController {
    private ProductService service;
    private ProductTableView view;

    public ProductController(ProductService service, ProductTableView view) {
        this.service = service;
        this.view = view;
        loadData();

        // Lambda Expressions untuk event handling
        view.btnAdd.setOnAction(e -> handleAdd());
        view.btnDelete.setOnAction(e -> handleDelete());
    }

    private void loadData() {
        try {
            view.table.setItems(FXCollections.observableArrayList(service.getAllProducts()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAdd() {
        try {
            Product p = new Product(
                view.txtCode.getText(), view.txtName.getText(),
                Double.parseDouble(view.txtPrice.getText()),
                Integer.parseInt(view.txtStock.getText())
            );
            service.addProduct(p);
            loadData();
            view.txtCode.clear(); view.txtName.clear(); view.txtPrice.clear(); view.txtStock.clear();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    private void handleDelete() {
        Product selected = view.table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                service.removeProduct(selected.getCode());
                loadData();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Gagal hapus: " + e.getMessage()).show();
            }
        }
    }
}