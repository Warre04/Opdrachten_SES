package be.kuleuven.CandyCrush.model;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.CandyCrush;
import be.kuleuven.candycrush.model.NormalCandy;
import be.kuleuven.candycrush.model.Position;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;


public class CandyCrushTest {
    @Test
    public void TestSetNameToWarre_ResultNameIsWarre() {
        CandyCrush model = new CandyCrush(10);
        model.setName("Warre");
        assert model.getName().equals("Warre");
    }
    @Test
    public void TestSetScoreTo10ANDSetScoreTo11_ResultScoreIs21() {
        CandyCrush model = new CandyCrush(10);
        model.addScore(10);
        model.addScore(11);
        assert model.getScore() == 21;
    }
    @Test
    public void TestSetscore10ANDrest_ResultScoreIs0() {
        CandyCrush model = new CandyCrush(10);
        model.addScore(10);
        model.reset();
        assert model.getScore() == 0;
    }
    @Test
    public void TestSetScoreTo10ANDSetScoreTomin11_ResultScoreIsmin1() {
        CandyCrush model = new CandyCrush(10);
        model.addScore(10);
        model.addScore(-11);
        assert model.getScore() == -1;
    }
    @Test
    public void testSetNameToWarre_Thenreset_ResultNameIsWarre() {
        CandyCrush model = new CandyCrush(10);
        model.setName("Warre");
        model.reset();
        assert model.getName().equals("Warre");
    }

    @Test
    public void TestCandyCrushAanmaken_ResultGridIsNotEmpty() {
        CandyCrush model = new CandyCrush(10);
        assert !model.getBoard().getCells().isEmpty();
    }
    @Test
    public void TestCandyCrushAanmaken_ResultGridSizeISHeigthxWidth() {
        CandyCrush model = new CandyCrush(10);
        assert model.getBoard().getCells().size() == model.getBoard().getBoardSize().hoogte() * model.getBoard().getBoardSize().breedte();
    }
    @Test
    public void TestsetName_CandyCrushReset_GritNotSize0() {
        CandyCrush model = new CandyCrush(10);
        model.setName("Warre");
        model.reset();
        assert !model.getBoard().getCells().isEmpty();
    }
    @Test
    public void TestsetName_CandyCrushReset_GritSizeIsHeigthxWidth() {
        CandyCrush model = new CandyCrush(10);
        model.setName("Warre");
        model.reset();
        assert model.getBoard().getCells().size() == model.getBoard().getBoardSize().hoogte() * model.getBoard().getBoardSize().breedte();
    }
    @Test
    public void TestFirstTwoHaveCandy(){
        CandyCrush model = new CandyCrush(10);
        model.getBoard().fill((pos) -> new NormalCandy(2));
        Candy candy = new NormalCandy(2);
        Position pos1 = new Position(0,0, model.getBoard().getBoardSize());
        assert model.firstTwoHaveCandy(candy, pos1.walkDown());
    }
    @Test
    public void TestFirstTwoHaveCandyWhithShortStream(){
        CandyCrush model = new CandyCrush(10);
        model.getBoard().fill((pos) -> new NormalCandy(2));
        Candy candy = new NormalCandy(2);
        Position pos1 = new Position(9,0, model.getBoard().getBoardSize());
        assert !model.firstTwoHaveCandy(candy, pos1.walkRight());
    }
    @Test
    public void TestHorizontalStartingPosition(){
        CandyCrush model = new CandyCrush(10);
        model.getBoard().fill((pos) -> new NormalCandy(2));
        model.getBoard().replaceCellAt(new Position(0,0, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(1,0, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(2,0, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(0,1, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(1,1, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(2,1, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(3,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(5,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(6,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(7,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(8,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(9,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        assert model.horizontalStartingPositions().count()==12;
    }
    @Test
    public void TestlongestMatchToRight(){
        CandyCrush model = new CandyCrush(10);
        model.getBoard().fill((pos) -> new NormalCandy(2));
        model.getBoard().replaceCellAt(new Position(3,0, model.getBoard().getBoardSize()), new NormalCandy(3));

        assert model.longestMatchToRight(new Position(0,0, model.getBoard().getBoardSize())).size()==3;
        assert model.longestMatchToRight(new Position(0,0, model.getBoard().getBoardSize())).get(0).equals(new Position(0,0, model.getBoard().getBoardSize()));
        assert model.longestMatchToRight(new Position(0,0, model.getBoard().getBoardSize())).get(1).equals(new Position(1,0, model.getBoard().getBoardSize()));
        assert model.longestMatchToRight(new Position(0,0, model.getBoard().getBoardSize())).get(2).equals(new Position(2,0, model.getBoard().getBoardSize()));
    }
    @Test
    public void TestFindAllMatches(){
        CandyCrush model =new CandyCrush(4);
        model.getBoard().fill((pos) -> new NormalCandy(2));
        model.getBoard().replaceCellAt(new Position(0,0, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(3,3, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(0,1, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(0,2, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(1,2, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(2,2, model.getBoard().getBoardSize()), new NormalCandy(1));
        model.getBoard().replaceCellAt(new Position(2,1, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(0,3, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(1,3, model.getBoard().getBoardSize()), new NormalCandy(3));
        model.getBoard().replaceCellAt(new Position(2,3, model.getBoard().getBoardSize()), new NormalCandy(3));
        assert model.findAllMatches().size()==5;

    }


}
