package com.example.myticket.tambah;

import com.example.myticket.Main;
import com.example.myticket.connection.SqliteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TambahKursi{

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public TextField kursi = new TextField();

    public void submitData (ActionEvent event) throws IOException {
        Main main = new Main();
        connection = SqliteConnection.Connector();
        String query = "insert into tb_kursi (kursi) values (?)";
        String validate = "select * from tb_kursi where kursi = ?";
        try {
            if(kursi.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Form Tidak Boleh Kosong !");
                alert.showAndWait();
            } else {
                var validation = connection.prepareStatement(validate);
                validation.setString(1, kursi.getText());
                resultSet = validation.executeQuery();
                if (resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Kursi yang anda masukkan telah terdaftar !");
                    alert.showAndWait();
                } else {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, kursi.getText());
                    preparedStatement.execute();
                    preparedStatement.close();
                    connection.close();
                    main.changeScene("admin/AdminKursi.fxml");
                }
                resultSet.close();
                validation.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    public void tiket (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("admin/AdminKereta.fxml");
    }

    @FXML
    public void stasiun (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("admin/AdminStasiun.fxml");
    }

    @FXML
    public void gerbong (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("admin/AdminGerbong.fxml");
    }

    @FXML
    public void kursi (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("admin/AdminKursi.fxml");
    }

    @FXML
    public void user (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("admin/AdminUser.fxml");
    }

    @FXML
    public void keluar (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("Login.fxml");
    }

    @FXML
    public void batal (ActionEvent event) throws IOException {
        Main logout = new Main();
        logout.changeScene("admin/AdminKursi.fxml");
    }

}