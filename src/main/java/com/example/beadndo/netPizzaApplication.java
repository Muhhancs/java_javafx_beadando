package com.example.beadndo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class netPizzaApplication extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(netPizzaApplication.class.getResource("fooldal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 350);
        stage.setTitle("Pizzanet");
        stage.setScene(scene);
        stage.show();
    }

}