package be.kuleuven.CandyCrush.view;
import be.kuleuven.candycrush.model.CandyCrush;
import be.kuleuven.candycrush.view.CandyCrushView;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

public class CandyCrushViewTest {
    @Test
    public void testSnoepnummer1_ThenColorIsRed() {
        CandyCrushView view = new CandyCrushView(new CandyCrush());
        assert view.getColor(1).equals(Color.RED);
    }
    @Test
    public void testSnoepnummer2_ThenColorIsBlue() {
        CandyCrushView view = new CandyCrushView(new CandyCrush());
        assert view.getColor(2).equals(Color.BLUE);
    }
    @Test
    public void testSnoepnummer3_ThenColorIsGreen() {
        CandyCrushView view = new CandyCrushView(new CandyCrush());
        assert view.getColor(3).equals(Color.GREEN);
    }
    @Test
    public void testSnoepnummer4_ThenColorIsYellow() {
        CandyCrushView view = new CandyCrushView(new CandyCrush());
        assert view.getColor(4).equals(Color.YELLOW);
    }
    @Test
    public void testSnoepnummer5_ThenColorIsPurple() {
        CandyCrushView view = new CandyCrushView(new CandyCrush());
        assert view.getColor(5).equals(Color.PURPLE);
    }
}
