package org.example.issfarmacielab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PharmacyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                PharmacyApp.class.getResource("/org/example/issfarmacielab3/hello-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Pharmacy App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}