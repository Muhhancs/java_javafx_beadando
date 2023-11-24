package com.example.beadndo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

public class FaController {
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

    public void mutat_click(ActionEvent event) {
        String fájlNév = "data/ionosphere.arff";
        int classIndex = 34;
        new GépiTanulás1(fájlNév, classIndex);
    }

    public class GépiTanulás1 {
        GépiTanulás1(String fájlNév, int classIndex) {
            try {
                PrintWriter kiir = new PrintWriter("Döntési fa.txt");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fájlNév));
                Instances instances = new Instances(bufferedReader);
                //System.out.println("instances.size():" + instances.size());
                instances.setClassIndex(classIndex);
                Instances tanító, kiértékelő;
                J48 classifier;
                Evaluation evaluation;
                classifier = new J48();
                //kiir.println("\nGépiTanulás1: Randomize után vagy anélkül: tanító: első 90%, kiértékelő: utolsó 10%"); kiir.close();
                if (false) instances.randomize(new Random());
                tanító = new Instances(instances, 0, 9 * instances.size() / 10);
                kiir.println("tanító.size():" + tanító.size());
                kiir.println("");
                kiértékelő = new Instances(instances, 9 * instances.size() / 10, instances.size() / 10);
                kiir.println("kiértékelő.size():" + kiértékelő.size());
                kiir.println("");
                classifier.buildClassifier(tanító);
                evaluation = new Evaluation(kiértékelő);
                double[] eredmeny = evaluation.evaluateModel(classifier, kiértékelő);
                //kiir.println(evaluation.toSummaryString("\nResults", false));
                //kiir.println("");
                kiir.println("Correctly Classified Instances:" + (int) evaluation.correct());
                kiir.println("");
                kiir.println("Incorrectly Classified Instances:" + (kiértékelő.size() - (int) evaluation.correct()));
                kiir.println("");
                //System.out.println(classifier.toString());
                kiir.println(classifier.toString());

                kiir.println("\nGépiTanulás1: Vizsgálat részletesen, egyesével:");
                kiir.println("");
                int TP = 0, TN = 0, FP = 0, FN = 0;
                //  TP:TtruePositive, TN:TrueNegative, FP:FalsePositive, FN:FalseNegative
                for (int i = 0; i < kiértékelő.size(); i++) {
                    kiir.println(i + "\t" + (((Instances) kiértékelő).get(i)).classValue() + "\t" + eredmeny[i]);
                    if ((((Instances) kiértékelő).get(i)).classValue() == 1 && eredmeny[i] == 1)
                        TP++;
                    if ((((Instances) kiértékelő).get(i)).classValue() == 1 && eredmeny[i] == 0)
                        FN++;
                    if ((((Instances) kiértékelő).get(i)).classValue() == 0 && eredmeny[i] == 1)
                        FP++;
                    if ((((Instances) kiértékelő).get(i)).classValue() == 0 && eredmeny[i] == 0)
                        TN++;
                }
                kiir.println("");
                kiir.println("TP=" + TP + ", " + "TN=" + TN + ", " + "FP=" + FP + ", " + "FN=" + FN);
                kiir.close();
                //kiir.println("\n");System.out.println("TP+TN="+(TP+TN));
                //kiir.println("\n");System.out.println("FP+FN="+(FP+FN)); kiir.close();
// Ennek nincs gyakorlati haszna:
                // System.out.println("\nGépiTanulás1: ha a tanító és kiértékelő megegyezik:");
                tanító = new Instances(instances, 0, instances.size());
                kiértékelő = new Instances(instances, 0, instances.size());
                classifier.buildClassifier(tanító);
                evaluation = new Evaluation(kiértékelő);
                evaluation.evaluateModel(classifier, kiértékelő);
                //System.out.println(evaluation.toSummaryString("\nResults", false));
                label1.setText("Az adatok kiírva a Döntési Fa.txt fájlba.");
            } catch (Exception e) {
                System.out.println("Error Occurred!!!! \n" + e.getMessage());
            }
        }
    }
}
