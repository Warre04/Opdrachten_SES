package be.kuleuven.candycrush.model;

import java.util.ArrayList;

public record Position(int x, int y, BoardSize board) {
    public Position {
        if ((x < 0) || (y < 0) || (x >= board.breedte()) || (y >= board.hoogte())) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
    }
    public int toIndex() {
        return y * board.breedte() + x;
    }

    public static Position fromIndex(int index, BoardSize board) {
        return new Position(index % board.breedte(), index / board.breedte(), board);
    }
    public Iterable<Position> neighborPositions()
    {
        ArrayList<Position> neighbors = new ArrayList<Position>();
        int[] operations ={-1,0,1};
        for (int i : operations) {
            for (int j : operations) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (x+i >= 0 && x+i < board.breedte() && y+j >= 0 && y+j < board.hoogte()) {
                    Position neighbor = new Position(x + i, y + j, board);
                    neighbors.add(neighbor);
                }
            }
        }
        return (Iterable<Position>)neighbors;
    }

    public void print() {
        System.out.println("x: " + x + " y: " + y);
    }

    public boolean isLastInRow() {
        return x == board.breedte() - 1;
    }
}
