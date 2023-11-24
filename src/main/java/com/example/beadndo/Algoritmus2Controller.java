package com.example.beadndo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.Utils;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Algoritmus2Controller implements Initializable {
    public Label label1;
    public ChoiceBox<String> algoritmus_choiceBox;
    public Label label2;
    public Label label3;
    public Label label4;


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

    public void mutat_click(ActionEvent event) throws Exception {
        String fájlNév = "data/ionosphere.arff";
        int classIndex=34;

        Classifier algorithm = null;
        if (Objects.equals(algoritmus_choiceBox.getValue(), "J48")) {
            algorithm = new J48();
        }
        if (Objects.equals(algoritmus_choiceBox.getValue(), "SMO")) {
            algorithm = new SMO();
        }
        if (Objects.equals(algoritmus_choiceBox.getValue(), "NaiveBayes")) {
            algorithm = new NaiveBayes();
        }
        if (Objects.equals(algoritmus_choiceBox.getValue(), "RandomForest")) {
            algorithm = new RandomForest();
        }
        if (Objects.equals(algoritmus_choiceBox.getValue(), "IBk")) {
            IBk classifier = new IBk();
            classifier.setOptions(Utils.splitOptions("-K 10"));
            algorithm = classifier;
        }

        new GépiTanulás2CrossValidation(fájlNév, classIndex, algorithm);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algoritmus_choiceBox.getItems().add("J48");
        algoritmus_choiceBox.getItems().add("SMO");
        algoritmus_choiceBox.getItems().add("NaiveBayes");
        algoritmus_choiceBox.getItems().add("RandomForest");
        algoritmus_choiceBox.getItems().add("IBk");
    }

    public class GépiTanulás2CrossValidation {
        GépiTanulás2CrossValidation(String fájlNév, int classIndex, Classifier classifier) throws FileNotFoundException {

            //String filePath = "Gépi tanulás.txt";

            //try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                label1.setText("\nclassifier.getClass():" + classifier.getClass());
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fájlNév));
                    Instances instances = new Instances(bufferedReader);
                    instances.setClassIndex(classIndex);
                    if (false) instances.randomize(new Random());
                    Evaluation evaluation = new Evaluation(instances);
                    evaluation.crossValidateModel(classifier, instances, 10, new Random(1));
                    label2.setText(evaluation.toSummaryString("\nResults", false));
                    label3.setText("Correctly Classified Instances:" + (int) evaluation.correct() + "\t" + 100 * evaluation.correct() / instances.size() + "%");
                    label4.setText("Incorrectly Classified Instances:" + (instances.size() - (int) evaluation.correct()));
                    //label1.setText("Az adatok kiírva a Gépi tanulás.txt fájlba.");
                } catch (Exception e) {
                    System.out.println("Error Occurred!!!! \n" + e.getMessage());
                }
        }
    }
}
