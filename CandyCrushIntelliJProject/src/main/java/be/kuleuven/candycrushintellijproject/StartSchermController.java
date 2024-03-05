package be.kuleuven.candycrushintellijproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class StartSchermController {

    @FXML
    private AnchorPane paneel;

    @FXML
    private Button LogInButton;

    @FXML
    private TextField Name;

    public void initialize() {
        LogInButton.setOnAction(e -> {
            System.out.println("Hello " + Name.getText());
        });
    }
}
