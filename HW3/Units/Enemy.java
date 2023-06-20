public abstract class Enemy extends Unit {
    private int EXPvalue;

    public Enemy( char character, String name, int HP, int AD, int defence, int EXPvalue) {
        super(character, name, HP, AD, defence);
        this.EXPvalue = EXPvalue;
    }

    public int getEXPvalue() {
        return EXPvalue;
    }

    public abstract void gameTick(BoardController controller);

    @Override
    public void visit(Player p) {
        int damage = p.getHittedWithDefence(AD);
        messageCallback.send(name +" attacked " + p.getName() + "and dealt " + damage + " damage.");
    }

    @Override
    public void visit(Enemy e) {}

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public void getHitedPureDamage(int amount){
        health.getHitedPureDamage(amount);
    }

    public void onEnemyDeath() {

    }
}
