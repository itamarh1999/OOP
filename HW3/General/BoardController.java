import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoardController {
    private List<Tile> allTiles;


    private CLI cli;
    private TileFactory tileFactory;
    private List<List<String>> levels;
    private Player player;
    private GameBoard gameBoard;
    private List<Enemy> enemies;

    private DeathCallBack deathCallBackd;

    public BoardController(String path) {
        cli = new CLI(this);
        tileFactory = new TileFactory();
        levels = loadLevel(path);
        game();
    }

    Player setPlayer() {
        System.out.println("Select a Player:");
        List<Player> players = tileFactory.listPlayers();
        int index = 8;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println("Option " + (i + 1) + ": " + player.printPlayer());
        }
        while (true) {
            try {
                index = Integer.parseInt(cli.readLine()) - 1;
                while (index < 0 | index > 5) {
                    System.out.println("You chose purely...(insert number in the range 1-6)");
                    index = Integer.parseInt(cli.readLine()) - 1;
                }
                return tileFactory.producePlayer(index);
            } catch (NumberFormatException e) {
                System.out.println("You chose purely...(insert number in the range 1-6)");
            }
        }
    }

    public List<List<String>> loadLevel(String file) {
        File[] files = new File(file).listFiles();
        List<List<String>> level = new ArrayList<>();
        assert files != null;
        for (int i = 1; i <= files.length; i++) {
            for (File f : files) {
                if (f.isFile() && f.getName().contains("level" + i)) {
                    List<String> line = new ArrayList<>();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(f));
                        String temp;
                        while ((temp = reader.readLine()) != null)
                            line.add(temp);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not exist");
                    } catch (IIOException e) {
                        System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    level.add(line);
                }
            }
        }
        return level;
    }

    private Tile[][] setLevel(List<List<String>> charLevel, int index) {
        Map<Character, Tile> tiles;
        enemies = new ArrayList<>();
        Tile[][] level = new Tile[charLevel.get(index).size()][charLevel.get(index).get(0).length()];
        List<String> stringLevel = charLevel.get(index);
        for (int i = 0; i < stringLevel.size(); i++) {
            String line = stringLevel.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (tileFactory.enemyMap().containsKey(c)) {
                    Enemy enemy = tileFactory.produceEnemy(c);
                    enemy.setPosition(i, j);
                    enemies.add(enemy);
                    level[i][j] = enemy;
                } else {
                    tiles = tileFactory.tilesMap(player);
                    Tile tile = (tiles.get(line.charAt(j)));
                    tile.setPosition(i, j);
                    level[i][j] = tile;
                }
            }
        }
        return level;
    }

    private void game() {
        player = setPlayer();
        for (int i = 0; i < levels.size(); i++) {
            this.gameBoard = new GameBoard(setLevel(levels, i));
            this.allTiles = gameBoard.getBoard();
            gameBoard.printBoard();
            printPlayer();
            cli.displayMassage(printPlayer());
            while (enemies.size() > 0) {
                playLevel();
                gameBoard.printBoard();
                cli.displayMassage(printPlayer());
                if (!player.isAlive())
                    player.dead(this);
            }
        }
        System.out.println("YOU WIM");
    }

    private void playLevel() {
        player.gameTick(cli.getInputReader(), this);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (!enemy.isAlive()) {
                gameBoard.remove(enemy);
                enemies.remove(enemy);
            } else
                enemy.gameTick(this);
        }
    }

    public List<Tile> getAllTiles() {
        return allTiles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public GameBoard getBoard() {
        return gameBoard;
    }

    public String printPlayer() {
        return player.printPlayer();
    }

    public void enemyDeath(Enemy enemy) {
        gameBoard.remove(enemy); // also adds a empty tile instead of it.
    }

    public void playerDeath(Player player) {
        player.setCharacter('X');
        cli.displayMassage("GAME OVER");
        System.exit(0);
    }
}
