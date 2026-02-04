package com.upb.agripos.view;

import com.upb.agripos.model.Product;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TransactionView extends VBox {
    private TableView<Product> productTable;
    private TableView<Product> cartTable;
    private TextField txtSearch;
    private Button btnTambah, btnBayar, btnHapus;
    private Label lblTotal;
    private RadioButton rbUmum, rbPetani;
    private ComboBox<String> cbMetodeBayar;

    public TransactionView() {
        setSpacing(10);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: #f0f0f0;");

        // 1. Bagian Inventaris (Atas)
        Label lblInventaris = new Label("Inventaris:");
        txtSearch = new TextField();
        txtSearch.setPromptText("Cari produk...");

        productTable = new TableView<>();
        setupProductTable(); // Menampilkan: Produk, Harga, Stok

        // 2. Tombol Aksi
        btnTambah = new Button("Tambah >>");
        btnTambah.setMaxWidth(Double.MAX_VALUE);
        btnTambah.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        // 3. Bagian Keranjang (Bawah)
        Label lblKeranjang = new Label("Keranjang:");
        cartTable = new TableView<>();
        setupCartTable(); // Menampilkan: Produk, Harga, JUMLAH

        // 4. Panel Pembayaran & Diskon
        HBox discountBox = new HBox(20);
        ToggleGroup group = new ToggleGroup();
        rbUmum = new RadioButton("Umum (Diskon 10%)");
        rbUmum.setToggleGroup(group);
        rbUmum.setSelected(true);
        rbPetani = new RadioButton("Petani (Potongan Rp 5.000)");
        rbPetani.setToggleGroup(group);
        discountBox.getChildren().addAll(rbUmum, rbPetani);

        lblTotal = new Label("Total: Rp 0");
        lblTotal.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        cbMetodeBayar = new ComboBox<>();
        cbMetodeBayar.getItems().addAll("Tunai", "QRIS");
        cbMetodeBayar.setValue("Tunai");

        btnBayar = new Button("BAYAR SEKARANG");
        btnBayar.setMinWidth(200);
        btnBayar.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox footer = new HBox(20, cbMetodeBayar, btnBayar);
        footer.setPadding(new Insets(10, 0, 0, 0));

        getChildren().addAll(lblInventaris, txtSearch, productTable, btnTambah, lblKeranjang, cartTable, discountBox, lblTotal, footer);
    }

    private void setupProductTable() {
        TableColumn<Product, String> nCol = new TableColumn<>("Produk");
        nCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));

        TableColumn<Product, Double> hCol = new TableColumn<>("Harga");
        hCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPrice()));

        TableColumn<Product, Integer> sCol = new TableColumn<>("Stok");
        sCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getStock()));

        productTable.getColumns().addAll(nCol, hCol, sCol);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setupCartTable() {
        TableColumn<Product, String> nCol = new TableColumn<>("Produk");
        nCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));

        TableColumn<Product, Double> hCol = new TableColumn<>("Harga");
        hCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getPrice()));

        // PERUBAHAN: Kolom Stok diganti menjadi "Jumlah" (Quantity)
        TableColumn<Product, Integer> jCol = new TableColumn<>("Jumlah");
        jCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getQuantity()));

        cartTable.getColumns().addAll(nCol, hCol, jCol);
        cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // Getters agar Controller bisa beraksi
    public TableView<Product> getProductTable() { return productTable; }
    public TableView<Product> getCartTable() { return cartTable; }
    public Button getBtnTambah() { return btnTambah; }
    public Button getBtnBayar() { return btnBayar; }
    public Label getLblTotal() { return lblTotal; }
    public RadioButton getRbUmum() { return rbUmum; }
    public RadioButton getRbPetani() { return rbPetani; }
    public ComboBox<String> getCbMetodeBayar() { return cbMetodeBayar; }
    public TextField getTxtSearch() { return txtSearch; }
}