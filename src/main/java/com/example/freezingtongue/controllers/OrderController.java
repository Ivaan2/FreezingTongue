package com.example.freezingtongue.controllers;

import com.example.freezingtongue.ConexionBBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class OrderController {
    private float total;

    @FXML
    private Label pedido;

    @FXML
    private Label factura;


    public void obtenerDatosPedido() throws SQLException {
        Connection con = ConexionBBDD.openConnection();
        float total = obtenerPrecioTamano(con) + obtenerPrecioSabor(con) + obtenerPrecioTopping(con);

        pedido.setText(ConfigHelados.tamano + " de " + ConfigHelados.sabor + " con " + ConfigHelados.topping);
        factura.setText(total + "€");
        con.close();
    }

    @FXML
    public void confirmarPedido(ActionEvent event) throws SQLException {
        int idPedido = 0;
        Connection con = ConexionBBDD.openConnection();

        PreparedStatement pst = con.prepareStatement("SELECT max(pedidoId) from pedido");
        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            idPedido = rs.getInt(1);
        }

        PreparedStatement pstUpdate = con.prepareStatement("UPDATE pedido SET tamanoId = ?, saborId = ?, topId = ? WHERE pedidoId = ?");
        pstUpdate.setInt(1, obtenerIdTamano(con));
        pstUpdate.setInt(2, obtenerIdSabor(con));
        pstUpdate.setInt(3, obtenerIdTop(con));
        pstUpdate.setInt(4, idPedido);

        pstUpdate.executeUpdate();

        con.close();
        obtenerDatosPedido();
        ConexionBBDD.mostrarAlert(event, "¡Su pedido se ha procesado correctamente!");
    }

    private int obtenerIdTop(Connection con) throws SQLException {
        int idTop = 0;

        PreparedStatement pst = con.prepareStatement("SELECT topId FROM topping WHERE nombre=?");
        pst.setString(1, ConfigHelados.topping);

        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            idTop = rs.getInt(1);
        }

        return idTop;
    }

    private int obtenerIdSabor(Connection con) throws SQLException {
        int idSabor = 0;

        PreparedStatement pst = con.prepareStatement("SELECT saborId FROM sabor WHERE nombre=?");
        pst.setString(1, ConfigHelados.sabor);

        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            idSabor = rs.getInt(1);
        }

        return idSabor;
    }

    private int obtenerIdTamano(Connection con) throws SQLException {
        int idTamano = 0;

        PreparedStatement pst = con.prepareStatement("SELECT tamanoId FROM tamano WHERE nombre=?");
        pst.setString(1, ConfigHelados.tamano);

        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            idTamano = rs.getInt(1);
        }

        return idTamano;
    }

    private float obtenerPrecioTopping(Connection con) throws SQLException {
        float precioTop = 0;

        PreparedStatement pst = con.prepareStatement("SELECT precio FROM topping WHERE nombre=?");
        pst.setString(1, ConfigHelados.topping);

        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            precioTop = rs.getFloat(1);
        }

        return precioTop;
    }

    private float obtenerPrecioSabor(Connection con) throws SQLException {
        float precioSabor = 0;

        PreparedStatement pst = con.prepareStatement("SELECT precio FROM sabor WHERE nombre=?");
        pst.setString(1, ConfigHelados.sabor);

        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            precioSabor = rs.getFloat(1);
        }

        return precioSabor;
    }

    private float obtenerPrecioTamano(Connection con) throws SQLException {
        float precioTamano = 0;

        PreparedStatement pst = con.prepareStatement("SELECT precio FROM tamano WHERE nombre=?");
        pst.setString(1, ConfigHelados.tamano);

        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            precioTamano = rs.getFloat(1);
        }

        return precioTamano;
    }

    public void volver(ActionEvent event) {
        ConexionBBDD.mostrarMenuAdministrador(event, "config_helados");
    }

}
