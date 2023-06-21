public abstract class Tile {
    protected char character;
    protected Position position;

    public Tile(char character,Position position) {
        this.character = character;
        this.position = new Position();
    }

    public Double getRange(Tile t){
      return position.getRange(t);
    }

    public abstract void accept(Unit unit);

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }

}

