package be.kuleuven.candycrush.model;
import java.util.ArrayList;

public class CandyCrush {

    private ArrayList<Candy> grid;
    private int score;
    private BoardSize boardSize;
    private String name;

    public CandyCrush() {
        boardSize= new BoardSize(10,10);
        this.name = "player32548";
        this.grid = new ArrayList<Candy>();
        this.score = 0;
        this.generateGrid();
    }

    public void generateGrid() {
        this.grid.clear();
        for (int i = 0; i < this.boardSize.hoogte() * this.boardSize.breedte(); i++) {
            this.grid.add(Candy.getRandomCandy());
        }
    }
    public void setGrid(ArrayList<Candy> grid) {
        this.grid = grid;
    }

    public void reset() {
        this.score = 0;
        this.generateGrid();
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return this.score;
    }
    public void addScore(int score) {
        this.score += score;
    }
    public int getHeight() {
        return this.boardSize.hoogte();
    }
    public int getWidth() {
        return this.boardSize.breedte();
    }
    public BoardSize getBoardSize() {
        return this.boardSize;
    }
    public ArrayList<Candy> getGrid() {
        return this.grid;
    }

    public void removeCandy(Position pos) {
        ArrayList<Position> candyToReplace = (ArrayList<Position>) getSameNeighbours(pos);
        System.out.println(candyToReplace.size());
        if(candyToReplace.size() < 3) {
            return;
        }
        for (Position i : candyToReplace) {
            this.grid.set(i.toIndex(), Candy.getRandomCandy());
        }
        addScore(candyToReplace.size());
    }



    private Iterable<Position> getSameNeighbours(Position pos){
        ArrayList<Position> neighborPositions= (ArrayList<Position>) pos.neighborPositions();
        ArrayList<Position> equalNeighborPositions= new ArrayList<>();
        equalNeighborPositions.add(pos);
        for (Position neighbor : neighborPositions) {
            if (this.grid.get(neighbor.toIndex()).equals( this.grid.get(pos.toIndex())) ){
                equalNeighborPositions.add(neighbor);
            }
        }
        return equalNeighborPositions;
    }


}
