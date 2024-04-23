package be.kuleuven.candycrush;

import be.kuleuven.candycrush.model.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import be.kuleuven.candycrush.model.CandyCrush;
import be.kuleuven.candycrush.view.CandyCrushView;

public class CandyCrushController {

        private CandyCrush model;
        private CandyCrushView view;

        @FXML
        private AnchorPane gridPaneel;

        @FXML
        private Button resetBTN;

        @FXML
        private Label name;

        @FXML
        private Label score;

        public void initialize() {
            model = HelloApplication.getModel();
            view = new CandyCrushView(model);
            gridPaneel.getChildren().add(view);
            name.setText(model.getName());
            score.setText(model.getScore()+"");
            resetBTN.setOnAction(this::reset);
            gridPaneel.setOnMouseClicked(this::mouseClicked);
        }

    private void mouseClicked(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX() / (300/model.getBoard().getBoardSize().breedte());
        int y = (int) mouseEvent.getY() / (300/model.getBoard().getBoardSize().hoogte());
        int index = y * model.getBoard().getBoardSize().breedte() + x;


        model.removeCandy(Position.fromIndex(index, model.getBoard().getBoardSize()));
        view.update();
        score.setText(model.getScore()+"");
    }

    private void reset(ActionEvent e) {
            model.reset();
            view.update();
            score.setText(model.getScore()+"");
    }


}
