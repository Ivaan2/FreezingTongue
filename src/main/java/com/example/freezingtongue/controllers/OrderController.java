package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.w3c.dom.events.Event;

public class OrderController {

    @FXML
    private Label pedido;

    @FXML
    private Label factura;

    @FXML
    public void order(ActionEvent event){
        ConexionBBDD.mostrarAlert(event, "Su pedido se está procesando...");
    }

}
