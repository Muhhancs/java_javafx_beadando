package com.example.beadndo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.beadndo.SqliteConnection.Connector;
import static com.example.beadndo.netPizzaApplication.*;

public class TorolController implements Initializable {
    public ConnectionModel connectionModel = new ConnectionModel();
    @FXML
    public Button torles_button;
    @FXML
    public ChoiceBox azonosito_choiceBox;
    @FXML
    public Button vissza_button;
    @FXML
    public Button restd_button;
    public TextArea restd_textField;
    @FXML
    private Label isConnected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (connectionModel.isDbConnected()) {
            getOsszAzonosito();
        } else {

        }
    }

    public void torles_click(ActionEvent event) {
        String query = "delete from rendeles where az = " + (int) azonosito_choiceBox.getValue();
        executeQuery(query);
        isConnected.setText("Rendelés törlés sikeres");
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
    public void restd_click(ActionEvent event) throws IOException {
        String ID = "5711470";
        DELETE(ID);
    }

    void DELETE(String ID) throws IOException {
        System.out.println("\nDELETE...");
        String url = "https://gorest.co.in/public/v1/users" + "/" + ID;
        URL postUrl = new URL(url);  // Url for making PUT request
        apiconnection = (HttpsURLConnection) postUrl.openConnection();
        apiconnection.setRequestMethod("DELETE");            // Set DELETE as request method
        segéd1();
        segéd3(HttpsURLConnection.HTTP_NO_CONTENT);
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
            restd_textField.setText("5711470 ID-jű felhasználó törölve");

        } else {
            System.out.println("Hiba!!!");
        }
        apiconnection.disconnect();
    }
    private void segéd1() {
        // Setting Header Parameters
        apiconnection.setRequestProperty("Content-Type", "application/json");
        apiconnection.setRequestProperty("Authorization", "Bearer " + apitoken);
        apiconnection.setUseCaches(false);
        apiconnection.setDoOutput(true);
    }
}
