import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{
    protected int mana;
    protected int manaPool;
    protected int manaCost;
    protected int AP;
    protected int hitsCount;
    protected int abilityRange;

    public Mage(String name, int HP, int AD, int defence,
                int manaPool, int manaCost, int AP, int hitsCount, int abilityRange) {
        super(name, HP, AD, defence);
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.AP = AP;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        mana = this.manaPool/4;
    }

    public void levelUp(){
        super.levelUp();
        manaPool = manaPool + 25;
        mana = Math.min(mana + (manaPool/4), manaPool);
        AP = AP + 10;
    }
    public void gameTick(InputReader inputReader,BoardController controller){
        super.gameTick(inputReader,controller);
        mana = Math.min(manaPool, mana + level);
    }

    public void castAbility(List<Enemy> enemies){
        if (mana < manaCost)
            messageCallback.send("not enough mana for ability");
        else {
            mana = mana - manaCost;
            int hits = 0;
            ArrayList<Enemy> enemiesInRange = enemiesInRange(enemies);//get all the enemies within ability range
            Random random = new Random();
            while ((hits < hitsCount) & enemiesInRange.size()>0) {
                int toAttack = random.nextInt(enemiesInRange.size());
                Enemy enemy = enemiesInRange.get(toAttack);
                enemy.getHitedPureDamage(AP);
                if (!enemy.isAlive()) {
                    gainEXP(enemy.getEXPvalue());
                    enemiesInRange.remove(enemy);
                    messageCallback.send(name + " used special ability and hit " + enemy.name + " and dealt " + AP +
                            " damage. " + enemy.name + " died" );
                }
                else
                    messageCallback.send(name + " used special ability and hit " + enemy.name + " and dealt " + AP +
                            " damage. " + enemy.name + " remaining hp: " + enemy.getHP() );
                hits++;
            }
        }
    }
    @Override
    public String printPlayer() {
        return String.format("Mage - %s\t\tLevel %s\t\tHealth: %s/%s\t\tAttack: %d\t\tSpell Power: %d\t\tDefense: %d\t\t Experience: %d/%d\t\t Mana: %d"
                , getName(),getLevel(), getHP(),getMaxHP(),getAttack(),AP, getDefence(),EXP,(50*level),mana);
    }
}

