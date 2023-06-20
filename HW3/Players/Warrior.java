import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {

    private int abilityCool;
    private int remainCool;

    public Warrior(String name, int HP, int AD, int defence, int abilityCool) {
        super(name, HP, AD, defence);
        this.abilityCool = abilityCool;
        remainCool = 0;
    }

    public void levelUp() {
        super.levelUp();
        health.warriorLevelUP(level);
        remainCool = 0;
        AD = AD + (2*level);
        defence = defence + level;
    }
    public void castAbility(List<Enemy> enemies) {
        if (remainCool == 0)
        {
            remainCool = abilityCool;
            heal(10 * defence);
            ArrayList<Enemy> enemiesInRange = enemiesInRange(enemies);//get all the enemies within ability range
            if (enemiesInRange.size()>0) {
                Random random = new Random();
                int toAttack = random.nextInt(enemiesInRange.size());
                Enemy enemy = enemiesInRange.get(toAttack);
                enemy.health.getHitedPureDamage( health.getMaxHP() / 10);
                if (!enemy.isAlive()){
                    gainEXP(enemy.getEXPvalue());
                    messageCallback.send(name + " used special ability and hit " + enemy.name + " and dealt " +
                            health.getMaxHP()/10 + " damage. " + enemy.name + " died" );
                }
                else
                    messageCallback.send(name + " used special ability and hit " + enemy.name + " and dealt " +
                            health.getMaxHP()/10 + " damage. " + enemy.name + " remaining hp: " + enemy.getHP() );
            }
        }
        else
            messageCallback.send("ability in cooldown");
    }

    @Override
    public void gameTick(InputReader inputReader,BoardController controller) {
        this.move(inputReader.read(),controller);
        remainCool = Math.max(remainCool-1,0);
    }
    private void heal(int amount)
    {
        health.heal(amount);
    }

    @Override
    public void visit(Player p) {}

    @Override
    public String printPlayer() {
        return String.format("Warrior - %s\t\tHealth: %s/%s\t\tAttack: %d\t\tDefense: %d\t\t Experience: %d/%d\t\t Cooldown: %d"
                , getName(), getHP(),getMaxHP(),getAttack(), getDefence(),EXP,(50*level),remainCool);
    }

}
