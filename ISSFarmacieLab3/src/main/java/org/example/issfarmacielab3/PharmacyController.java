package org.example.issfarmacielab3;

import controller.FarmacieController;
import entitate.Comanda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class PharmacyController {

    @FXML
    private TextField infoTextField;

    @FXML
    private TextArea infoTextArea;

    @FXML
    private TextField medicationField;

    @FXML
    private TextField quantityField;

    @FXML
    private ListView<String> ordersListView;

    @FXML
    private ListView<String> historyListView;

    @FXML
    private TextField viewTextField;

    @FXML
    private Label viewResultLabel;

    @FXML
    private Label orderDetailsLabel;

    private final FarmacieController farmacieController = new FarmacieController();
    private Comanda selectedOrder;

    @FXML
    public void initialize() {
        handleRefreshList();
        handleRefreshHistory();

        ordersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String medication = newValue.split(":")[1].split("-")[0].trim();
                int quantity = Integer.parseInt(newValue.split("-")[1].trim());
                selectedOrder = farmacieController.findOrder(medication, quantity);
                orderDetailsLabel.setText("Selected Order: " + medication + " - Quantity: " + quantity);
            }
        });
    }

    @FXML
    private void handleSubmitOrder() {
        String medication = medicationField.getText();
        String quantity = quantityField.getText();
        if (medication.isEmpty() || quantity.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }
        try {
            int qty = Integer.parseInt(quantity);
            int id = farmacieController.generateRandomId();
            Comanda comanda = new Comanda(id, medication, qty);
            farmacieController.adaugaComanda(comanda);
            System.out.println("Order added successfully.");
            medicationField.clear();
            quantityField.clear();
            handleRefreshList();
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a number.");
        }
    }

    @FXML
    private void handleFulfillOrder() {
        if (selectedOrder == null) {
            System.out.println("Please select an order to fulfill.");
            return;
        }
        farmacieController.onoreazaComanda(selectedOrder);
        selectedOrder = null;
        orderDetailsLabel.setText("Selected Order: None");
        handleRefreshList();
        handleRefreshHistory();
    }

    @FXML
    private void handleRefreshList() {
        ordersListView.getItems().clear();
        farmacieController.getComenziNeonorate().forEach(comanda ->
                ordersListView.getItems().add("Order: " + comanda.getMedicament() + " - " + comanda.getCantitate())
        );
        System.out.println("Orders list refreshed.");
    }

    @FXML
    private void handleRefreshHistory() {
        historyListView.getItems().clear();
        farmacieController.getIstoricComenzi().forEach(comanda ->
                historyListView.getItems().add("Order: " + comanda.getMedicament() + " - " + comanda.getCantitate() +
                        " (" + (comanda.isOnorata() ? "Onorată" : "Neonorată") + ")")
        );
        System.out.println("History list refreshed.");
    }

    @FXML
    private void handleViewSubmit() {
        String medication = viewTextField.getText();
        if (medication.isEmpty()) {
            viewResultLabel.setText("Please enter a medication name.");
            return;
        }
        int quantity = farmacieController.cautaMedicament(medication);
        if (quantity >= 0) {
            viewResultLabel.setText("Remaining stock of " + medication + ": " + quantity);
        } else {
            viewResultLabel.setText("Medication not found.");
        }
    }

    @FXML
    private void handleExit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    @FXML
    public void handleLoadInfo(ActionEvent actionEvent) {
        String keyword = infoTextField.getText().trim();
        if (keyword.isEmpty()) {
            infoTextArea.setText("Please enter a keyword.");
            return;
        }

        List<String> medications = farmacieController.searchMedicationsByKeyword(keyword);
        if (medications.isEmpty()) {
            infoTextArea.setText("No medications found for the keyword: " + keyword);
        } else {
            infoTextArea.setText("Medications for \"" + keyword + "\":\n" + String.join(", ", medications));
        }
    }
}