package com.example.freezingtongue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;

public class HelloController {

    //DISCODURODEROER <-- Tarea Dam 2 Disco

    //AATRIBUTOS GLOBALES
    private String username;
    private String password;

    //ATRIBUTOS REGISTER
    @FXML
    private TextField register_user;
    @FXML
    private TextField register_pwd;
    @FXML
    private TextField register_adress;

    private String suscripcion;

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
            mostrarMenuAdministrador(event, "config_helados");
        }else{
            //usuario y contraseñas incorrectas
            login_msg.setVisible(true);
        }
    }

    @FXML
    private void clicRegister(Event event){
        mostrarMenuAdministrador(event, "register_screen");
    }


    @FXML
    private void clicReset(Event event){
        mostrarMenuAdministrador(event, "reset_password");
    }

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
    private void register_button(Event event){
        if(register_user.equals("") || !checkPassword(register_pwd)){
            System.out.println("Inserte usuario y contraseñas correctas.");
        }else{
            mostrarMenuAdministrador(event, "login_screen");
        }
    }

    private boolean checkPassword(TextField register_pwd) {
        boolean pwd_ok = false;
        if (register_pwd.getLength() < 6) {
            pwd_ok = true;
        }
        else{
            System.out.println("Contraseña demasiado corta.");
        }
        return pwd_ok;
    }


    //METODOS GLOBALES
    private boolean checkCredentials(String username, String password) {
        String database = "jdbc:mysql://localhost:3306/freezing_tongue";
        String user = "root";
        String pwd = "";
        Connection con;
        return true;
    }

    @FXML
    public void mostrarMenuAdministrador(Event event, String ruta) {
        try {
            String rutafx = ruta + ".fxml";
            Parent root = FXMLLoader.load(getClass().getResource(rutafx));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();

        } catch (Exception e) {
        }
    }
}