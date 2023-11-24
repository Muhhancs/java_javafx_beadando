package com.example.beadndo;

import com.oanda.v20.Config;
import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AktualisArakController implements Initializable {
    @FXML
    public Button mutat_button;
    @FXML
    public Button vissza_button;
    @FXML
    public ChoiceBox<String> devizaParok_choiceBox;
    @FXML
    public TextArea valasz_textarea;

    public void mutat_click(ActionEvent event) {
        try {
            valasz_textarea.setText("");
            Context ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("PricePolling").build();
            AccountID accountId = Config.ACCOUNTID;
            String instruments = "valami";
            if (Objects.equals(devizaParok_choiceBox.getValue(), "EUR - USD")) {
                instruments = "EUR_USD";
            }
            if (Objects.equals(devizaParok_choiceBox.getValue(), "USD - JPY")) {
                instruments = "USD_JPY";

            }
            if (Objects.equals(devizaParok_choiceBox.getValue(), "GBP - USD")) {
                instruments = "GBP_USD";

            }
            if (Objects.equals(devizaParok_choiceBox.getValue(), "USD - CHF")) {
                instruments = "USD_CHF";
            }

            PricingGetRequest request = new PricingGetRequest(accountId, Collections.singleton(instruments));
            PricingGetResponse resp = ctx.pricing.get(request);
            for (ClientPrice price : resp.getPrices())
                valasz_textarea.setText(devizaParok_choiceBox.getValue() + " : " + price.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

          /*  PricingGetRequest request = new PricingGetRequest(accountId, instruments);
            DateTime since = null;
            while (true) {
                if (since != null)
                {
                    legutobbi_label.setText("Polling since " + since);
                    request.setSince(since);
                }
                PricingGetResponse resp = ctx.pricing.get(request);
                for (ClientPrice price : resp.getPrices())
                    arfolyam_label.setText(String.valueOf(price));
                since = resp.getTime();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        devizaParok_choiceBox.getItems().add("EUR - USD");
        devizaParok_choiceBox.getItems().add("USD - JPY");
        devizaParok_choiceBox.getItems().add("GBP - USD");
        devizaParok_choiceBox.getItems().add("USD - CHF");
    }
}
