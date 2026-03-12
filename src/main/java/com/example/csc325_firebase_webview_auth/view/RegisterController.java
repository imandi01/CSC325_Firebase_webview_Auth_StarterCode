package com.example.csc325_firebase_webview_auth.view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void register() {

        String email = emailField.getText();
        String password = passwordField.getText();

        System.out.println("Register: " + email);

        // later connect Firebase Auth here
    }

    @FXML
    private void goLogin() {

        try {
            App.setRoot("/files/Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}