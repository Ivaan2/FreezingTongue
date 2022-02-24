package com.example.freezingtongue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {


    private String valorUsuario;
    private String contrasenna;

    @FXML
    private TextField loginUsuario;
    @FXML
    private TextField loginContrasenna;

    @FXML
    private void Login(ActionEvent event){
        this.valorUsuario = loginUsuario.getText();
        this.contrasenna = loginContrasenna.getText();
        System.out.println("Usuario " + valorUsuario + " Contraseña: " + contrasenna);
        mostrarMenuAdministrador(event);
    }
    @FXML
    public void mostrarMenuAdministrador(Event event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("reset_password.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();

        } catch (Exception e) {
        }
    }
}