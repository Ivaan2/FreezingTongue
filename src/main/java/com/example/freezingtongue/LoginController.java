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
import javafx.stage.Stage;
import java.sql.Connection;

public class LoginController {

    private String username, password;
    //ATRIBUTOS LOGIN
    @FXML
    private TextField login_user;
    @FXML
    private TextField login_pwd;
    @FXML
    private Label login_msg;


    //METODOS LOGIN
    @FXML
    private void Login(ActionEvent event){
        this.username = login_user.getText();
        this.password = login_pwd.getText();
        if(checkCredentials(username, password)){
            //Si usuario y contraseña son correctos
            //pasa a config_helados
            ConexionBBDD.mostrarMenuAdministrador(event, "config_helados");
        }else{
            //usuario y contraseñas incorrectas
            login_msg.setVisible(true);
        }
    }

    @FXML
    private void clicRegister(Event event){
        ConexionBBDD.mostrarMenuAdministrador(event, "register_screen");
    }


    @FXML
    private void clicReset(Event event){
        ConexionBBDD.mostrarMenuAdministrador(event, "reset_password");
    }

    //METODOS GLOBALES
    private boolean checkCredentials(String username, String password) {
        String database = "jdbc:mysql://localhost:3306/freezing_tongue";
        String user = "root";
        String pwd = "";
        Connection con = ConexionBBDD.openConnection(database, user, pwd);
        return true;
    }
}