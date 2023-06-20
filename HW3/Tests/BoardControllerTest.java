import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class BoardControllerTest {

    @Test
    public void testLoadLevels() {
        // Arrange
        String filePath = "Levels";
        BoardController boardController = new BoardController(filePath);

        // Act
        List<List<String>> levels = boardController.loadLevel(filePath);

        // Assert
        assertNotNull(levels);
        assertEquals(3, levels.size());
        assertEquals(5, levels.get(0).size());
        assertEquals(7, levels.get(1).size());
        // Add more assertions to test the correctness of the loaded levels
    }

    @Test
    public void testSetPlayer() {
        // Arrange
        BoardController boardController = new BoardController("path/to/levels");

        // Act
        Player player = boardController.setPlayer();

        // Assert
        assertNotNull(player);
        // Add more assertions to test the correctness of the selected player
    }

    // Add more test methods for other functionalities of the BoardController class

}