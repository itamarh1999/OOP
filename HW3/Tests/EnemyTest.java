import junit.framework.TestCase;
import org.junit.Assert;

public class EnemyTest extends TestCase {
    Player p;
    Enemy e1;
    public void setUp() throws Exception {
        super.setUp();
        p = new Warrior("itamar",3000,30,1,2);
        p.setPosition(0,0);
        e1 = new Monster('a',"monst",299,2900,1,30,4);
        e1.setPosition(1,1);
    }

    public void testVisit() {
        e1.visit(p);
        Assert.assertEquals(true, p.getHP()<3000);
    }
}