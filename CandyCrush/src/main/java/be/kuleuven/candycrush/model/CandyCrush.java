package be.kuleuven.candycrush.model;
import java.util.ArrayList;

public class CandyCrush {

    private int score;
    private BoardSize boardSize;
    private String name;
    private Board<Candy> board;

    public CandyCrush() {
        this.board = new Board<Candy>();
        boardSize= board.getBoardSize();
        this.name = "player32548";
        this.score = 0;
        this.generateGrid();
    }

    public void generateGrid() {
        this.board.fill((pos) -> Candy.getRandomCandy());
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
    public Board<Candy> getBoard(){
        return this.board;
    }

    public void removeCandy(Position pos) {
        ArrayList<Position> candyToReplace = (ArrayList<Position>) getSameNeighbours(pos);
        if(candyToReplace.size() < 3) {
            return;
        }
        for (Position i : candyToReplace) {
            this.board.replaceCellAt(i, Candy.getRandomCandy());
        }
        addScore(candyToReplace.size());
    }



    private Iterable<Position> getSameNeighbours(Position pos){
        ArrayList<Position> neighborPositions= (ArrayList<Position>) pos.neighborPositions();
        ArrayList<Position> equalNeighborPositions= new ArrayList<>();
        equalNeighborPositions.add(pos);
        for (Position neighbor : neighborPositions) {
            if (this.board.getCellAt(neighbor).equals( this.board.getCellAt(pos)) ){
                equalNeighborPositions.add(neighbor);
            }
        }
        return equalNeighborPositions;
    }



}
