package com.example.csc325_firebase_webview_auth.view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {

        String email = emailField.getText();
        String password = passwordField.getText();

        System.out.println("Login attempt: " + email);

        // Later you can connect Firebase login here
    }

    @FXML
    private void goRegister() {

        try {
            App.setRoot("/files/Register.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

