package be.kuleuven.CandyCrush.model;
import be.kuleuven.candycrush.model.BoardSize;
import be.kuleuven.candycrush.model.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardSizeTest {
    @Test
    public void TestBoardSizeConstructorWithNegativeWidth() {
        try {
            BoardSize boardSize = new BoardSize(-1, 10);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
    @Test
    public void TestBoardSizeConstructorWithNegativeHeight() {
        try {
            BoardSize boardSize = new BoardSize(10, -1);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
    @Test
    public void TestBoardSizeConstructorWithPositiveWidthAndHeight() {
        BoardSize boardSize = new BoardSize(10, 10);
        assert boardSize.breedte() == 10;
        assert boardSize.hoogte() == 10;
    }
    @Test
    public void TestBoardSizePositions() {
        BoardSize boardSize = new BoardSize(2, 2);
        ArrayList<Position> positions= (ArrayList<Position>) boardSize.positions();
        assert positions.size() == 4;
        assert positions.get(0).equals(new Position(0, 0, boardSize));
        assert positions.get(1).equals(new Position(1, 0, boardSize));
        assert positions.get(2).equals(new Position(0, 1, boardSize));
        assert positions.get(3).equals(new Position(1, 1, boardSize));
    }
}
