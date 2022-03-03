package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class ResetController {
    private String username;
    private String pwd, pwd2;
    @FXML
    private TextField reset_user;
    @FXML
    private PasswordField reset_pwd;
    @FXML
    private PasswordField reset_pwd2;

    public ResetController() {
    }

    @FXML
    public void reset(ActionEvent event) {
        username = reset_user.getText();
        pwd = reset_pwd.getText();
        pwd2 = reset_pwd2.getText();
        reset_pwd.setText("");
        reset_pwd2.setText("");
        reset_user.setText("");

        boolean ok = true;

        if(username.trim().equals("")){
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "Introudzca un usuario.");
        }
        if(pwd.trim().equals("")){
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "Introudzca una contraseña.");
        }
        if(ok && pwd.equals(pwd2)){
            //It works
            int userId = userExists(event);
            if(userId != 0){
                if(changePwd(event, userId)){
                    ConexionBBDD.mostrarAlert(event, "Se ha cambiado la contraseña correctamente.");
                    ConexionBBDD.mostrarMenuAdministrador(event, "login_screen");
                }
            }
        }else{
            ok = false;
            ConexionBBDD.mostrarAlertError(event, "Las contraseñas no coinciden.");
        }
    }

    private boolean changePwd(ActionEvent event, int id) {
        boolean commit = false;
        String sql = "UPDATE usuario SET contrasena = ? WHERE idUsuario = ?";
        Connection con = ConexionBBDD.openConnection();

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, pwd);
            st.setInt(2, id);
            if(st.executeUpdate() != 0){
                commit = true;
            }else{
                ConexionBBDD.mostrarAlertError(event, "El ususario insertado no existe en nuestra base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commit;
    }

    private int userExists(ActionEvent event) {
        int id = 0;
        String sql = "SELECT idUsuario FROM usuario WHERE nombre = ?;";
        Connection con = ConexionBBDD.openConnection();
        ResultSet resultSet;

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            resultSet = st.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt("idUsuario");
            }else{
                ConexionBBDD.mostrarAlertError(event, "El ususario insertado no existe en nuestra base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @FXML
    public void reset_back(Event event){
        ConexionBBDD.mostrarMenuAdministrador(event, "login_screen");
    }
}
