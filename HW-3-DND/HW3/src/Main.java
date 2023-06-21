import java.io.File;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
//        String path = args[0];
        String path = "HW3/Levels";
        BoardController controller = new BoardController(path);
        CLI cli = new CLI(controller);
    }
}