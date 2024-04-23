package be.kuleuven.CandyCrush.model;

import be.kuleuven.candycrush.model.Board;
import be.kuleuven.candycrush.model.BoardSize;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.Position;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BoardTest {
    @Test
    public void TestBoardConstructor() {
        Board<Candy> board = new Board<Candy>(10);
        assert board.getBoardSize().equals(new BoardSize(10, 10));
    }

    @Test
    public void TestBoardFill() {
        Board<Candy> board = new Board<Candy>(10);
        board.fill((pos) -> Candy.getRandomCandy());
        for (Map.Entry<Position, Candy> i : board.getCells().entrySet()) {
            assert i.getValue() != null;
        }
    }

    @Test
    public void TestBoardCopyTo() {
        Board<Candy> board = new Board<Candy>(10);
        board.fill((pos) -> Candy.getRandomCandy());
        Board<Candy> newBoard = new Board<Candy>(10);
        newBoard = board.copyTo(newBoard);
        assert newBoard.getCells().equals(board.getCells());
    }

    @Test
    public void TestBoardGetCellAt() {
        Board<Candy> board = new Board<Candy>(10);
        board.fill((pos) -> Candy.getRandomCandy());
        Position pos = new Position(0, 0, board.getBoardSize());
        assert board.getCellAt(pos) != null;
    }

    @Test
    public void TestBoardReplaceCellAt() {
        Board<Candy> board = new Board<>(10);
        board.fill((pos) -> Candy.getRandomSpecialCandy());
        Position pos = new Position(0, 0, board.getBoardSize());
        board.replaceCellAt(pos, Candy.GetNormalCandy(2));
        assert board.getCellAt(pos).equals(Candy.GetNormalCandy(2));
    }

    @Test
    public void TestBoardGetPositionsOfElement() {
        Board<Candy> board = new Board<>(10);
        board.fill((pos) -> Candy.GetNormalCandy(1));
        board.replaceCellAt(new Position(0, 0, board.getBoardSize()), Candy.GetNormalCandy(2));
        board.replaceCellAt(new Position(0, 1, board.getBoardSize()), Candy.GetNormalCandy(2));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(2)).contains(new Position(0, 0, board.getBoardSize()));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(2)).contains(new Position(0, 1, board.getBoardSize()));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(2)).size() == 2;
    }

    @Test
    public void TestBoardGenerateReversedCellsAfterFillAndAfterReplaceCellWithNewCandyType() {
        Board<Candy> board = new Board<>(10);
        board.fill((pos) -> Candy.GetNormalCandy(1));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(1)).size() == board.getBoardSize().hoogte() * board.getBoardSize().breedte();
        board.replaceCellAt(new Position(0, 0, board.getBoardSize()), Candy.GetNormalCandy(2));
        board.replaceCellAt(new Position(0, 1, board.getBoardSize()), Candy.GetNormalCandy(2));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(2)).contains(new Position(0, 0, board.getBoardSize()));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(2)).contains(new Position(0, 1, board.getBoardSize()));
        assert board.getPositionsOfElement(Candy.GetNormalCandy(1)).size() == board.getBoardSize().hoogte() * board.getBoardSize().breedte() - 2;
    }

    @Test
    public void TestBoardGenerateReversedCellsAfterCopyTo() {
        Board<Candy> board = new Board<>(10);
        board.fill((pos) -> Candy.GetNormalCandy(1));
        board.replaceCellAt(new Position(0, 0, board.getBoardSize()), Candy.GetNormalCandy(2));
        Board<Candy> newBoard = new Board<>(10);
        newBoard = board.copyTo(newBoard);
        assert newBoard.getPositionsOfElement(Candy.GetNormalCandy(1)).size() == newBoard.getBoardSize().hoogte() * newBoard.getBoardSize().breedte() - 1;
        assert newBoard.getPositionsOfElement(Candy.GetNormalCandy(2)).contains(new Position(0, 0, newBoard.getBoardSize()));

    }

    @Test
    public void TestBoardSetCells() {
        Board<Candy> board = new Board<>(10);
        board.fill((pos) -> Candy.GetNormalCandy(1));
        Board<Candy> newBoard = new Board<>(10);
        newBoard.fill((pos) -> Candy.GetNormalCandy(2));
        newBoard.setCells((HashMap<Position, Candy>) board.getCells());
        assert newBoard.getCells().equals(board.getCells());
        assert newBoard.getPositionsOfElement(Candy.GetNormalCandy(1)).size() == newBoard.getBoardSize().hoogte() * newBoard.getBoardSize().breedte();
    }



}
