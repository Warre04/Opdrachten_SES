package be.kuleuven.candycrush;

import be.kuleuven.candycrush.model.Position;
import be.kuleuven.candycrush.model.Solution;
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
        private Button TestBTN;

        @FXML
        private Button resetBTN;

        @FXML
        private Label name;
        @FXML
        private Label Oscore;

        @FXML
        private Label score;

        public void initialize() {

            model = HelloApplication.getModel();

            view = new CandyCrushView(model);
            gridPaneel.getChildren().add(view);
            name.setText(model.getName());
            score.setText(model.getScore()+"");
            resetBTN.setOnAction(this::reset);
            TestBTN.setOnAction(this::test);

            gridPaneel.setOnMouseClicked(this::mouseClicked);
            model.updateBoard(this.model.getBoard());
            score.setText(model.getScore()+"");
            view.update();
        }

    private void mouseClicked(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX() / (300/model.getBoard().getBoardSize().breedte());
        int y = (int) mouseEvent.getY() / (300/model.getBoard().getBoardSize().hoogte());
        int index = y * model.getBoard().getBoardSize().breedte() + x;
        Position pos = Position.fromIndex(index, model.getBoard().getBoardSize());
        model.playerSwap(pos);
        model.updateBoard(this.model.getBoard());
        view.update();
        score.setText(model.getScore()+"");
    }

    private void reset(ActionEvent e) {
            model.reset();
            model.updateBoard(this.model.getBoard());
            Oscore.setText("0");
            score.setText(model.getScore()+"");
            view.update();

    }

    private void test(ActionEvent e) {
        Solution solution = model.findBestSolution();
        Oscore.setText(solution.score()+"");
        solution.steps().forEach(step ->{
            System.out.println("(r"+step[0].y() + ",c" + step[0].x() + ")<->(r" + step[1].y() + ",c" + step[1].x()+")");
        });
        view.update();
    }



}
