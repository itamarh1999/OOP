import junit.framework.TestCase;
import org.junit.Assert;

public class HealthTest extends TestCase {
    Health h1;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        h1 = new Health(300);
    }

    public void testGetHitedPureDamage() {
        h1.getHitedPureDamage(10);
        Assert.assertEquals(290,h1.getHP());
    }

    public void testGetHittedWithDefence() {
        h1.getHittedWithDefence(15,5);
        Assert.assertEquals(290,h1.getHP());
    }

    public void testLevelUp() {
        h1.levelUp(2);
        Assert.assertEquals(320,h1.getHP());

    }

    public void testWarriorLevelUP() {
        h1.warriorLevelUP(2);
        Assert.assertEquals(310,h1.getMaxHP());

    }

    public void testAliveSunny() {
        Assert.assertEquals(true,h1.alive());
    }
    public void testAliveRainy() {
        h1.getHitedPureDamage(300);
        Assert.assertEquals(false,h1.alive());
    }

    public void testHeal() {
        h1.getHitedPureDamage(200);
        h1.heal(20);
        Assert.assertEquals(120,h1.getHP());
    }
}