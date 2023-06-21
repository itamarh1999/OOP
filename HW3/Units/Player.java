import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Unit implements HeroicUnit {
    protected int level;
    protected int EXP;

    public Player(String name, int HP, int AD, int defence) {
        super('@', name, HP, AD, defence);
        this.level = 1;
        this.EXP = 0;
    }

    public void levelUp(){
        EXP = EXP - level * 50;
        level = level + 1;
        health.levelUp(level);
        AD = AD + (4 * level);
        defence = defence + level;
    };
    public void move(String move, GameBoard board , List<Enemy> enemies){
        switch (move){
            case "w"://Move UP
                interact(board.get(position.getX()-1,position.getY()));
                break;
            case "s"://Move Down
                interact(board.get(position.getX()+1,position.getY()));
                break;
            case "a"://Move Left
                interact(board.get(position.getX(),position.getY()-1));
                break;
            case "d"://Move Right
                interact(board.get(position.getX(),position.getY()+1));
                break;
            case "e"://Cast Special Ability
                castAbility(enemies);
                break;
            case"q"://Do Nothing
                break;
        }
    }


    public abstract void castAbility(List<Enemy> e);
    public void gameTick(InputReader inputReader,BoardController controller){
        String order = inputReader.read();
        while (!order.equals("a") && !order.equals("s") && !order.equals("d")
                && !order.equals("w") && !order.equals("e") && !order.equals("q")) {
            messageCallback.send("illegal command please use w,a,s,d to move , " +
                    "e for special ability or q to stay in place");
            order = inputReader.read();
        }
        move(order,controller.getBoard(),controller.getEnemies());
    }

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
        setCharacter('X');
        controller.playerDeath(this);
    }

    public int getLevel() {
        return level;
    }

    public abstract String printPlayer();
}
