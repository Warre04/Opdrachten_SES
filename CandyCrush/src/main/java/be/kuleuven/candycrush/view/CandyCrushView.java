package be.kuleuven.candycrush.view;

import javafx.scene.layout.Region;
import be.kuleuven.candycrush.model.CandyCrush;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;


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
        for(int i : model.getGrid()) {
            Ellipse ellipse = new Ellipse();
            ellipse.setRadiusY((double) 150/model.getHeight());
            ellipse.setRadiusX((double) 150/model.getWidth());
            ellipse.setCenterX(ellipse.getRadiusX()+x*2*ellipse.getRadiusX());
            ellipse.setCenterY(ellipse.getRadiusX()+y*2*ellipse.getRadiusX());
            ellipse.setFill(getColor(i));
            getChildren().add(ellipse);
            x++;
            if(x == model.getWidth()) {
                x = 0;
                y++;
            }
        }
    }
    public Color getColor(int i) {
        return switch (i) {
            case 1 -> Color.RED;
            case 2 -> Color.BLUE;
            case 3 -> Color.GREEN;
            case 4 -> Color.YELLOW;
            case 5 -> Color.PURPLE;
            default -> null;
        };
    }

}
