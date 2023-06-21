import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest extends TestCase {

    Player p;
    Enemy e1;
    Enemy e2;
    List<Enemy> enemies;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        p = new Warrior("itamar",300,999,10,2);
        p.setPosition(0,0);
        e1 = new Monster('a',"monst",1000,30,1,30,4);
        e1.setPosition(1,1);
        e2 = new Monster('a',"monst",100,30,10,30,4);
        e2.setPosition(10,10);
        enemies = new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);
    }

    public void testLevelUp() {
        p.levelUp();
        Assert.assertEquals(2,p.level);
        Assert.assertEquals(42,p.AD);
    }

    public void testVisit() {
        p.visit(e1);
        Assert.assertEquals(true,e1.getHP() < 100);
    }

    public void testEnemiesInRange() {
        List<Enemy> lst = p.enemiesInRange(enemies);
        Assert.assertEquals(1,lst.size());
    }

    public void testGainEXP() {
        p.gainEXP(30);
        Assert.assertEquals(30,p.EXP);
        p.gainEXP(155);
        Assert.assertEquals(3,p.level);
        Assert.assertEquals(35,p.EXP);
    }
}