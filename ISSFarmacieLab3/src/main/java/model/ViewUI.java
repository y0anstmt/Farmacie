package model;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ViewUI {
    public VBox createViewPane() {
        VBox vbox = new VBox(10);

        TextField viewTextField = new TextField();
        viewTextField.setPromptText("Enter something...");

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String input = viewTextField.getText();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Please enter something!");
            } else {
                System.out.println("You entered: " + input);
            }
        });

        vbox.getChildren().addAll(viewTextField, submitBtn);
        return vbox;
    }
}