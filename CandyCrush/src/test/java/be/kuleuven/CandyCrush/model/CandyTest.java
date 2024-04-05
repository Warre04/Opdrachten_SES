package be.kuleuven.CandyCrush.model;
import be.kuleuven.candycrush.model.NormalCandy;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import be.kuleuven.candycrush.model.Candy;
public class CandyTest {
    @Test
    public void testNormalCandy() {
        Candy normalCandy = new NormalCandy(1);
        assertEquals(Color.BLUE, normalCandy.getColor());
    }

}
