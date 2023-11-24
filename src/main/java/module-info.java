module com.example.beadndo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    requires httpclient;
    requires gson;
    requires httpcore;
    requires java.xml.bind;
    requires java.jws;
    requires java.xml.ws;
    requires weka.stable;
    //requires javax.xml.ws;

    opens com.oanda.v20.account to gson;
    opens com.oanda.v20.primitives to gson;
    opens com.oanda.v20.transaction to gson;
    opens com.example.beadndo to javafx.fxml;
    opens com.oanda.v20.pricing;
    opens com.oanda.v20.pricing_common;
    opens com.oanda.v20.order;
    opens com.oanda.v20.instrument;
    opens com.oanda.v20.trade;
    exports com.oanda.v20.primitives;
    exports com.oanda.v20.transaction;
    exports com.oanda.v20.pricing_common;
    exports com.oanda.v20.order;
    exports com.oanda.v20.trade;

    exports com.example.beadndo;
}