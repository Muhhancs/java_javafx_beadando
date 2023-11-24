package com.example.beadndo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.beadndo.SqliteConnection.Connector;

public class OlvasController implements Initializable {
    @FXML
    public TableView<osszesites> olvas_tablazat;
    @FXML
    public TableColumn<osszesites, Integer> azonosito_column;
    @FXML
    public TableColumn<osszesites, String> pizza_nev_column;
    @FXML
    public TableColumn<osszesites, Integer> mennyiseg_column;
    @FXML
    public TableColumn<osszesites, String> felvetel_column;
    @FXML
    public TableColumn<osszesites, String> kiszallitas_column;
    @FXML
    public TableColumn<osszesites, String> kategoria_column;
    @FXML
    public TableColumn<osszesites, Boolean> vega_column;
    @FXML
    public TableColumn<osszesites, Integer> ar_column;
    @FXML
    public Button vissza_button;
    @FXML
    public ConnectionModel connectionModel = new ConnectionModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (connectionModel.isDbConnected()) {
            showAdat();
        } else {

        }
    }

    public ObservableList<osszesites> getOsszAdat() {
        ObservableList<osszesites> osszadatLista = FXCollections.observableArrayList();
        Connection connection = Connector();
        String query = "select * from kategoria inner join pizza on kategoria.nev = pizza.kategorianev inner join rendeles on pizza.nev = rendeles.pizzanev";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            osszesites mindenadat;
            while (rs.next()) {
                mindenadat = new osszesites(rs.getString("pizzanev"), rs.getString("kategorianev"), rs.getBoolean("vegetarianus"), rs.getInt("az"), rs.getInt("darab"), rs.getString("felvetel"), rs.getString("kiszallitas"), rs.getInt("ar"));
                osszadatLista.add(mindenadat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return osszadatLista;
    }

    public void showAdat() {
        ObservableList<osszesites> list = getOsszAdat();

        azonosito_column.setCellValueFactory(new PropertyValueFactory<osszesites, Integer>("az"));
        pizza_nev_column.setCellValueFactory(new PropertyValueFactory<osszesites, String>("pizzanev"));
        mennyiseg_column.setCellValueFactory(new PropertyValueFactory<osszesites, Integer>("darab"));
        felvetel_column.setCellValueFactory(new PropertyValueFactory<osszesites, String>("felvetel"));
        kiszallitas_column.setCellValueFactory(new PropertyValueFactory<osszesites, String>("kiszallitas"));
        kategoria_column.setCellValueFactory(new PropertyValueFactory<osszesites, String>("kategorianev"));
        vega_column.setCellValueFactory(new PropertyValueFactory<osszesites, Boolean>("vegetarianus"));
        ar_column.setCellValueFactory(new PropertyValueFactory<osszesites, Integer>("ar"));

        olvas_tablazat.setItems(list);
    }

    public void vissza_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fooldal-view.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 750, 350);
            Stage stage = new Stage();
            stage.setTitle("Netpizza");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


}
