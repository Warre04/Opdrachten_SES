package be.kuleuven.CandyCrush.model;
import be.kuleuven.candycrush.model.CandyCrush;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

public class CandyCrushTest {
    @Test
    public void TestSetNameToWarre_ResultNameIsWarre() {
        CandyCrush model = new CandyCrush();
        model.setName("Warre");
        assert model.getName().equals("Warre");
    }
    @Test
    public void TestSetScoreTo10ANDSetScoreTo11_ResultScoreIs21() {
        CandyCrush model = new CandyCrush();
        model.addScore(10);
        model.addScore(11);
        assert model.getScore() == 21;
    }
    @Test
    public void TestSetscore10ANDrest_ResultScoreIs0() {
        CandyCrush model = new CandyCrush();
        model.addScore(10);
        model.reset();
        assert model.getScore() == 0;
    }
    @Test
    public void TestSetScoreTo10ANDSetScoreTomin11_ResultScoreIsmin1() {
        CandyCrush model = new CandyCrush();
        model.addScore(10);
        model.addScore(-11);
        assert model.getScore() == -1;
    }
    @Test
    public void testSetNameToWarre_Thenreset_ResultNameIsWarre() {
        CandyCrush model = new CandyCrush();
        model.setName("Warre");
        model.reset();
        assert model.getName().equals("Warre");
    }

    @Test
    public void TestCandyCrushAanmaken_ResultGridIsNotEmpty() {
        CandyCrush model = new CandyCrush();
        assert !model.getGrid().isEmpty();
    }
    @Test
    public void TestCandyCrushAanmaken_ResultGridSizeISHeigthxWidth() {
        CandyCrush model = new CandyCrush();
        assert model.getGrid().size() == model.getHeight() * model.getWidth();
    }
    @Test
    public void TestsetName_CandyCrushReset_GritNotSize0() {
        CandyCrush model = new CandyCrush();
        model.setName("Warre");
        model.reset();
        assert !model.getGrid().isEmpty();
    }
    @Test
    public void TestsetName_CandyCrushReset_GritSizeIsHeigthxWidth() {
        CandyCrush model = new CandyCrush();
        model.setName("Warre");
        model.reset();
        assert model.getGrid().size() == model.getHeight() * model.getWidth();
    }
}
