package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProductFormView extends VBox {
    public TextField txtCode = new TextField();
    public TextField txtName = new TextField();
    public TextField txtPrice = new TextField();
    public TextField txtStock = new TextField();
    public Button btnAdd = new Button("Tambah Produk");
    public ListView<String> listView = new ListView<>();

    public ProductFormView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setAlignment(Pos.TOP_CENTER);

        Label lblTitle = new Label("FORM INPUT PRODUK AGRI-POS");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Kode Produk:"), 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(new Label("Nama Produk:"), 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(new Label("Harga:"), 0, 2);
        grid.add(txtPrice, 1, 2);
        grid.add(new Label("Stok:"), 0, 3);
        grid.add(txtStock, 1, 3);

        // Styling tombol agar lebar
        btnAdd.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(lblTitle, grid, btnAdd, new Label("Daftar Produk Terinput:"), listView);
    }
}