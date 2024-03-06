package be.kuleuven.candycrush;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import be.kuleuven.candycrush.model.CandyCrush;

import java.io.IOException;

public class HelloApplication extends Application {
    private static CandyCrush model;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        model = new CandyCrush();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StartScherm.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Candy Crush!");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
    }




    public static void main(String[] args) {
        launch();
    }

    public static CandyCrush getModel() {
        return model;
    }
}