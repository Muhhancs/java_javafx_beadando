package com.example.beadndo;


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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import static com.example.beadndo.SqliteConnection.Connector;
import static com.example.beadndo.netPizzaApplication.*;

public class IrController {
    public ConnectionModel connectionModel = new ConnectionModel();
    @FXML
    public TextField pizza_nev_textField;
    @FXML
    public TextField kategoria_nev_textField;
    @FXML
    public CheckBox vega_Button;
    @FXML
    public Button beszuras_Button;
    @FXML
    public Button vissza_button;
    @FXML
    public Button postapi_button;
    public TextArea restp_textArea;

    HttpsURLConnection apiconnection;
    String apitoken = "37cdfd4d37abacf7c0803a358c1fce131d125371e95fe4f88aac0bf3019b0abb";


    public void beszuras_click(ActionEvent event) {
        String query = "insert into pizza values (\"" + pizza_nev_textField.getText() + "\",\"" + kategoria_nev_textField.getText() + "\"," + vega_Button.isSelected() + ")";
        executeQuery(query);
        restp_textArea.setText("Pizza beszúrása az adatbázisba sikeres");
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

    public void postapi_click(ActionEvent event) throws IOException {
        POST("GAMF Imre", "male", "pizzafutar_example@data.hu", "active");

    }
    void POST(String name, String gender, String email, String status) throws IOException {
        System.out.println("\nPOST...");
        URL postUrl = new URL("https://gorest.co.in/public/v1/users");  // Url for making POST request
        apiconnection = (HttpsURLConnection) postUrl.openConnection();
        apiconnection.setRequestMethod("POST");            // Set POST as request method
        segéd1();
        // Adding Body payload for POST request
        String params = "{\"name\":\"" + name + "\", \"gender\":\"" + gender + "\", \"email\":\"" + email + "\", \"status\":\"" + status + "\"}";
        segéd2(params);
        segéd3(HttpsURLConnection.HTTP_CREATED);
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
            restp_textArea.setText("List of users: " + jsonResponseData.toString());
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
