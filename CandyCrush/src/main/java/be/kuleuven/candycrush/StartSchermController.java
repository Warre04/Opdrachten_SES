package be.kuleuven.candycrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import be.kuleuven.candycrush.model.CandyCrush;

import java.io.IOException;

public class StartSchermController {
    private CandyCrush model;
    @FXML
    private AnchorPane paneel;

    @FXML
    private Button LogInButton;

    @FXML
    private TextField Name;

    public void initialize() {
        model = HelloApplication.getModel();
        LogInButton.setOnAction(this::logIn);
    }

    private void logIn(ActionEvent e) {
        model.setName(Name.getText());
        try {
            veranderScherm();
        } catch (IOException ioe) {
            System.out.println("Foutje! Kan CandyCrush.fxml niet oproepen.");
            ioe.printStackTrace();
        }
    }

    private void veranderScherm() throws IOException {
        HelloApplication.setRoot("CandyCrush");
    }
}




