package be.kuleuven.candycrush.model;

public record Position(int x, int y,BoardSize board) {
// TODO testen schrijven voor de record Position
    public Position {
        if ((x < 0) || (y < 0) || (x >= board.breedte()) || (y >= board.hoogte())) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
    }
    int toIndex() {
        return y * board.breedte() + x;
    }

    static Position fromInde(int index, BoardSize board) {
        return new Position(index % board.breedte(), index / board.breedte(), board);
    }
    Iterable<Position> neighborPositions()
    {
        // TODO
        //een methode Iterable<Position> neighborPositions()
        //die alle posities van (bestaande) directe buren in het speelveld teruggeeft.
        return null;
    }
    boolean isLastInRow() {
        return x == board.breedte() - 1;
    }
}
