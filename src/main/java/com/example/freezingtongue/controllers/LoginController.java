package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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


    @FXML
    private void Login(ActionEvent event) {
        boolean ok = true;
        this.username = this.login_user.getText();
        this.password = this.login_pwd.getText();
        login_pwd.setText("");
        login_pwd.setText("");
        if (this.username.trim().equals("")) {
            ConexionBBDD.mostrarAlertError(event, "Usuario vacío.");
            ok = false;
        }
        if (this.password.trim().equals("")) {
            ConexionBBDD.mostrarAlertError(event, "Introduzca una contraseña.");
            ok = false;
        }
        if(ok){
            if (logUser(event, this.username, this.password)) {
                ConexionBBDD.mostrarMenuAdministrador(event, "config_helados");
            }else{
                ConexionBBDD.mostrarMenuAdministrador(event, "No se ha detectado tu cuenta en la base de datos.");
            }
        }
    }

    @FXML
    private void clicRegister(ActionEvent event) {
        ConexionBBDD.mostrarMenuAdministrador(event, "register_screen");
    }

    @FXML
    private void clicReset(ActionEvent event) {
        ConexionBBDD.mostrarMenuAdministrador(event, "reset_password");
    }

    private boolean logUser(ActionEvent event, String username, String password) {
        boolean great = false;

        String select = "SELECT nombre, contrasena FROM usuario WHERE nombre = ? and contrasena = ?;";
        Connection con = ConexionBBDD.openConnection();

        try {
            String db_name, db_pwd;
            PreparedStatement pst = con.prepareStatement(select);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                db_name = rs.getString("nombre");
                db_pwd = rs.getString("contrasena");
                if(username.equals(db_name) && password.equals(db_pwd)){
                    great = true;
                }
            }else{
                ConexionBBDD.mostrarAlertError(event, "Tu usuario no está registrado en nuestra base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return great;
    }
}