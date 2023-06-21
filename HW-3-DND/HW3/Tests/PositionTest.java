import junit.framework.TestCase;
import org.junit.Assert;

public class PositionTest extends TestCase {
    Tile t;
    Position p = new Position();
    public void setUp() throws Exception {
        super.setUp();
        t = new Empty();
        t.setPosition(0,1);
        p.setY(1);
        p.setX(1);
    }

    public void testGetRange() {
        Assert.assertEquals(1.0,p.getRange(t).doubleValue(),0.1);
    }

    public void testSwap() {
        p.swap(t.position);
        Assert.assertEquals(0,p.getX());
    }
}