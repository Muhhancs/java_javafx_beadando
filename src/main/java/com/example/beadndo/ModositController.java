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
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.beadndo.SqliteConnection.Connector;
import static com.example.beadndo.netPizzaApplication.*;

public class ModositController implements Initializable {
    public ConnectionModel connectionModel = new ConnectionModel();
    @FXML
    public ChoiceBox<Integer> azonosito_choiceBox;
    @FXML
    public TextField pizza_nev_textField;
    @FXML
    public TextField mennyiseg_textField;
    @FXML
    public TextField felvetel_textField;
    @FXML
    public TextField kiszallitas_textField;
    @FXML
    public Button modositas_button;

    @FXML
    public Button vissza_button;
    @FXML
    public Button putapi_button;
    @FXML
    public TextArea restp_textarea;

    HttpsURLConnection apiconnection;
    String apitoken = "37cdfd4d37abacf7c0803a358c1fce131d125371e95fe4f88aac0bf3019b0abb";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (connectionModel.isDbConnected()) {

            getOsszAzonosito();
        } else {

        }
    }

    public void modosit_click(ActionEvent event) {
        String query = "update rendeles set pizzanev = '" + pizza_nev_textField.getText() + "', darab = '" + mennyiseg_textField.getText() + "', felvetel = '" + felvetel_textField.getText() + "', kiszallitas = '" + kiszallitas_textField.getText() + "'" + " Where az = " + (int) azonosito_choiceBox.getValue();
        executeQuery(query);
        restp_textarea.setText("Rendelés módosítás sikeres");
    }

    private void executeQuery(String query) {
        Connection connection = Connector();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getOsszAzonosito() {
        ArrayList<Integer> osszAzLista = new ArrayList<>();
        Connection connection = Connector();
        String query = "select az from rendeles";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            int azonositok;
            while (rs.next()) {
                azonositok = rs.getInt("az");
                osszAzLista.add(azonositok);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int var : osszAzLista) {
            azonosito_choiceBox.getItems().add(var);
        }
        return osszAzLista;
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

    public void putapi_click(ActionEvent event) throws IOException {
        String ID = "5772998";
        PUT(ID, "Horváth János2", "male", "email3_example_gamf@data.hu", "active");
        GET(ID);
    }

    void PUT(String ID, String name, String gender, String email, String status) throws IOException {
        System.out.println("\nPUT...");
        String url = "https://gorest.co.in/public/v1/users" + "/" + ID;
        URL postUrl = new URL(url);  // Url for making PUT request
        apiconnection = (HttpsURLConnection) postUrl.openConnection();
        apiconnection.setRequestMethod("PUT");            // Set PUT as request method
        segéd1();
        String params = "{\"name\":\"" + name + "\", \"gender\":\"" + gender + "\", \"email\":\"" + email + "\", \"status\":\"" + status + "\"}";   // Adding Body payload for POST request
        segéd2(params);
        segéd3(HttpsURLConnection.HTTP_OK);
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
        //System.out.println("statusCode: " + statusCode);
        if (statusCode == code) {
            BufferedReader in = new BufferedReader(new InputStreamReader(apiconnection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null)   // Append response line by line
                jsonResponseData.append(readLine);
            in.close();
            restp_textarea.setText("List of users: " + jsonResponseData.toString());    // Print result in string format
        } else {
            System.out.println("Hiba!!!");
        }
        apiconnection.disconnect();
    }

    void segéd2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(apiconnection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        apiconnection.connect();
    }

    private void segéd1() {
        // Setting Header Parameters
        apiconnection.setRequestProperty("Content-Type", "application/json");
        apiconnection.setRequestProperty("Authorization", "Bearer " + apitoken);
        apiconnection.setUseCaches(false);
        apiconnection.setDoOutput(true);
    }
}
