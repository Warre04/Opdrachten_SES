package be.kuleuven.candycrush.model;

public record BoardSize(int breedte, int hoogte) {
    public BoardSize {
        if (breedte < 0 || hoogte < 0) {
            throw new IllegalArgumentException("Board too small");
        }
    }
    Iterable<Position> positions()
    {
        // TODO
        //een methode Iterable<Position> positions()
        //die alle posities op het bord teruggeeft volgens oplopende index.
        return null;
    }
}
