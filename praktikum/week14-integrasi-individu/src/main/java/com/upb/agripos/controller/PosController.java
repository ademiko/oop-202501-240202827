package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

public class PosController {
    private ProductService pService;
    private CartService cService;
    private PosView view;

    public PosController(ProductService ps, CartService cs, PosView v) {
        this.pService = ps; this.cService = cs; this.view = v;
        loadProducts();
        
        view.btnAddProd.setOnAction(e -> {
            try {
                Product p = new Product(view.txtCode.getText(), view.txtName.getText(), Double.parseDouble(view.txtPrice.getText()), Integer.parseInt(view.txtStock.getText()));
                pService.addProduct(p);
                loadProducts();
            } catch (Exception ex) { showError(ex.getMessage()); }
        });

        view.btnDeleteProd.setOnAction(e -> {
            try {
                Product s = view.table.getSelectionModel().getSelectedItem();
                if (s != null) { pService.deleteProduct(s.getCode()); loadProducts(); }
            } catch (Exception ex) { showError(ex.getMessage()); }
        });

        view.btnAddToCart.setOnAction(e -> {
            try {
                Product s = view.table.getSelectionModel().getSelectedItem();
                int qty = Integer.parseInt(view.txtQty.getText());
                cService.addToCart(s, qty);
                updateCartUI();
            } catch (Exception ex) { showError(ex.getMessage()); }
        });
    }

    private void loadProducts() {
        try { view.table.setItems(FXCollections.observableArrayList(pService.getAllProducts())); } catch (Exception e) {}
    }

    private void updateCartUI() {
        view.cartList.getItems().clear();
        cService.getCart().getItems().forEach(item -> view.cartList.getItems().add(item.toString()));
        view.lblTotal.setText("Total: Rp " + cService.getCart().getTotalPrice());
    }

    private void showError(String msg) { new Alert(Alert.AlertType.ERROR, msg).show(); }
}