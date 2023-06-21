public class Wall extends Tile {
    public Wall()
    {
        super('#',new Position());
    }


    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
