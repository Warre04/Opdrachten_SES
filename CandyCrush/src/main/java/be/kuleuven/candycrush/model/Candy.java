package be.kuleuven.candycrush.model;

import javafx.scene.paint.Color;

public sealed interface Candy permits Drop, Lekstok, Napoleon, NormalCandy, Zuurtje {


    public static Candy getRandomCandy() {
        int random = (int) (Math.random() * 10);
        switch (random) {
            case 0:
                return getRandomSpecialCandy();
            default:
                int randomNormal= (int) (Math.random() * 4);
                return new NormalCandy(randomNormal);
        }
    }

    public static Candy GetNormalCandy(int color) {
        return new NormalCandy(color);

    }
    public static Candy getRandomSpecialCandy(){
        return switch ((int) (Math.random() * 4)) {
            case 0 -> new Drop();
            case 1 -> new Lekstok();
            case 2 -> new Napoleon();
            default -> new Zuurtje();
        };
    }

    public default Color getColor(){
        return switch (this) {
            case NormalCandy normalCandy -> switch (normalCandy.Color()) {
                case 0 -> Color.RED;
                case 1 -> Color.BLUE;
                case 2 -> Color.GREEN;
                case 3 -> Color.YELLOW;
                default -> Color.BLACK;
            };
            case Drop drop -> Color.PURPLE;
            case Lekstok lekstok -> Color.ORANGE;
            case Napoleon napoleon -> Color.PINK;
            case Zuurtje zuurtje -> Color.CYAN;
            default -> Color.BLACK;
        };
    }
    public default boolean isSpecial() {
        return this instanceof Drop || this instanceof Lekstok || this instanceof Napoleon || this instanceof Zuurtje;
    }

    public static Candy charToCandy(char c){
        return switch(c) {
            case 'o' -> new NormalCandy(0);
            case '*' -> new NormalCandy(1);
            case '#' -> new NormalCandy(2);
            case '@' -> new NormalCandy(3);
            case 'N' -> null;
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
    }


}
