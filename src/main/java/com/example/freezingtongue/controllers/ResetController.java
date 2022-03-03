package com.example.freezingtongue.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetController {
    private String username;
    private String password;
    @FXML
    private TextField reset_user;
    @FXML
    private PasswordField reset_pwd;
    @FXML
    private PasswordField reset_pwd2;
    @FXML
    private Label reset_msg;

    public ResetController() {
    }

    @FXML
    public void reset(ActionEvent event) {
    }
}
