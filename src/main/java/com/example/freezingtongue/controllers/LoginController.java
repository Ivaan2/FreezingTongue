package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private String username;
    private String password;
    @FXML
    private TextField login_user;
    @FXML
    private PasswordField login_pwd;

    public LoginController() {
    }

    @FXML
    private void Login(ActionEvent event) {
        this.username = this.login_user.getText();
        this.password = this.login_pwd.getText();
        if (this.username.trim().equals("")) {
            ConexionBBDD.mostrarAlertError(event, "Usuario vacío.");
        } else if (this.password.trim().equals("")) {
            ConexionBBDD.mostrarAlertError(event, "Introduzca una contraseña.");
        } else if (this.checkCredentials(this.username, this.password)) {
            ConexionBBDD.mostrarMenuAdministrador(event, "config_helados");
        } else {
            ConexionBBDD.mostrarAlertError(event, "Datos incorrectos..");
        }

    }

    @FXML
    private void clicRegister(Event event) {
        ConexionBBDD.mostrarMenuAdministrador(event, "register_screen");
    }

    @FXML
    private void clicReset(Event event) {
        ConexionBBDD.mostrarMenuAdministrador(event, "reset_password");
    }

    private boolean checkCredentials(String username, String password) {
        boolean great = false;
        String database = "jdbc:mysql://localhost:3306/freezing_tongue";
        String user = "root";
        String pwd = "";
        String select = "SELECT nombre, contrasena FROM usuario WHERE nombre = ? and contrasena = ?;";
        Connection con = ConexionBBDD.openConnection(database, user, pwd);

        try {
            PreparedStatement pst = con.prepareStatement(select);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                great = true;
                con.commit();
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        }

        return great;
    }
}