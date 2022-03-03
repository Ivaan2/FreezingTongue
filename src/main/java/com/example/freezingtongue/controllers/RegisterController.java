package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {

    //ATRIBUTOS REGISTER
    @FXML
    private TextField register_user;
    @FXML
    private TextField register_pwd;
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
    private void register_button(Event event){
        if(register_user.equals("") || !checkPassword(register_pwd)){
            System.out.println("Inserte usuario y contraseñas correctas.");
        }else{
            ConexionBBDD.mostrarMenuAdministrador(event, "login_screen");
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
}
