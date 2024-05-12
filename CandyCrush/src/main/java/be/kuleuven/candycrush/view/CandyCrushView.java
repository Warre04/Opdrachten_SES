package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.Position;
import be.kuleuven.candycrush.model.Solution;
import javafx.scene.layout.Region;
import be.kuleuven.candycrush.model.CandyCrush;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;


public class CandyCrushView extends Region {
    private CandyCrush model;
    public CandyCrushView(CandyCrush model) {

        this.model = model;
        update();

    }


    public void update() {
        getChildren().clear();


        for(Map.Entry<Position, Candy> i: model.getBoard().getCells().entrySet()) {
            int x = i.getKey().x();
            int y = i.getKey().y();
            if (i.getValue() == null) {

            }
            else if (i.getValue().isSpecial()){
                Rectangle s = new Rectangle();
                s.setWidth((double) 300/model.getBoard().getBoardSize().breedte());
                s.setHeight((double) 300/model.getBoard().getBoardSize().hoogte());
                s.setX(x*s.getWidth());
                s.setY(y*s.getHeight());
                s.setFill(i.getValue().getColor());
                if(i.getKey().equals(model.getSelectedPosition())){
                    s.setStroke(Color.BLACK);
                    s.setStrokeWidth(2);
                }
                getChildren().add(s);
            }
                
            else {
                Ellipse ellipse = new Ellipse();
                ellipse.setRadiusY((double) 150/model.getBoard().getBoardSize().hoogte());
                ellipse.setRadiusX((double) 150/model.getBoard().getBoardSize().breedte());
                ellipse.setCenterX(ellipse.getRadiusX()+x*2*ellipse.getRadiusX());
                ellipse.setCenterY(ellipse.getRadiusX()+y*2*ellipse.getRadiusX());
                ellipse.setFill(i.getValue().getColor());
                if(i.getKey().equals(model.getSelectedPosition())){
                    ellipse.setStroke(Color.BLACK);
                    ellipse.setStrokeWidth(2);
                }
                getChildren().add(ellipse);
            }
        }
        model.findAllMatches(model.getBoard()).forEach(match -> {
            for (Position pos: match){
                Rectangle s = new Rectangle();
                s.setWidth((double) 300/model.getBoard().getBoardSize().breedte());
                s.setHeight((double) 300/model.getBoard().getBoardSize().hoogte());
                s.setX(pos.x()*s.getWidth());
                s.setY(pos.y()*s.getHeight());
                s.setFill(Color.TRANSPARENT);
                s.setStroke(Color.BLACK);
                s.setStrokeWidth(2);
                getChildren().add(s);
            }
        });
        displayPosibleSwaps();
        displayNextStepOptimalSolution();
    }
    private void displayNextStepOptimalSolution(){
        double Breedte= (double) 300 /model.getBoard().getBoardSize().breedte();
        double Hoogte= (double) 300 /model.getBoard().getBoardSize().hoogte();
        Solution opSol = model.getOptimalSolution();
        if (opSol !=null && !opSol.steps().isEmpty()){
            Position pos1 = opSol.steps().getFirst()[0];
            Position pos2 = opSol.steps().getFirst()[1];
            Ellipse e = new Ellipse(Breedte/2+pos1.x()*Breedte,Hoogte/2+pos1.y()*Hoogte,10,10);
            e.setFill(Color.WHITE);
            getChildren().add(e);
            Ellipse e2 = new Ellipse(Breedte/2+pos2.x()*Breedte,Hoogte/2+pos2.y()*Hoogte,10,10);
            e2.setFill(Color.WHITE);
            getChildren().add(e2);
        }
    }

    private void displayPosibleSwaps(){
        double Breedte= (double) 300 /model.getBoard().getBoardSize().breedte();
        double Hoogte= (double) 300 /model.getBoard().getBoardSize().hoogte();
        model.getPosibleSwaps(model.getBoard()).forEach(swap -> {
            if (swap[0].x() == swap[1].x()){//verticaal swap
                Rectangle s = new Rectangle(swap[0].x()*Breedte +Breedte/2-5,swap[0].y()*Hoogte+Hoogte-10,10,10);
                s.setFill(Color.BLACK);
                getChildren().add(s);
                Rectangle s2 = new Rectangle(swap[1].x()*Breedte +Breedte/2-5,swap[1].y()*Hoogte,10,10);
                s2.setFill(Color.BLACK);
                getChildren().add(s2);
            }
            else {
                Rectangle s = new Rectangle(swap[0].x()*Breedte +Breedte-10,swap[0].y()*Hoogte+Hoogte/2-5,10,10);
                s.setFill(Color.GRAY);
                getChildren().add(s);
                Rectangle s2 = new Rectangle(swap[1].x()*Breedte,swap[1].y()*Hoogte+Hoogte/2-5,10,10);
                s2.setFill(Color.GRAY);
                getChildren().add(s2);
            }
            });

    }



}
