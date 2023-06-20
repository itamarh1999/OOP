import java.io.File;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Path");
//        String path = scanner.nextLine();
        String path = "/Users/sriftin/Library/Mobile Documents/com~apple~CloudDocs/תואר/סימסטר ב׳/תכנות מונחה עצמים/עבודות/עבודה 3/levels_dir 3";
        BoardController controller = new BoardController(path);
        CLI cli = new CLI(controller);
    }
}