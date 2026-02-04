package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ReportView extends VBox {
    private TableView<Object[]> table; // Menggunakan Object[] untuk data fleksibel

    public ReportView() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);

        Label title = new Label("LAPORAN PENJUALAN AGRI-POS");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        table = new TableView<>();
        
        TableColumn<Object[], String> idCol = new TableColumn<>("ID Transaksi");
        idCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue()[0].toString()));
        
        TableColumn<Object[], String> totalCol = new TableColumn<>("Total Pendapatan");
        totalCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty("Rp " + d.getValue()[1].toString()));
        
        TableColumn<Object[], String> dateCol = new TableColumn<>("Tanggal");
        dateCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue()[2].toString()));

        table.getColumns().addAll(idCol, totalCol, dateCol);
        this.getChildren().addAll(title, table);
    }

    public TableView<Object[]> getTable() { return table; }
}