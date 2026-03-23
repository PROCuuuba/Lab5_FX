module com.example.lab5_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires telegrambots.meta;
    requires telegrambots.client;
    requires telegrambots.longpolling;

    opens com.example.lab5_fx to javafx.fxml;
    exports com.example.lab5_fx;
}