import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private List<Tile> board;
    private int sizeX;
    private int sizeY;
    public GameBoard(Tile[][] board){
        this.sizeX = board.length;
        this.sizeY = board[0].length;
        this.board = new ArrayList<>();
        for(Tile[] line : board){
            this.board.addAll(Arrays.asList(line));
        }
    }
    public Tile get(int x, int y) { //if cant get a position return null
        Position p = new Position(x,y);
        for(Tile t : board){
            if (t.getPosition().equals(p)){
                return t;
            }
        }
        return null;
    }
    public void remove(Enemy e) {
        board.remove(e);
        Position p = e.getPosition();
        Empty empty = new Empty();
        empty.setPosition(p.getX(), p.getY());
        board.add(empty);
    }

    public void printBoard(){
        char [][] boardChars = new char[sizeX][sizeY];
        for (Tile t : board)
        {
            boardChars[t.getPosition().getX()][t.getPosition().getY()] = t.getCharacter();
        }
        for (int i = 0; i < boardChars.length; i++) {
            for (int j = 0; j < boardChars[0].length; j++) {
                System.out.print(boardChars[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public List<Tile> getBoard() {
        return board;
    }
}