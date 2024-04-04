package be.kuleuven.candycrush.model;

import javafx.scene.paint.Color;

public record NormalCandy(Color kleur , int value) implements Candy {
    public NormalCandy {
        if (value >=0 && value < 4) {
            throw new IllegalArgumentException("Value must be between 1 and 5");
        }
    }
}
