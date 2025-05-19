package model;

import controller.FarmacieController;
import controller.SectieController;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Random;
import java.util.random.*;

public class OrderUI {
    private final SectieController controller;

    public OrderUI(SectieController controller) {
        this.controller = controller;
    }

    public OrderUI(FarmacieController farmacieController) {
        this.controller = new SectieController();
    }

    public VBox createOrderPane() {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label medLabel = new Label("Medication:");
        TextField medField = new TextField();

        Label qtyLabel = new Label("Quantity:");
        TextField qtyField = new TextField();

        Button submitBtn = new Button("Submit Order");
        submitBtn.setOnAction(e -> {
            try {
                String medication = medField.getText();
                int quantity = Integer.parseInt(qtyField.getText());
                controller.trimiteComanda(medication, quantity);
                medField.clear();
                qtyField.clear();
                showAlert("Success", "Order submitted successfully!");
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid quantity!");
            }
        });

        grid.add(medLabel, 0, 0);
        grid.add(medField, 1, 0);
        grid.add(qtyLabel, 0, 1);
        grid.add(qtyField, 1, 1);
        grid.add(submitBtn, 0, 2, 2, 1);

        vbox.getChildren().addAll(new Label("Create New Medication Order"), grid);
        return vbox;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}