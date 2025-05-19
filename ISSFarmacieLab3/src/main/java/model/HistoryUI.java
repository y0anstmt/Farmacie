package model;

import controller.SectieController;
import entitate.Comanda;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class HistoryUI {
    private final SectieController controller;

    public HistoryUI(SectieController controller) {
        this.controller = controller;
    }

    public VBox createHistoryPane() {
        VBox vbox = new VBox(10);
        ListView<String> historyListView = new ListView<>();

        controller.getIstoricComenzi().forEach(order ->
                historyListView.getItems().add("Order: " + order.getMedicament() + " - " + order.getCantitate())
        );

        vbox.getChildren().addAll(new javafx.scene.control.Label("Order History"), historyListView);
        return vbox;
    }
}