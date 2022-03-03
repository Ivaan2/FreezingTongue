package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfigHelados {
    private String tamano, sabor, topping;

    @FXML
    private Label tTamaño;
    @FXML
    private Label tSabor;
    @FXML
    private Label tTopping;

    //TAMAÑO HELADO
    @FXML
    protected void btnTamGrande() {
        tTamaño.setText("Grande");
    }
    @FXML
    protected void btnTamMediano() {
        tTamaño.setText("Mediano");
    }
    @FXML
    protected void btnTamPeque() {
        tTamaño.setText("Pequeño");
    }

    //SABOR HELADO
    @FXML
    protected void btnSabFresa() {
        tSabor.setText("Fresa");
    }
    @FXML
    protected void btnSabVainilla() {
        tSabor.setText("Vainilla");
    }
    @FXML
    protected void btnSabChocolate() {
        tSabor.setText("Chocolate");
    }
    @FXML
    protected void btnSabTurron() {
        tSabor.setText("Turron");
    }
    @FXML
    protected void btnSabNata() {
        tSabor.setText("Nata");
    }
    @FXML
    protected void btnSabStra() {
        tSabor.setText("Stracciatella");
    }

    //TOPPINGS
    @FXML
    protected void btnTopGalleta() {
        tTopping.setText("Galleta");
    }
    @FXML
    protected void btnTopOreo() {
        tTopping.setText("Oreo");
    }
    @FXML
    protected void btnTopLacasitos() {
        tTopping.setText("Lacasitos");
    }

    //BOTON PARA AÑADIR AL CARRITO/COMPRAR
    @FXML
    public void btnAddCarrito(ActionEvent event) {
        String sql = "SELECT max(heladoId) from helado h;";
        tamano = tTamaño.getText();
        sabor = tSabor.getText();
        topping = tTopping.getText();
        ConexionBBDD.mostrarMenuAdministrador(event, "orderResume");
    }
}
