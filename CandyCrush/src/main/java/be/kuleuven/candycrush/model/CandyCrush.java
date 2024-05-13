package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CandyCrush {

    private int score;
    private String name;
    private Board<Candy> board;
    private Position SelectedPosition;
    private Solution optimalSolution;

    public CandyCrush(int size) {
        this.board = new Board<Candy>(size);
        this.name = "player32548";
        this.score = 0;
        this.generateGrid();
        this.SelectedPosition = null;
    }

    public CandyCrush(int size, String boardString) {
        this.board = new Board<Candy>(size);
        this.name = "player32548";
        this.score = 0;
        board.fill((pos) -> Candy.charToCandy(boardString.charAt(pos.y() * size + pos.x())));
        this.SelectedPosition = null;
    }


    public void generateGrid() {
        this.board.fill((pos) -> Candy.getRandomCandy());
    }

    public void reset() {
        this.optimalSolution = null;
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
        score = (int) this.board.getCells().entrySet().stream().filter(entry -> entry.getValue() == null).count();
        return this.score;
    }


    public Board<Candy> getBoard() {
        return this.board;
    }


    public boolean firstTwoHaveCandy(Candy candyToCheck, Stream<Position> positions, Board<Candy> boardToCheck) {
        List<Position> positionList = positions.toList();
        if (positionList.size() < 2) {
            return false;
        }
        try {
            return positionList.stream().limit(2).allMatch((pos) -> boardToCheck.getCellAt(pos).equals(candyToCheck));
        } catch (NullPointerException e) {
            return false;
        }
    }

    public Stream<Position> horizontalStartingPositions(Board<Candy> boardToCheck) {
        return boardToCheck.getCells().entrySet().stream()
                .filter(entry -> firstTwoHaveCandy(entry.getValue(), entry.getKey().walkRight(), boardToCheck))
                .filter(entry -> !firstTwoHaveCandy(entry.getValue(), entry.getKey().walkLeft(), boardToCheck))
                .map(entry -> entry.getKey());
    }

    public Stream<Position> verticalStartingPositions(Board<Candy> boardToCheck) {
        return boardToCheck.getCells().entrySet().stream()
                .filter(entry -> firstTwoHaveCandy(entry.getValue(), entry.getKey().walkDown(), boardToCheck))
                .filter(entry -> !firstTwoHaveCandy(entry.getValue(), entry.getKey().walkUp(), boardToCheck))
                .map(entry -> entry.getKey());
    }

    public List<Position> longestMatchToRight(Position pos, Board<Candy> boardToCheck) {
        return boardToCheck.getCells().entrySet().stream()
                .filter(entry -> pos.walkRight().anyMatch(p -> p.equals(entry.getKey())))
                .sorted(Comparator.comparingInt(entry -> entry.getKey().x()))
                .takeWhile(entry -> entry.getValue() != null && entry.getValue().equals(boardToCheck.getCellAt(pos)))
                .map(entry -> entry.getKey())
                .toList();
    }

    public List<Position> longestMatchDown(Position pos, Board<Candy> boardToCheck) {
        return boardToCheck.getCells().entrySet().stream()
                .filter(entry -> pos.walkDown().anyMatch(p -> p.equals(entry.getKey())))
                .sorted(Comparator.comparingInt(entry -> entry.getKey().y()))
                .takeWhile(entry -> entry.getValue() != null && entry.getValue().equals(boardToCheck.getCellAt(pos)))
                .map(entry -> entry.getKey())
                .toList();
    }

    public Set<List<Position>> findAllMatches(Board<Candy> boardToCheck) {
        Set<List<Position>> horizontalSet = horizontalStartingPositions(boardToCheck).map(p -> longestMatchToRight(p, boardToCheck)).filter(list -> list.size() >= 3).collect(Collectors.toSet());
        verticalStartingPositions(boardToCheck).map(p -> longestMatchDown(p, boardToCheck)).filter(list -> list.size() > 2).forEach(horizontalSet::add);
        return horizontalSet;
    }

    public void clearMatch(List<Position> match, Board<Candy> boardToClear) {
        if (!match.isEmpty()) {
            Position pos = match.getFirst();
            if (boardToClear.getCellAt(pos) != null) {
                boardToClear.replaceCellAt(pos, null);
            }
            clearMatch(match.subList(1, match.size()), boardToClear);
        }
    }

    public void fallDownTo(Position pos, Board<Candy> boardToCheck) {
        if (pos.y() == 0) {
            if (pos.walkDown().dropWhile(p -> boardToCheck.getCellAt(p) == null).allMatch(p -> boardToCheck.getCellAt(p) != null)) {
                return;
            } else {
                fallDownTo(new Position(pos.x(), boardToCheck.getBoardSize().hoogte() - 1, boardToCheck.getBoardSize()), boardToCheck);
            }
        } else {
            Position posBovenPos = new Position(pos.x(), pos.y() - 1, boardToCheck.getBoardSize());
            if (boardToCheck.getCellAt(pos) == null) {
                boardToCheck.replaceCellAt(pos, boardToCheck.getCellAt(posBovenPos));
                boardToCheck.replaceCellAt(posBovenPos, null);
            }
            fallDownTo(posBovenPos, boardToCheck);
        }

    }

    public boolean updateBoard(Board<Candy> boardToUpdate) {
        Set<List<Position>> matches = this.findAllMatches(boardToUpdate);
        if (matches.isEmpty()) {
            return false;
        }
        matches.forEach(match -> clearMatch(match, boardToUpdate));
        for (int i = 0; i < boardToUpdate.getBoardSize().breedte(); i++) {
            fallDownTo(new Position(i, boardToUpdate.getBoardSize().hoogte() - 1, boardToUpdate.getBoardSize()), boardToUpdate);
        }
        updateBoard(boardToUpdate);
        return true;
    }

    public Position getSelectedPosition() {
        return this.SelectedPosition;
    }

    public List<Position[]> getPosibleSwaps(Board<Candy> boardToCheck) {

        Stream<Position[]> resultHorizontal =
                boardToCheck.getCells().entrySet().stream().filter(entry -> canSwapRigth(entry.getKey(), boardToCheck))
                        .map(entry -> new Position[]{entry.getKey(), new Position(entry.getKey().x() + 1, entry.getKey().y(), boardToCheck.getBoardSize())});
        Stream<Position[]> resultVertical = boardToCheck.getCells().entrySet().stream()
                .filter(entry -> canSwapDown(entry.getKey(), boardToCheck))
                .map(entry -> new Position[]{entry.getKey(), new Position(entry.getKey().x(), entry.getKey().y() + 1, boardToCheck.getBoardSize())});
        return Stream.concat(resultHorizontal, resultVertical).toList();


    }

    public boolean canSwapRigth(Position pos, Board<Candy> boardToCheck) {

        if (pos.x() == boardToCheck.getBoardSize().breedte() - 1 || boardToCheck.getCellAt(pos) == null) {
            return false;
        }
        Position tempPos = new Position(pos.x() + 1, pos.y(), boardToCheck.getBoardSize());
        if (boardToCheck.getCellAt(tempPos) == null) {
            return false;
        }
        Candy temp = boardToCheck.getCellAt(tempPos);
        boardToCheck.replaceCellAt(tempPos, boardToCheck.getCellAt(pos));
        boardToCheck.replaceCellAt(pos, temp);
        if (!findAllMatches(boardToCheck).isEmpty()) {
            temp = boardToCheck.getCellAt(tempPos);
            boardToCheck.replaceCellAt(tempPos, boardToCheck.getCellAt(pos));
            boardToCheck.replaceCellAt(pos, temp);
            return true;
        }
        temp = boardToCheck.getCellAt(tempPos);
        boardToCheck.replaceCellAt(tempPos, boardToCheck.getCellAt(pos));
        boardToCheck.replaceCellAt(pos, temp);
        return false;
    }

    public boolean canSwapDown(Position pos, Board<Candy> boardToCheck) {
        if (pos.y() == boardToCheck.getBoardSize().hoogte() - 1 || boardToCheck.getCellAt(pos) == null) {
            return false;
        }
        Position tempPos = new Position(pos.x(), pos.y() + 1, boardToCheck.getBoardSize());
        Candy temp = boardToCheck.getCellAt(tempPos);
        boardToCheck.replaceCellAt(tempPos, boardToCheck.getCellAt(pos));
        boardToCheck.replaceCellAt(pos, temp);
        if (!findAllMatches(boardToCheck).isEmpty()) {
            temp = boardToCheck.getCellAt(tempPos);
            boardToCheck.replaceCellAt(tempPos, boardToCheck.getCellAt(pos));
            boardToCheck.replaceCellAt(pos, temp);
            return true;
        }
        temp = boardToCheck.getCellAt(tempPos);
        boardToCheck.replaceCellAt(tempPos, boardToCheck.getCellAt(pos));
        boardToCheck.replaceCellAt(pos, temp);
        return false;
    }


    public void playerSwap(Position pos) {
        if (this.board.getCellAt(pos) == null) {
            return;
        }
        if (this.SelectedPosition == null) {
            this.SelectedPosition = pos;
            return;
        }
        if (pos.equals(SelectedPosition)) {
            this.SelectedPosition = null;
            return;
        }
        swap(pos, SelectedPosition, this.board);
        this.SelectedPosition = null;

    }

    public Solution getOptimalSolution() {
        return this.optimalSolution;
    }

    public void swap(Position pos1, Position pos2, Board<Candy> boardToSwap) {
        CheckSwapInSolution(pos1, pos2);

        if (pos1.y() + 1 == pos2.y() && pos1.x() == pos2.x() || pos1.y() - 1 == pos2.y() && pos1.x() == pos2.x()
                || pos1.y() == pos2.y() && pos1.x() - 1 == pos2.x() || pos1.y() == pos2.y() && pos1.x() + 1 == pos2.x()) {
            Candy temp = boardToSwap.getCellAt(pos1);
            boardToSwap.replaceCellAt(pos1, boardToSwap.getCellAt(pos2));
            boardToSwap.replaceCellAt(pos2, temp);
            if (findAllMatches(boardToSwap).isEmpty()) {
                temp = boardToSwap.getCellAt(pos1);
                boardToSwap.replaceCellAt(pos1, boardToSwap.getCellAt(pos2));
                boardToSwap.replaceCellAt(pos2, temp);
            }
        }
    }

    public void CheckSwapInSolution(Position pos1, Position pos2) {
        if (optimalSolution != null && !optimalSolution.steps().isEmpty()) {
            if (pos1.equals(optimalSolution.steps().getFirst()[0]) && pos2.equals(optimalSolution.steps().getFirst()[1])
                    || pos1.equals(optimalSolution.steps().getFirst()[1]) && pos2.equals(optimalSolution.steps().getFirst()[0])) {
                ArrayList<Position[]> newSteps = new ArrayList<>();
                newSteps = (ArrayList<Position[]>) optimalSolution.steps();
                newSteps.removeFirst();
                this.optimalSolution = new Solution(optimalSolution.score(), newSteps, optimalSolution.board());
            } else {
                this.optimalSolution = null;
                System.out.println("not correct");
            }
        }
    }


    public boolean SolutionIsComplete(Solution solution) {
        return getPosibleSwaps(solution.board()).isEmpty();
    }

    public Solution findBestSolution() {
        this.optimalSolution = null;
        Solution initSolution = new Solution(0, List.of(), this.board);
        optimalSolution = OptimalSolutionFinder(initSolution, null);
        return optimalSolution;
    }

    public Solution OptimalSolutionFinder(Solution currant, Solution BestSoFar) {
        if (SolutionIsComplete(currant)) {
            if (BestSoFar == null || currant.isBetter(BestSoFar)) {
                return currant;
            }
            return BestSoFar;
        }
        List<Position[]> swaps = getPosibleSwaps(currant.board());
        for (Position[] swap : swaps) {
            Board<Candy> newBoard = new Board<>(currant.board());
            swap(swap[1], swap[0], newBoard);
            updateBoard(newBoard);
            int score = (int) newBoard.getCells().entrySet().stream().filter(entry -> entry.getValue() == null).count();
            List<Position[]> newSteps = new ArrayList<>(currant.steps());
            newSteps.add(swap);
            Solution newSolution = new Solution(score, newSteps, newBoard);
            BestSoFar = OptimalSolutionFinder(newSolution, BestSoFar);

        }
        return BestSoFar;
    }

}
