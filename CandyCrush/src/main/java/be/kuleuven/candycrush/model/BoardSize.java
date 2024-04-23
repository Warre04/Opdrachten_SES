package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.Collection;

public record BoardSize(int breedte, int hoogte) {
    public BoardSize {
        if (breedte <= 0 || hoogte <= 0) {
            throw new IllegalArgumentException("Board too small");
        }
    }

    public Collection<Position> positions()
    {
        ArrayList<Position> positions = new ArrayList<Position>();
        for (int i = 0; i < hoogte; i++) {
            for (int j = 0; j < breedte; j++) {
                positions.add(new Position(j, i, this));
            }
        }
        return positions;
    }

}

