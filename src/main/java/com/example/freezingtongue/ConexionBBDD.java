package com.example.freezingtongue;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConexionBBDD {
    public static Connection openConnection(String database, String user, String pwd){
        Connection con = null;
        try {
            con = DriverManager.getConnection(database, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void mostrarMenuAdministrador(Event event, String ruta) {
        try {
            String rutafx = ruta + ".fxml";
            Parent root = FXMLLoader.load(Objects.requireNonNull(ConexionBBDD.class.getResource(rutafx)));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
