package be.kuleuven.candycrush.model;

import java.util.List;

public record Solution(int score, List<Position[]> steps, Board<Candy> board) {

    public boolean isBetter(Solution other) {
        if (this.score() > other.score()) {
            return true;
        } else if (this.score() < other.score()) {
            return false;
        } else {
            return this.steps().size() < other.steps().size();
        }
    }



}
