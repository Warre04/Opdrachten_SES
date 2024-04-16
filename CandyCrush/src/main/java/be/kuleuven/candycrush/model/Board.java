package be.kuleuven.candycrush.model;

import java.util.function.Function;

import java.util.ArrayList;
import java.util.function.Function;

public class Board<T> {
    private BoardSize boardSize;
    private ArrayList<T> cells;

    public Board() {
        this.boardSize = new BoardSize(10, 10);
        this.cells = new ArrayList<T>();
        for (int i = 0; i < this.boardSize.hoogte() * this.boardSize.breedte(); i++) {
            this.cells.add(null);
        }
    }

    public T getCellAt(Position pos) {
        return this.cells.get(pos.toIndex());
    }

    public void replaceCellAt(Position pos, T newCell) {
        this.cells.set(pos.toIndex(), newCell);
    }

    public Board<T> copyTo(Board<T> newBoard) {
        if (!newBoard.boardSize.equals(this.boardSize)) {
            throw new IllegalArgumentException("Board sizes do not match");
        }
        newBoard.cells = new ArrayList<T>(this.cells);
        return newBoard;
    }

    public void fill(Function<Position, T> cellCreator) {
        for (int i = 0; i < this.boardSize.hoogte() * this.boardSize.breedte(); i++) {
            Position pos = Position.fromIndex(i, this.boardSize);
            replaceCellAt(pos, cellCreator.apply(pos));
        }
    }

    public BoardSize getBoardSize() {
        return this.boardSize;
    }
    public int getArrarLength() {
        return this.cells.size();
    }
    public ArrayList<T> getCells() {
        return this.cells;
    }
    public void setCells(ArrayList<T> cells) {
        this.cells = cells;
    }
}
