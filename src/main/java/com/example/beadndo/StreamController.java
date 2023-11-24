package com.example.beadndo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.beadndo.SqliteConnection.Connector;

public class StreamController implements Initializable {
    public TextField pizza_nev_textField;
    public ChoiceBox<Integer> mennyiseg_ChoiceBox;
    public CheckBox vega_checkBox;
    public RadioButton aprod_radioButton;
    public RadioButton kiraly_radioButton;
    public RadioButton fonemes_radioButton;
    public RadioButton lovag_radioButton;
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
    public TableView<osszesites> stream_tablazat;

    public void vissza_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fooldal-view.fxml"));
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

    public void keres_click(ActionEvent event) {
        String kategoria = "";
        if (aprod_radioButton.isSelected()) {
            kategoria = "apród";
        }
        if (kiraly_radioButton.isSelected()) {
            kategoria = "király";
        }
        if (lovag_radioButton.isSelected()) {
            kategoria = "lovag";
        }
        if (fonemes_radioButton.isSelected()) {
            kategoria = "főnemes";
        }
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


        String finalKategoria = kategoria;
        List<osszesites> osszadatLista2 = null;
        osszadatLista2 = osszadatLista.stream().filter(p -> Objects.equals(p.getKategorianev(), finalKategoria)).filter(a -> Objects.equals(a.getPizzanev(), pizza_nev_textField.getText())).filter(c -> c.isVegetarianus() == vega_checkBox.isSelected()).filter(b -> b.getDarab() == mennyiseg_ChoiceBox.getValue()).collect(Collectors.toList());

        azonosito_column.setCellValueFactory(new PropertyValueFactory<>("az"));
        pizza_nev_column.setCellValueFactory(new PropertyValueFactory<>("pizzanev"));
        mennyiseg_column.setCellValueFactory(new PropertyValueFactory<>("darab"));
        felvetel_column.setCellValueFactory(new PropertyValueFactory<>("felvetel"));
        kiszallitas_column.setCellValueFactory(new PropertyValueFactory<>("kiszallitas"));
        kategoria_column.setCellValueFactory(new PropertyValueFactory<>("kategorianev"));
        vega_column.setCellValueFactory(new PropertyValueFactory<>("vegetarianus"));
        ar_column.setCellValueFactory(new PropertyValueFactory<>("ar"));

        stream_tablazat.setItems(FXCollections.observableArrayList(osszadatLista2));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mennyiseg_ChoiceBox.getItems().add(1);
        mennyiseg_ChoiceBox.getItems().add(2);
        mennyiseg_ChoiceBox.getItems().add(3);
        ToggleGroup radioGroup = new ToggleGroup();
        aprod_radioButton.setToggleGroup(radioGroup);
        kiraly_radioButton.setToggleGroup(radioGroup);
        fonemes_radioButton.setToggleGroup(radioGroup);
        lovag_radioButton.setToggleGroup(radioGroup);
    }
}
