package com.example.beadndo;

import com.oanda.v20.Config;
import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.InstrumentName;
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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.oanda.v20.instrument.CandlestickGranularity.H1;

public class HistorikusArakController implements Initializable {
    @FXML
    public Button mutat_button;
    @FXML
    public Button vissza_button;
    @FXML
    public ChoiceBox<String> devizaParok_choiceBox;
    @FXML
    public TextArea valasz_textarea;

    public void mutat_click(ActionEvent event) {
        Context ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("HistorikusAdatok").build();
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
        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instruments));
            request.setGranularity(H1);
            request.setCount(10L);    // 10 adat	L: long adattípus
            InstrumentCandlesResponse resp = ctx.instrument.candles(request);
            for (Candlestick candle : resp.getCandles())
                valasz_textarea.setText(String.valueOf(candle));
            for (Candlestick candle : resp.getCandles())
                valasz_textarea.appendText(candle.getTime() + "\t" + candle.getMid().getC());

        } catch (Exception e) {
            throw new RuntimeException(e);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        devizaParok_choiceBox.getItems().add("EUR - USD");
        devizaParok_choiceBox.getItems().add("USD - JPY");
        devizaParok_choiceBox.getItems().add("GBP - USD");
        devizaParok_choiceBox.getItems().add("USD - CHF");
    }
}
