import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    protected Health health;
    protected int AD;
    protected int defence;
    protected MessageCallback messageCallback;
    public Unit( char character, String name, int HP, int AD, int defence) {
        super(character,new Position());
        this.name = name;
        this.health = new Health(HP);
        this.AD = AD;
        this.defence = defence;
        this.messageCallback = (s -> displayMassage(s));
    }

    public void displayMassage(String m) {
        System.out.println(m);
    }
    public void visit(Empty e) {
        position.swap(e.position);
    }
    public void visit(Wall w){}
    public void interact(Tile tile){
        tile.accept(this);
    }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public void combat(Unit unit){
//        unit.health.getHittedWithDefence(this.AD,unit.getDefence());
//        if (!health.alive())
//            unit.
    }


    public String getName(){return name;}
    public int getAttack(){ return AD;}
    public int getDefence(){return defence;}
    public int getHP(){return health.getHP();}
    public int getMaxHP(){return health.getMaxHP();}
    public String description(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHP(),getAttack(), getDefence() );
    }
    public int getHittedWithDefence(int damage){
        Random random = new Random();
        return health.getHittedWithDefence(random.nextInt(damage),random.nextInt(defence));
    }
    public boolean isAlive(){
        return health.alive();
    }

    @Override
    public String toString() {
        return character + " health - " + health.getHP();
    }
}
