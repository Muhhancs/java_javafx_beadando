package com.example.beadndo;

import com.oanda.v20.Context;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.primitives.AccountUnits;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.transaction.TransactionID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SzamlainfoController implements Initializable {


    @FXML
    public TableColumn<szamlainfo, AccountID> id_column;
    @FXML
    public TableColumn<szamlainfo, String> alias_column;
    @FXML
    public TableColumn<szamlainfo, Currency> currency_column;
    @FXML
    public TableColumn<szamlainfo, AccountUnits> balance_column;
    @FXML
    public TableColumn<szamlainfo, Long> created_column;
    @FXML
    public TableColumn<szamlainfo, DateTime> created_time_column;
    @FXML
    public TableColumn<szamlainfo, Long> open_trade_column;
    @FXML
    public TableColumn<szamlainfo, TransactionID> last_trans_column;
    @FXML
    public TableView<szamlainfo> szamlainfo_tablazat;

    @FXML
    public Button vissza_button;

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
        Context ctx = new Context("https://api-fxpractice.oanda.com", "de7895a23b01d7cc4c8896d1139e706b-2daacfbab11b921a924b461a8e2a7438");
        try {
            AccountSummary summary = ctx.account.summary(new AccountID("101-004-27326966-001")).getAccount();
            szamlainfo account = new szamlainfo(summary.getId(), summary.getAlias(), summary.getBalance(), summary.getCreatedByUserID(), summary.getCurrency(), summary.getCreatedTime(), summary.getLastTransactionID(), summary.getOpenTradeCount());

            id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
            alias_column.setCellValueFactory(new PropertyValueFactory<>("alias"));
            currency_column.setCellValueFactory(new PropertyValueFactory<>("currency"));
            balance_column.setCellValueFactory(new PropertyValueFactory<>("balance"));
            created_column.setCellValueFactory(new PropertyValueFactory<>("createdByUserId"));
            created_time_column.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
            open_trade_column.setCellValueFactory(new PropertyValueFactory<>("openTradeCount"));
            last_trans_column.setCellValueFactory(new PropertyValueFactory<>("lastTransactionID"));

            szamlainfo_tablazat.getItems().addAll(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
