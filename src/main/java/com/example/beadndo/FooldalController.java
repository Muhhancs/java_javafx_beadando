package com.example.beadndo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FooldalController implements Initializable {
    public ConnectionModel connectionModel = new ConnectionModel();

    @FXML
    public Button olvas_button;
    @FXML
    public Button olvas2_button;
    @FXML
    public Button ir_button;
    @FXML
    public Button modosit_button;
    @FXML
    public Button torol_button;
    @FXML
    public Button fa_button;
    @FXML
    public Button algoritmus_button;
    @FXML
    public Button algoritmus2_button;
    @FXML
    public Button parhuzamos_button;
    @FXML
    public Button stream_button;
    @FXML
    public Button info_button;
    @FXML
    public Button arak_button;
    @FXML
    public Button tortenet_button;
    @FXML
    public Button nyitas_button;
    @FXML
    public Button zaras_button;
    public Button nyitott_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (connectionModel.isDbConnected()) {

        } else {

        }
    }

    public void olvas_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("olvas-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Olvas");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void olvas2_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("olvas2-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Olvas2");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void torol_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("torol-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 285);
            Stage stage = new Stage();
            stage.setTitle("Rendelés törlés");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void ir_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ir-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 172);
            Stage stage = new Stage();
            stage.setTitle("Pizza beszúrás");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void modosit_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("modosit-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 350);
            Stage stage = new Stage();
            stage.setTitle("Rendelés módosítás");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void fa_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fa-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage stage = new Stage();
            stage.setTitle("Adatbányászat - Fa");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void algoritmus_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("algoritmus-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage stage = new Stage();
            stage.setTitle("Adatbányászat - Algoritmus");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void algoritmus2_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("algoritmus2-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 400);
            Stage stage = new Stage();
            stage.setTitle("Adatbányászat - Algoritmus2");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void parhuzamos_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("parhuzamos-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            Stage stage = new Stage();
            stage.setTitle("Párhuzamos");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void stream_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("stream-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Stream");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void info_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("szamlainfo-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 150);
            Stage stage = new Stage();
            stage.setTitle("Számla információk");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void arak_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("aktualis-arak-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 200);
            Stage stage = new Stage();
            stage.setTitle("Aktuális deviza árak");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void tortenet_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("historikus-arak-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 200);
            Stage stage = new Stage();
            stage.setTitle("Historikus deviza árak");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void nyitas_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("nyitas-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 250);
            Stage stage = new Stage();
            stage.setTitle("Pozíció Nyitás");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void zaras_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("zaras-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 350);
            Stage stage = new Stage();
            stage.setTitle("Pozíció Zárás");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void nyitott_click(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("nyitott-poziciok-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 250);
            Stage stage = new Stage();
            stage.setTitle("Nyitott Pozíciók");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}