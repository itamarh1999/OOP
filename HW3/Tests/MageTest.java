import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class MageTest extends TestCase {
    private Player mordehai;
    private Enemy haman;

    private List<Enemy> enemies;



    public void setUp() throws Exception {
        super.setUp();
        mordehai = new Mage("Mordehai Hamacabi",250,35,1,300,20,20,1,6);
        mordehai.setPosition(2,3);
        haman = new Monster('H',"haman Harasha",150,3,25,30,15);
        haman.setPosition(2,4);
        enemies = new ArrayList<>();
        enemies.add(haman);
    }

    public void testCastAbility(){
        mordehai.castAbility(enemies);
        Assert.assertEquals( 130,haman.getHP());
    }
}