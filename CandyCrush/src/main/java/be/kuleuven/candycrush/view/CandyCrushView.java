package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.model.Candy;
import javafx.scene.layout.Region;
import be.kuleuven.candycrush.model.CandyCrush;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class CandyCrushView extends Region {
    private CandyCrush model;
    public CandyCrushView(CandyCrush model) {
        this.model = model;
        update();
    }
    public void update() {
        getChildren().clear();
        int x = 0;
        int y = 0;
        for(Candy i : model.getGrid()) {

            if (i.isSpecial()){
                Rectangle s = new Rectangle();
                s.setWidth((double) 300/model.getWidth());
                s.setHeight((double) 300/model.getHeight());
                s.setX(x*s.getWidth());
                s.setY(y*s.getHeight());
                s.setFill(i.getColor());
                getChildren().add(s);
            }
            else {
                Ellipse ellipse = new Ellipse();
                ellipse.setRadiusY((double) 150/model.getHeight());
                ellipse.setRadiusX((double) 150/model.getWidth());
                ellipse.setCenterX(ellipse.getRadiusX()+x*2*ellipse.getRadiusX());
                ellipse.setCenterY(ellipse.getRadiusX()+y*2*ellipse.getRadiusX());
                ellipse.setFill(i.getColor());
                getChildren().add(ellipse);
            }
            x++;
            if(x == model.getWidth()) {
                x = 0;
                y++;
            }
        }
    }


}
