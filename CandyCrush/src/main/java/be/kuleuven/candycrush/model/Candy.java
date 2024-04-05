package be.kuleuven.candycrush.model;

import javafx.scene.paint.Color;

public sealed interface Candy permits Drop, Lekstok, Napoleon, NormalCandy, Zuurtje {

    public static Candy getRandomCandy() {
        int random = (int) (Math.random() * 5);
        switch (random) {
            case 0:
                return new NormalCandy(0);
            case 1:
                return new NormalCandy(1);
            case 2:
                return new NormalCandy(2);
            case 3:
                return new NormalCandy(3);
            case 4:
                switch ((int) (Math.random() * 4)) {
                    case 0:
                        return new Drop();
                    case 1:
                        return new Lekstok();
                    case 2:
                        return new Napoleon();
                    default:
                        return new Zuurtje();
                }
            default:
                return new NormalCandy(0);
        }
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


}
