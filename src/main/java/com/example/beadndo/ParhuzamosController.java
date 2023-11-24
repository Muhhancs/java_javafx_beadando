package com.example.beadndo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ParhuzamosController {
    public Button indit_button;
    public Label label2;
    public Label label1;


    public void indit_click(ActionEvent event) {
        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> label1.setText("Első feladat: 0")),
                new KeyFrame(Duration.seconds(1), e -> label1.setText("Első feladat: 1")),
                new KeyFrame(Duration.seconds(2), e -> label1.setText("Első feladat: 2")),
                new KeyFrame(Duration.seconds(3), e -> label1.setText("Első feladat: 3")),
                new KeyFrame(Duration.seconds(4), e -> label1.setText("Első feladat: 4")),
                new KeyFrame(Duration.seconds(5), e -> label1.setText("Első feladat: 5"))
        );
        timeline1.setCycleCount(10);


        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> label2.setText("Második feladat: 0")),
                new KeyFrame(Duration.seconds(2), e -> label2.setText("Második feladat: 1")),
                new KeyFrame(Duration.seconds(4), e -> label2.setText("Második feladat: 2")),
                new KeyFrame(Duration.seconds(6), e -> label2.setText("Második feladat: 3")),
                new KeyFrame(Duration.seconds(8), e -> label2.setText("Második feladat: 4")),
                new KeyFrame(Duration.seconds(10), e -> label2.setText("Második feladat: 5"))
        );
        timeline2.setCycleCount(5);

        timeline1.play();
        timeline2.play();
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
}
