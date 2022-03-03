package com.example.freezingtongue.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreen implements Initializable {

    @FXML
    private AnchorPane splashScreenPane;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ImageView fondo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                int time = 0;

                while (time <= 100){
                    Thread.sleep(500);

                    if (time < 90) {
                        progressBar.setProgress(time * 0.01);
                    }
                    else {
                        progressBar.setProgress(1);
                    }
                    time += 10;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new File("src/main/java/com/example/freezingtongue/resources/login_screen.fxml").toURI().toURL();

                        Stage splash = (Stage) fondo.getScene().getWindow();
                        splash.hide();

                        Stage nuevaVentana = new Stage();
                        Parent root = FXMLLoader.load(url);
                        Scene scene = new Scene(root);

                        nuevaVentana.setScene(scene);
                        nuevaVentana.show();

                        splashScreenPane.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }).start();
    }
}
