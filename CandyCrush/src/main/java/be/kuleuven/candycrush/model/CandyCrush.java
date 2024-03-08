package be.kuleuven.candycrush.model;
import java.util.ArrayList;
import be.kuleuven.CheckNeighboursInGrid;
public class CandyCrush {

    private ArrayList<Integer> grid;
    private int score;
    private int height;
    private int width;
    private String name;

    public CandyCrush() {
        this.height = 10;
        this.width = 10;
        this.name = "player32548";
        this.grid = new ArrayList<Integer>();
        this.score = 0;
        this.generateGrid();
    }

    public void generateGrid() {
        this.grid.clear();
        for (int i = 0; i < this.height * this.width; i++) {
            this.grid.add((int) (Math.random() * 5)+1);
        }
    }
    public void setGrid(ArrayList<Integer> grid) {
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
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public ArrayList<Integer> getGrid() {
        return this.grid;
    }
    public void removeCandy(int index) {
        ArrayList<Integer> candyToReplace = getCandyToRemove(index);
        if(candyToReplace.size() < 3) {
            return;
        }
        for (int i : candyToReplace) {
            this.grid.set(i, (int) (Math.random() * 5)+1);
        }
        addScore(candyToReplace.size());
    }
    public ArrayList<Integer> getCandyToRemove(int index) {
        Iterable<Integer> Igrid= (Iterable<Integer>) grid;
        Iterable<Integer> IcandyToReplace = CheckNeighboursInGrid.getSameNeighboursIds(Igrid, this.width, this.height, index);
        ArrayList<Integer> candyToReplace = (ArrayList<Integer>) IcandyToReplace;
        candyToReplace.add(index);
        return candyToReplace;
    }


}
