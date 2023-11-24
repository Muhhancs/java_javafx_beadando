package com.example.beadndo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class netPizzaApplication extends Application {
    static HttpsURLConnection apiconnection;
    static String apitoken = "37cdfd4d37abacf7c0803a358c1fce131d125371e95fe4f88aac0bf3019b0abb";

    public static void main(String[] args) {
        launch();
    }

    /*static void GET(String ID) throws IOException {  // Get a list of users
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

    static void POST(String name, String gender, String email, String status) throws IOException {
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

    static void PUT(String ID, String name, String gender, String email, String status) throws IOException {
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

    static void DELETE(String ID) throws IOException {
        System.out.println("\nDELETE...");
        String url = "https://gorest.co.in/public/v1/users" + "/" + ID;
        URL postUrl = new URL(url);  // Url for making PUT request
        apiconnection = (HttpsURLConnection) postUrl.openConnection();
        apiconnection.setRequestMethod("DELETE");            // Set DELETE as request method
        segéd1();
        segéd3(HttpsURLConnection.HTTP_NO_CONTENT);
    }

    static void segéd3(int code) throws IOException {
        int statusCode = apiconnection.getResponseCode();   // Getting response code
        System.out.println("statusCode: " + statusCode);
        if (statusCode == code) {
            BufferedReader in = new BufferedReader(new InputStreamReader(apiconnection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null)   // Append response line by line
                jsonResponseData.append(readLine);
            in.close();
            System.out.println("List of users: " + jsonResponseData.toString());    // Print result in string format

        } else {
            System.out.println("Hiba!!!");
        }
        apiconnection.disconnect();
    }

    static void segéd2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(apiconnection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        apiconnection.connect();
    }

    private static void segéd1() {
        // Setting Header Parameters
        apiconnection.setRequestProperty("Content-Type", "application/json");
        apiconnection.setRequestProperty("Authorization", "Bearer " + apitoken);
        apiconnection.setUseCaches(false);
        apiconnection.setDoOutput(true);
    }*/

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(netPizzaApplication.class.getResource("fooldal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 350);
        stage.setTitle("Pizzanet");
        stage.setScene(scene);
        stage.show();
    }

}