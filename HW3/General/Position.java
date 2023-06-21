import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Double getRange(Tile t){
        int distY = getX() - t.position.getX();
        int distX = getY() - t.position.getY();
        return Math.sqrt((distY * distY) + (distX * distX));
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Position){
            return this.x == ((Position) other).x && this.y == ((Position) other).y;
        }
        return false;
    }
    public void swap(Position p){
        int tempX = p.getX();
        int tempY = p.getY();
        p.setX(x);
        p.setY(y);
        this.x = tempX;
        this.y = tempY;
    }
}
