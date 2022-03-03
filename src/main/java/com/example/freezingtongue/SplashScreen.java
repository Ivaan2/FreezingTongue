package com.example.freezingtongue;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreen implements Initializable {

    @FXML
    ImageView fondo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition transition = new FadeTransition(Duration.millis(3000), fondo);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    URL url = new File("src/main/java/com/example/freezingtongue/resources/login_screen.fxml").toURI().toURL();
                    Stage ventana = (Stage) fondo.getScene().getWindow();
                    ventana.hide();
                    Stage ventanaApp = new Stage();
                    Parent root = FXMLLoader.load(url);
                    Scene scene = new Scene(root,600,400);
                    ventanaApp.setScene(scene);
                    ventanaApp.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
