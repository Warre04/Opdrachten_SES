package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.Position;
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
            if (i.getValue().isSpecial()){
                Rectangle s = new Rectangle();
                s.setWidth((double) 300/model.getWidth());
                s.setHeight((double) 300/model.getHeight());
                s.setX(x*s.getWidth());
                s.setY(y*s.getHeight());
                s.setFill(i.getValue().getColor());
                getChildren().add(s);
            }
                
            else {
                Ellipse ellipse = new Ellipse();
                ellipse.setRadiusY((double) 150/model.getHeight());
                ellipse.setRadiusX((double) 150/model.getWidth());
                ellipse.setCenterX(ellipse.getRadiusX()+x*2*ellipse.getRadiusX());
                ellipse.setCenterY(ellipse.getRadiusX()+y*2*ellipse.getRadiusX());
                ellipse.setFill(i.getValue().getColor());
                getChildren().add(ellipse);
            }

        }
    }


}
