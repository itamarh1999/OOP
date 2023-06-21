import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class RogueTest extends TestCase {
    private Player mordehai;
    private  Enemy nebuchadnezzarII;

    private List<Enemy> enemies;



    public void setUp() throws Exception {
        super.setUp();
        mordehai = new Rogue("Mordehai Hamacabi",450,35,28,10);
        mordehai.setPosition(2,3);
        nebuchadnezzarII = new Monster('N',"nebuchadnezzarII",200,3,2,30,15);
        nebuchadnezzarII.setPosition(3,4);
        enemies = new ArrayList<>();
        enemies.add(nebuchadnezzarII);
    }

    public void testCastAbility(){
        mordehai.castAbility(enemies);//new method just for cast ability rogue
        Assert.assertEquals( 165,nebuchadnezzarII.getHP());
    }
}
