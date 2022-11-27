module com.example.buttonedtextfield {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires json;


    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.buttonapplier;
    opens com.example.buttonapplier to javafx.fxml;
}