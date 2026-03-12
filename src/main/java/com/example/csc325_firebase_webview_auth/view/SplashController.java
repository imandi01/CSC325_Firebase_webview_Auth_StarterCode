package com.example.csc325_firebase_webview_auth.view;

import javafx.application.Platform;

public class SplashController {

    public void initialize() {

        new Thread(() -> {

            try {

                Thread.sleep(3000);

                Platform.runLater(() -> {

                    try {
                        App.setRoot("/files/AccessFBView.fxml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

    }
}