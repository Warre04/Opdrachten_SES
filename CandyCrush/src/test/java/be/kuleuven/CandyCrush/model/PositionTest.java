package be.kuleuven.CandyCrush.model;
import be.kuleuven.candycrush.model.BoardSize;
import org.junit.jupiter.api.Test;
import be.kuleuven.candycrush.model.Position;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {

    @Test
    public void TestPositionConstructorWithInvalidX() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(-1, 0, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidY() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(0, -1, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndY() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(-1, -1, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndYAndBoard() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(-1, -1, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndYAndBoard2() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(10, 10, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndYAndBoard3() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(10, 0, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndYAndBoard4() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(0, 10, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndYAndBoard5() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(0, 10, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionConstructorWithInvalidXAndYAndBoard6() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Position(10, 0, new BoardSize(10, 10)));
    }
    @Test
    public void TestPositionToIndex() {
        Position position = new Position(1, 1, new BoardSize(4, 4));
        assert position.toIndex() == 5;
    }
    @Test
    public void TestPositionFromIndex() {
        Position position = Position.fromIndex(6, new BoardSize(4, 4));
        assert position.x() == 2;
        assert position.y() == 1;
    }
    @Test
    public void TestPositionFromIndex2() {

        try {
            Position position = Position.fromIndex(16, new BoardSize(4, 4));
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
    }


    @Test
    public void TestPositionNeighborPositionsXis0Yis0() {
        Position position = new Position(0, 0, new BoardSize(4, 4));
        ArrayList<Position> neighborPositions = (ArrayList<Position>) position.neighborPositions();
        assert neighborPositions.size() == 3;
        assert neighborPositions.get(0).equals(new Position(0, 1, new BoardSize(4, 4)));
        assert neighborPositions.get(1).equals(new Position(1, 0, new BoardSize(4, 4)));;
        assert neighborPositions.get(2).equals(new Position(1, 1, new BoardSize(4, 4)));;
    }

    @Test
    public void TestPositionNeighborPositionsXis3Yis3() {
        Position position = new Position(2, 1, new BoardSize(4, 4));
        ArrayList<Position> neighborPositions = (ArrayList<Position>) position.neighborPositions();
        assert neighborPositions.size() == 8;
        assert neighborPositions.get(0).equals(new Position(1, 0, new BoardSize(4, 4)));
        assert neighborPositions.get(1).equals(new Position(1, 1, new BoardSize(4, 4)));
        assert neighborPositions.get(2).equals(new Position(1, 2, new BoardSize(4, 4)));
        assert neighborPositions.get(3).equals(new Position(2, 0, new BoardSize(4, 4)));
        assert neighborPositions.get(4).equals(new Position(2, 2, new BoardSize(4, 4)));
        assert neighborPositions.get(5).equals(new Position(3, 0, new BoardSize(4, 4)));
        assert neighborPositions.get(6).equals(new Position(3, 1, new BoardSize(4, 4)));
        assert neighborPositions.get(7).equals(new Position(3, 2, new BoardSize(4, 4)));
    }
    @Test
    public void TestPositionIsLastInRow() {
        Position position = new Position(3, 1, new BoardSize(4, 4));
        assert position.isLastInRow();
    }
}
