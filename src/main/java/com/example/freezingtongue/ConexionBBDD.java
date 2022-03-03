package com.example.freezingtongue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
    public ConexionBBDD() {
    }

    public static Connection openConnection() {
        Connection con = null;
        String database = "jdbc:mysql://localhost:3306/freezing_tongue";
        String user = "root";
        String pwd = "";
        try {
            con = DriverManager.getConnection(database, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void mostrarMenuAdministrador(Event event, String ruta) {
        Stage appStage = null;
        Scene scene = null;

        try {
            Parent root = (Parent)FXMLLoader.load(new File("src/main/java/com/example/freezingtongue/resources/" + ruta + ".fxml").toURI().toURL());
            scene = new Scene(root);
            appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ruta.equals("config_helados")){
            appStage.setY(100);
            appStage.setX(500);
        }
        else if (ruta.equals("orderResume")){
            appStage.setY(600);
        }
        appStage.setScene(scene);
        appStage.toFront();
        appStage.setResizable(false);
        appStage.show();
    }

    public static void mostrarAlertError(ActionEvent event, String msj) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText((String)null);
        alert.setTitle("Error");
        alert.setContentText(msj);
        alert.showAndWait();
    }

    public static void mostrarAlert(ActionEvent event, String msj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText((String)null);
        alert.setTitle("Mensaje");
        alert.setContentText(msj);
        alert.showAndWait();
    }
}
