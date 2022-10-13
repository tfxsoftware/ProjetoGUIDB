module com.tfxsoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires mongo.java.driver;
    requires com.fasterxml.jackson.databind;
    opens com.tfxsoftware to javafx.fxml;
    exports com.tfxsoftware;
}