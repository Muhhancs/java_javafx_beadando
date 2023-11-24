package com.example.beadndo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlgoritmusController {
    public Label label1;

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
        int classIndex = 34;
        new GépiTanulás2CrossValidation(fájlNév, classIndex, new J48());
        new GépiTanulás2CrossValidation(fájlNév, classIndex, new SMO());
        new GépiTanulás2CrossValidation(fájlNév, classIndex, new NaiveBayes());
        IBk classifier = new IBk();
        classifier.setOptions(Utils.splitOptions("-K 10"));
        new GépiTanulás2CrossValidation(fájlNév, classIndex, classifier);
        new GépiTanulás2CrossValidation(fájlNév, classIndex, new RandomForest());
        //kiir.close();
    }

    public class GépiTanulás2CrossValidation {
        GépiTanulás2CrossValidation(String fájlNév, int classIndex, Classifier classifier) throws FileNotFoundException {

            String filePath = "Gépi tanulás.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write("\nclassifier.getClass():" + classifier.getClass());
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fájlNév));
                    Instances instances = new Instances(bufferedReader);
                    instances.setClassIndex(classIndex);
                    if (false) instances.randomize(new Random());
                    Evaluation evaluation = new Evaluation(instances);
                    evaluation.crossValidateModel(classifier, instances, 10, new Random(1));
                    writer.write(evaluation.toSummaryString("\nResults", false));
                    writer.write("Correctly Classified Instances:" + (int) evaluation.correct() + "\t" + 100 * evaluation.correct() / instances.size() + "%");
                    writer.write("Incorrectly Classified Instances:" + (instances.size() - (int) evaluation.correct()));
                    writer.newLine();
                    writer.newLine();
                    writer.newLine();
                    label1.setText("Az adatok kiírva a Gépi tanulás.txt fájlba.");
                } catch (Exception e) {
                    System.out.println("Error Occurred!!!! \n" + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("Hiba történt a fájl hozzáfűzésekor: " + e.getMessage());
            }
        }
    }
}