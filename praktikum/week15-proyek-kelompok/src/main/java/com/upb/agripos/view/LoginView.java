package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView extends VBox {
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnLogin;

    public LoginView() {
        setSpacing(15);
        setPadding(new Insets(50));
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f4f4f4;");

        Label lblTitle = new Label("AGRI-POS LOGIN");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        txtUsername = new TextField();
        txtUsername.setPromptText("Username");
        txtUsername.setMaxWidth(250);

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");
        txtPassword.setMaxWidth(250);

        btnLogin = new Button("MASUK");
        btnLogin.setMinWidth(250);
        btnLogin.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");

        getChildren().addAll(lblTitle, txtUsername, txtPassword, btnLogin);
    }

    public TextField getTxtUsername() { return txtUsername; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public Button getBtnLogin() { return btnLogin; }
}