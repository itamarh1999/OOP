import junit.framework.TestCase;
import org.junit.Assert;


public class UnitTest extends TestCase {
    private Player mordehai;
    private Empty empty;



    public void setUp() throws Exception {
        super.setUp();
        mordehai = new Mage("Mordehai Hamacabi",250,35,1,300,20,20,1,6);
        mordehai.setPosition(2,3);
        empty = new Empty();
        empty.setPosition(3,3);
    }

    public void testInteractTest(){
        mordehai.interact(empty);
        Assert.assertEquals(3,mordehai.getPosition().getX());
    }

    public void testIsAliveTest(){
        boolean alive = mordehai.isAlive();
        Assert.assertTrue(alive);
    }
}