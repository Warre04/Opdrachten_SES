package be.kuleuven.CandyCrush.model;
import be.kuleuven.candycrush.model.Board;
import be.kuleuven.candycrush.model.BoardSize;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.Position;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void TestBoardConstructor() {
        Board<Candy> board = new Board<Candy>();
        assert board.getBoardSize().equals(new BoardSize(10, 10));
    }
    @Test
    public void TestBoardFill() {
        Board<Candy> board = new Board<Candy>();
        board.fill((pos) -> Candy.getRandomCandy());
        for (int i = 0; i < board.getBoardSize().hoogte()*board.getBoardSize().breedte(); i++) {
            assert board.getCells().get(i) != null;
        }
    }
    @Test
    public void TestBoardCopyTo() {
        Board<Candy> board = new Board<Candy>();
        board.fill((pos) -> Candy.getRandomCandy());
        Board<Candy> newBoard = new Board<Candy>();
        newBoard = board.copyTo(newBoard);
        assert newBoard.getCells().equals(board.getCells());
    }
    @Test
    public void TestBoardGetCellAt() {
        Board<Candy> board = new Board<Candy>();
        board.fill((pos) -> Candy.getRandomCandy());
        Position pos = new Position(0, 0, board.getBoardSize());
        assert board.getCellAt(pos) != null;
    }
    @Test
    public void TestBoardReplaceCellAt() {
        Board<Candy> board = new Board<>();
        board.fill((pos) -> Candy.getRandomSpecialCandy());
        Position pos = new Position(0, 0, board.getBoardSize());
        board.replaceCellAt(pos, Candy.GetNormalCandy(2));
        assert board.getCellAt(pos).equals(Candy.GetNormalCandy(2));
    }


}
