package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CandyCrush {

    private int score;
    private String name;
    private Board<Candy> board;

    public CandyCrush(int size) {
        this.board = new Board<Candy>(size);
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

    public Board<Candy> getBoard() {
        return this.board;
    }

    public void removeCandy(Position pos) {
        ArrayList<Position> candyToReplace = (ArrayList<Position>) getSameNeighbours(pos);
        if (candyToReplace.size() < 3) {
            return;
        }
        for (Position i : candyToReplace) {
            this.board.replaceCellAt(i, Candy.getRandomCandy());
        }
        addScore(candyToReplace.size());
    }


    private Iterable<Position> getSameNeighbours(Position pos) {
        ArrayList<Position> neighborPositions = (ArrayList<Position>) pos.neighborPositions();
        ArrayList<Position> equalNeighborPositions = new ArrayList<>();
        equalNeighborPositions.add(pos);
        for (Position neighbor : neighborPositions) {
            if (this.board.getCellAt(neighbor).equals(this.board.getCellAt(pos))) {
                equalNeighborPositions.add(neighbor);
            }
        }
        return equalNeighborPositions;
    }


    public boolean firstTwoHaveCandy(Candy candy, Stream<Position> positions) {
        List<Position> positionList = positions.toList();
        if (positionList.size() < 2) {
            return false;
        }
        return positionList.stream().limit(2).allMatch((pos) -> this.board.getCellAt(pos).equals(candy));
    }

    public Stream<Position> horizontalStartingPositions() {
        return this.board.getCells().entrySet().stream()
                .filter(entry-> firstTwoHaveCandy(entry.getValue(),entry.getKey().walkRight()))
                .filter(entry -> !firstTwoHaveCandy(entry.getValue(),entry.getKey().walkLeft()))
                .map(entry->entry.getKey());
    }
    public Stream<Position> verticalStartingPositions() {
        return this.board.getCells().entrySet().stream()
                .filter(entry-> firstTwoHaveCandy(entry.getValue(),entry.getKey().walkDown()))
                .filter(entry -> !firstTwoHaveCandy(entry.getValue(),entry.getKey().walkUp()))
                .map(entry->entry.getKey());
    }
    public List<Position> longestMatchToRight(Position pos){
        return this.board.getCells().entrySet().stream()
                .filter(entry-> pos.walkRight().anyMatch(p->p.equals(entry.getKey())))
                .sorted(Comparator.comparingInt(entry -> entry.getKey().x()))
                .takeWhile(entry->entry.getValue().equals(this.board.getCellAt(pos)))
                .map(entry -> entry.getKey())
                .toList();
    }
    public List<Position> longestMatchDown(Position pos){
        return this.board.getCells().entrySet().stream()
                .filter(entry-> pos.walkDown().anyMatch(p->p.equals(entry.getKey())))
                .sorted(Comparator.comparingInt(entry -> entry.getKey().y()))
                .takeWhile(entry->entry.getValue().equals(this.board.getCellAt(pos)))
                .map(entry -> entry.getKey())
                .toList();
    }
    public Set<List<Position>> findAllMatches(){
        Set<List<Position>>  horizontalSet = horizontalStartingPositions().map(p->longestMatchToRight(p)).filter(list->list.size()>2).collect(Collectors.toSet());
        verticalStartingPositions().map(p->longestMatchDown(p)).filter(list->list.size()>2).forEach(list->horizontalSet.add(list));
        return horizontalSet;
    }

}
