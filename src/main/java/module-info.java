module com.example.checksumgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.checksumgui to javafx.fxml;
    exports com.example.checksumgui;
}