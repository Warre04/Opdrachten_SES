package be.kuleuven.candycrush.model;

import java.util.*;
import java.util.function.Function;

public class Board<T> {
    private BoardSize boardSize;
    private Map<Position, T> cells;
    private Map<T,ArrayList<Position>> reverseCells;

    public Board(int formaat) {
        this.boardSize = new BoardSize(formaat, formaat);
        this.cells = new HashMap<>();
        this.reverseCells=new HashMap<>();
    }

    public T getCellAt(Position pos) {
        return this.cells.get(pos);
    }

    public void replaceCellAt(Position pos, T newCell) {
        T oldCell = this.cells.get(pos);
        if (oldCell != null && this.reverseCells.containsKey(oldCell)) {
            this.reverseCells.get(oldCell).remove(pos);
        }
        if(this.reverseCells.containsKey(newCell)){
            this.reverseCells.get(newCell).add(pos);
        }
        else{
            ArrayList<Position> temp = new ArrayList<>();
            temp.add(pos);
            this.reverseCells.put(newCell,temp);
        }
        this.cells.put(pos, newCell);
    }

    public Board<T> copyTo(Board<T> newBoard) {
        if (!newBoard.boardSize.equals(this.boardSize)) {
            throw new IllegalArgumentException("Board sizes do not match");
        }
        newBoard.setCells(new HashMap<Position, T>(this.cells));
        return newBoard;
    }

    public void fill(Function<Position, T> cellCreator) {
        for (int i = 0; i < this.boardSize.hoogte() * this.boardSize.breedte(); i++) {
            Position pos = Position.fromIndex(i, this.boardSize);
            replaceCellAt(pos, cellCreator.apply(pos));
        }
        generateReversedCells();
    }

    public List<Position> getPositionsOfElement(T element){
        return Collections.unmodifiableList(this.reverseCells.get(element));
    }
    public BoardSize getBoardSize() {
        return this.boardSize;
    }
    public Map<Position, T> getCells() {
        return this.cells;
    }
    public void setCells(HashMap<Position, T> cells) {
        this.cells = cells;
        generateReversedCells();
    }

    private void generateReversedCells() {
        reverseCells.clear();
        for (Map.Entry<Position, T> i : this.cells.entrySet()) {
            if(this.reverseCells.containsKey(i.getValue())){
                this.reverseCells.get(i.getValue()).add(i.getKey());
            }
            else{
                ArrayList<Position> temp = new ArrayList<>();
                temp.add(i.getKey());
                this.reverseCells.put(i.getValue(),temp);
            }
        }
    }
}
