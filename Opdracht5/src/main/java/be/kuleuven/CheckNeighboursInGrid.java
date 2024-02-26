package be.kuleuven;

import javax.naming.InsufficientResourcesException;
import java.util.*;

public class CheckNeighboursInGrid {
    /**
     * This method takes a 1D Iterable and an element in the array and gives back an iterable containing the indexes of all neighbours with the same value as the specified element
     *
     * @param grid         - This is a 1D Iterable containing all elements of the grid. The elements are integers.
     * @param width        - Specifies the width of the grid.
     * @param height       - Specifies the height of the grid (extra for checking if 1D grid is complete given the specified width)
     * @param indexToCheck - Specifies the index of the element which neighbours that need to be checked
     * @return - Returns a 1D Iterable of ints, the Integers represent the indexes of all neighbours with the same value as the specified element on index 'indexToCheck'.
     */
    public static Iterable<Integer> getSameNeighboursIds(Iterable<Integer> grid, int width, int height, int indexToCheck) {
        ArrayList<Integer> gridList = (ArrayList<Integer>) grid;
        int valueAtIndex = gridList.get(indexToCheck);
        int coX = indexToCheck % width;
        int coY = indexToCheck / width;
        int[] coToCheck = {-1, 0, 1};
        ArrayList<Integer> sameNeighboursList = new ArrayList<>();
        for (int y : coToCheck) {
            for (int x : coToCheck) {
                int index = width * (coY + y) + (coX + x);
                if (coY + y >= 0 && coY + y < height && coX + x >= 0 && coX + x < width && index != indexToCheck && index<width*height-1 && gridList.get(index) == valueAtIndex) {
                    sameNeighboursList.add(index);
                }
            }
        }
        Iterable<Integer> iterableList = (Iterable<Integer>) sameNeighboursList;
        return iterableList;
    }
}