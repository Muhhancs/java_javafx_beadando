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

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.beadndo.SqliteConnection.Connector;
import static com.example.beadndo.netPizzaApplication.*;

public class Olvas2Controller implements Initializable {
    public ConnectionModel connectionModel = new ConnectionModel();
    @FXML
    public TableView<osszesites> olvas2_tablazat;
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
    public TextField pizza_nev_textField;
    @FXML
    public ChoiceBox<Integer> mennyiseg_ChoiceBox;
    @FXML
    public CheckBox vega_checkBox;
    @FXML
    public RadioButton aprod_radioButton;
    @FXML
    public RadioButton kiraly_radioButton;
    @FXML
    public RadioButton fonemes_radioButton;
    @FXML
    public RadioButton lovag_radioButton;

    @FXML
    public Button keres_Button;
    @FXML
    public Button vissza_button;
    @FXML
    public Button getapi_button;
    @FXML
    public TextArea restg_textArea;

    HttpsURLConnection apiconnection;
    String apitoken = "37cdfd4d37abacf7c0803a358c1fce131d125371e95fe4f88aac0bf3019b0abb";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (connectionModel.isDbConnected()) {
            ToggleGroup radioGroup = new ToggleGroup();
            aprod_radioButton.setToggleGroup(radioGroup);
            kiraly_radioButton.setToggleGroup(radioGroup);
            fonemes_radioButton.setToggleGroup(radioGroup);
            lovag_radioButton.setToggleGroup(radioGroup);
            getOsszMennyiseg();
        } else {

        }
    }

    public void handledButtonAction(ActionEvent event) {
        showAdat();
    }

    public void getOsszMennyiseg() {
        ArrayList<Integer> osszMennyisegLista = new ArrayList<>(new HashSet<>());
        Connection connection = Connector();
        String query = "select darab from rendeles";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            int mennyiseg;
            while (rs.next()) {
                mennyiseg = rs.getInt("darab");
                osszMennyisegLista.add(mennyiseg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashSet<Integer> hashSetNumbers
                = new HashSet<Integer>(osszMennyisegLista);

        for (int var : hashSetNumbers) {
            mennyiseg_ChoiceBox.getItems().add(var);
        }
    }

    public ObservableList<osszesites> getOsszAdat() {
        String kategoria = null;
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
        String query = "select * from kategoria inner join pizza on kategoria.nev = pizza.kategorianev inner join rendeles on pizza.nev = rendeles.pizzanev where pizzanev = \"" + pizza_nev_textField.getText() + "\" and darab = " + mennyiseg_ChoiceBox.getValue() + " and vegetarianus = " + vega_checkBox.isSelected() + " and kategoria.nev = \"" + kategoria + "\"";
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

        olvas2_tablazat.setItems(list);
    }

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

    public void getapi_click(ActionEvent event) throws IOException {
        String ID = "5772998";
        GET(ID);
    }

    void GET(String ID) throws IOException {  // Get a list of users
        System.out.println("\nGET...");
        String url = "https://gorest.co.in/public/v1/users";
        if (ID != null)
            url = url + "/" + ID;
        URL usersUrl = new URL(url); // Url for making GET request
        apiconnection = (HttpsURLConnection) usersUrl.openConnection();
        apiconnection.setRequestMethod("GET");  // Set request method
        if (ID != null)
            apiconnection.setRequestProperty("Authorization", "Bearer " + apitoken);
        segéd3(HttpsURLConnection.HTTP_OK);
    }

    void segéd3(int code) throws IOException {
        int statusCode = apiconnection.getResponseCode();   // Getting response code
        System.out.println("statusCode: " + statusCode);
        if (statusCode == code) {
            BufferedReader in = new BufferedReader(new InputStreamReader(apiconnection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null)   // Append response line by line
                jsonResponseData.append(readLine);
            in.close();
            //System.out.println("List of users: " + jsonResponseData.toString());    // Print result in string format
            restg_textArea.setText("List of users: " + jsonResponseData.toString());
        } else {
            System.out.println("Hiba!!!");
        }
        apiconnection.disconnect();
    }
}