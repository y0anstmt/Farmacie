module org.example.issfarmacielab3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.issfarmacielab3 to javafx.fxml;
    exports org.example.issfarmacielab3;
    exports controller;
    exports entitate;
    exports service;
}