public class Empty extends Tile
{
    public Empty()
    {
        super('.',new Position());
    }
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
