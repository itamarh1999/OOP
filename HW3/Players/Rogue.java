import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rogue extends Player {
    private int energy;
    private int maxEnergy;
    private int abilityCost;

    public Rogue(String name, int HP, int AD, int defence, int abilityCost) {
        super(name, HP, AD, defence);
        this.abilityCost = abilityCost;
        maxEnergy = 100;
        energy = 100;
    }

    public void levelUp()
    {
        super.levelUp();
        energy = 100;
        AD = AD + (level * 3);
    }
    public void castAbility(List<Enemy> enemies){
        if (energy<abilityCost)
            messageCallback.send("not enough energy");
        else {
            energy = energy - abilityCost;
            ArrayList<Enemy> enemiesInRange = enemiesInRange(enemies);
            for (Enemy e: enemiesInRange) {
                int damage = e.getHittedWithDefence(AD);
                if (!e.isAlive()){
                    gainEXP(e.getEXPvalue());
                messageCallback.send(name + " used special ability and hit " + e.name + " and dealt " + damage +
                        " damage. " + e.name + " died" );
                }
                else
                    messageCallback.send(name + " used special ability and hit " + e.name + " and dealt " + damage +
                            " damage. " + e.name + " remaining hp: " + e.getHP() );
            }

        }
    }
    public void gameTick(InputReader inputReader, BoardController controller) {
        super.gameTick(inputReader,controller);
        energy = Math.min(energy + 10, maxEnergy);

    }

    @Override
    public String printPlayer() {
        return String.format("Rouge - %s\t\tHealth: %s/%s\t\tAttack: %d\t\tDefense: %d\t\t Experience: %d/%d\t\t Energy: %d"
                , getName(), getHP(),getMaxHP(),getAttack(), getDefence(),EXP,(50*level),energy);
    }
}
