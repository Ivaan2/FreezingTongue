package com.example.freezingtongue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfigHelados {
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
    protected void btnAddCarrito() {
        tTamaño.getText();
        tSabor.getText();
        tTopping.getText();
    }
}
