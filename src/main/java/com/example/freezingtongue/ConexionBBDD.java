package com.example.freezingtongue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConexionBBDD {
    public ConexionBBDD() {
    }

    public static Connection openConnection(String database, String user, String pwd) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(database, user, pwd);
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return con;
    }

    public static void mostrarMenuAdministrador(Event event, String ruta) {
        try {
            String rutafx = ruta + ".fxml";
            Parent root = (Parent)FXMLLoader.load((URL)Objects.requireNonNull(ConexionBBDD.class.getResource(rutafx)));
            Scene scene = new Scene(root);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static void mostrarAlertError(ActionEvent event, String msj) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText((String)null);
        alert.setTitle("Error");
        alert.setContentText(msj);
        alert.showAndWait();
    }
}
