import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Unit {
    protected int level;
    protected int EXP;

    public Player(String name, int HP, int AD, int defence) {
        super('@', name, HP, AD, defence);
        this.level = 1;
        this.EXP = 0;
    }

    public void levelUp(){
        if (EXP >= level*50) {
            EXP = EXP - level * 50;
            level = level + 1;
            health.levelUp(level);
            AD = AD + (4 * level);
            defence = defence + level;
        }
    };
    public void move(String move, BoardController controller){
        List<Tile> allTiles = controller.getAllTiles();
        switch (move){
            case "w"://Move UP
                interact(controller.getBoard().get(position.getX()-1,position.getY()));
                break;
            case "s"://Move Down
                interact(controller.getBoard().get(position.getX()+1,position.getY()));
                break;
            case "a"://Move Left
                interact(controller.getBoard().get(position.getX(),position.getY()-1));
                break;
            case "d"://Move Right
                interact(controller.getBoard().get(position.getX(),position.getY()+1));
                break;
            case "e"://Cast Special Ability
                castAbility(controller.getEnemies());
                break;
            case"q"://Do Nothing
                break;
            default:
                System.out.println("press arrow dude or lady");
        }
    }


    public abstract void castAbility(List<Enemy> e);
    public abstract void gameTick(InputReader inputReader,BoardController controller);

    public void visit(Enemy e) {
        int damage = e.getHittedWithDefence(AD);
        if(!e.isAlive()){
            gainEXP(e.getEXPvalue());
            position.swap(e.position);
            messageCallback.send(name + " attacked " + e.name + " and dealt " + damage + "damage. " + e.name + " died" );
        }
        else
            messageCallback.send(name + " attacked " + e.name + " and dealt " + damage + "damage. " + e.name + " remaining hp: " + e.getHP() );
    }
    public void visit(Player p){}

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    protected ArrayList<Enemy> enemiesInRange(List<Enemy> enemies) {
        ArrayList<Enemy> enemiesInRange = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            if (getRange(e) < 3)
                enemiesInRange.add(e);
        }
        return enemiesInRange;
    }
    public void gainEXP(int amount){
        this.EXP += amount;
        while (EXP >= 50 * level)
            levelUp();
    }
    public void dead(BoardController controller){
        controller.playerDeath(this);
    }

    public abstract String printPlayer();
}
