package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class ProductTableView extends VBox {
    public TextField txtCode = new TextField(), txtName = new TextField();
    public TextField txtPrice = new TextField(), txtStock = new TextField();
    public Button btnAdd = new Button("Tambah"), btnDelete = new Button("Hapus Terpilih");
    public TableView<Product> table = new TableView<>();

    public ProductTableView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.add(new Label("Kode:"), 0, 0); form.add(txtCode, 1, 0);
        form.add(new Label("Nama:"), 0, 1); form.add(txtName, 1, 1);
        form.add(new Label("Harga:"), 0, 2); form.add(txtPrice, 1, 2);
        form.add(new Label("Stok:"), 0, 3); form.add(txtStock, 1, 3);

        TableColumn<Product, String> c1 = new TableColumn<>("Kode");
        c1.setCellValueFactory(new PropertyValueFactory<>("code"));
        TableColumn<Product, String> c2 = new TableColumn<>("Nama");
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> c3 = new TableColumn<>("Harga");
        c3.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Integer> c4 = new TableColumn<>("Stok");
        c4.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table.getColumns().addAll(c1, c2, c3, c4);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox actions = new HBox(10, btnAdd, btnDelete);
        this.getChildren().addAll(new Label("AGRI-POS MASTER DATA"), form, actions, table);
    }
}