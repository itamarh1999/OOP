import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.List;

public class Hunter extends Player{

    int range;
    int arrows;
    int tickCount;
    public Hunter(String name, int HP, int AD, int defence, int range) {
        super(name, HP, AD, defence);
        this.range = range;
        this.tickCount = 0;
        this.arrows = 10;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        arrows = arrows + (level * 10);
        AD = AD + (level * 2);
        defence = defence + level;
    }

    @Override
    public void gameTick(InputReader inputReader, BoardController controller) {
        if (tickCount == 10){
            arrows = arrows + level;
            tickCount = 0;
        }
        else
            tickCount++;
        super.gameTick(inputReader, controller);
    }

    @Override
    public void castAbility(List<Enemy> e) {
        if (arrows > 0 && !e.isEmpty()) {
            Enemy enemy = colsestEnemy(e);
            if (getRange(enemy) <= range){
                arrows = arrows -1;
                int damage = enemy.getHittedWithDefence(AD);
                messageCallback.send(name + " used special ability and hit " + enemy.getName() +
                        " and dealt " + damage + ". remaining hp: " + enemy.getHP());
                if (!enemy.isAlive())
                    gainEXP(enemy.getEXPvalue());
            }
            else
                messageCallback.send("missed Arrow... " + enemy.getName() +  " is not in the range");
        }
    }

    private Enemy colsestEnemy(List<Enemy> e) {
        double range1 = Integer.MAX_VALUE;
        Enemy temp = null;
        for(Enemy enemy :e){
            double newRange = getRange(enemy);
            if (newRange < range1) {
                range1 = newRange;
                temp = enemy;
            }
        }
        return temp;
    }

    @Override
    public String printPlayer() {
        return String.format("Hunter -%s\t\tLevel %s\t\tHealth: %s/%s\t\tAttack: %d\t\tDefense: %d\t\tExperience: %d/%d\t\tArrows: %d"
                , getName(),getLevel(), getHP(),getMaxHP(),getAttack(), getDefence(),EXP,(50*level),arrows);
    }
}
