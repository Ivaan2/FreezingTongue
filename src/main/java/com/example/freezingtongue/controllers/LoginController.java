package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public String username;
    private String password;
    @FXML
    public TextField login_user;
    @FXML
    private PasswordField login_pwd;


    @FXML
    private void Login(ActionEvent event) throws SQLException {
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
                int idUsuario = 0;
                Connection con = ConexionBBDD.openConnection();

                PreparedStatement pst = con.prepareStatement("SELECT idUsuario FROM usuario WHERE nombre=? AND contrasena=?");
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()){
                    idUsuario = rs.getInt(1);
                }

                PreparedStatement pstInsert = con.prepareStatement("INSERT INTO pedido (idUsuario) VALUES (?)");
                pstInsert.setInt(1, idUsuario);

                pstInsert.executeUpdate();

                con.close();

                ConexionBBDD.mostrarMenuAdministrador(event, "config_helados");
            }else{
                ConexionBBDD.mostrarAlertError(event, "No se ha detectado tu cuenta en la base de datos, regístrate.");
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

    private boolean logUser(ActionEvent event, String username, String password) throws SQLException {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        con.close();

        return great;
    }
}