import java.util.List;
import java.util.Scanner;

public class CLI {
    private MessageCallback messageCallback;
    private InputReader inputReader;
    private BoardController controller;


    public CLI(BoardController controller) {
        this.controller = controller;
        this.messageCallback = (s -> displayMassage(s));
        this.inputReader = () -> readLine();
    }

    public void displayMassage(String m) {
        System.out.println(m);
    }

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public InputReader getInputReader() {
        return inputReader;
    }

    //    private void play(InputReader inputReader) {
//        myPlayer.gameTick(inputReader);
//        for (Enemy enemy: boardController.getEnemies()) {
//            enemy.gameTick();
//        }
//        gameBoard.printBoard();
//    }
}

