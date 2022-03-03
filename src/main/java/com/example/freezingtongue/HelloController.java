package com.example.freezingtongue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;

public class HelloController {


    private String username;
    private String password;

    @FXML
    private TextField login_user;
    @FXML
    private TextField login_pwd;

    @FXML
    private void Login(ActionEvent event){
        this.username = login_user.getText();
        this.password = login_pwd.getText();
        if(checkCredentials(username, password)){

        }
        System.out.println("Usuario " + username + " Contraseña: " + password);
        mostrarMenuAdministrador(event);
    }

    private boolean checkCredentials(String username, String password) {
        String database = "jdbc:mysql://localhost:3306/freezing_tongue";
        String user = "root";
        String pwd = "";
        Connection con;
        return true;
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