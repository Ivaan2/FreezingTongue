package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    String username, pwd, adress;
    //ATRIBUTOS REGISTER
    @FXML
    private TextField register_user;
    @FXML
    private PasswordField register_pwd;
    @FXML
    private TextField register_adress;

    private String suscripcion;

    //METODOS REGISTER
    @FXML
    private void register_suscription_yes(Event event){
        suscripcion = "Básico";
    }

    @FXML
    private void register_suscription_no(Event event){
        suscripcion = "Premium";
    }

    @FXML
    private void register(ActionEvent event){
        boolean ok = true;
        username = register_user.getText();
        pwd = register_pwd.getText();
        adress = register_adress.getText();

        if(username.trim().equals("")) {
            ConexionBBDD.mostrarAlertError(event, "Inserte un usuario.");
            ok = false;
        }
        if(pwd.trim().equals("")){
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "Inserte una contraseña.");
        }else if(!checkPassword(register_pwd)){
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "Contraseña demasiado corta.");
        }

        if(adress.trim().equals("")){
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "Introduzca una dirección.");
        }
        if(suscripcion.equals("")){
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "No se ha detectado ninguna suscripción.");
        }
        if(ok){
            if(registerUser(event)){
                ConexionBBDD.mostrarAlert(event, "Registrado con éxito.");
                ConexionBBDD.mostrarMenuAdministrador(event, "login_screen");
            }
        }
    }

    private boolean registerUser(ActionEvent event) {
        boolean commit = false;
        Connection con = ConexionBBDD.openConnection();
        String sql = "INSERT INTO usuario (nombre, contrasena, direccion, suscripcion) VALUES (?, ?, ?, ?);";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, pwd);
            pst.setString(3, adress);
            pst.setString(4, suscripcion);
            if(pst.executeUpdate() != 0){
                commit = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commit;
    }

    private boolean checkPassword(TextField register_pwd) {
        return pwd.length() > 6;
    }

    @FXML
    public void register_back(ActionEvent event){
        ConexionBBDD.mostrarMenuAdministrador(event, "login_screen");
    }
}
