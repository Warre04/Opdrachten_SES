package be.kuleuven.candycrush.model;


public record NormalCandy(int Color) implements Candy {
    public NormalCandy {
        if (Color < 0 || Color > 3) {
            throw new IllegalArgumentException("Value must be 0,1,2 or 3");
        }
    }
}
