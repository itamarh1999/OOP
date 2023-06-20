import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileFactory {
    private List<Supplier<Player>> playersList;
    private Map<Character, Supplier<Enemy>> enemiesList;
    private MessageCallback messageCallback;

    public TileFactory(){
        playersList = initPlayers();
        enemiesList = initEnemies();
        this.messageCallback = (s -> displayMassage(s));
    }
    public void displayMassage(String m) {
        System.out.println(m);
    }
    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3,25, 3),//0
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4),//1
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5),//2
                () -> new Monster('z', "Wright", 600, 30, 15,100, 3),//3
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250,  4),//4
                () -> new Monster('g', "Giant-Wright",1500, 100, 40,500,   5),//5
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),//6
                () -> new Monster('M',"The Mountain",1000,60,25,6,500),//7
                () -> new Monster('C',"Queen Cersei",100,10,10,1,1000),//8
                () -> new Monster('K',"Night’s King",5000,300,150,8,5000),//9
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 5),//10
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7),//11
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10)//12
        );
        return enemies.stream().collect(Collectors.toMap(s -> s.get().character, Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4,3),
                () -> new Warrior("The Hound", 400, 20,  6,5),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 40,  2, 20),
                () -> new Rogue("Bronn", 250, 35, 3, 50)
        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }

    public Player producePlayer(int idx){
        return listPlayers().get(idx);
    }
    // TODO: Add additional callbacks of your choice

    public Map<Character, Enemy> enemyMap() {
        Map<Character, Enemy> enemyMap = new HashMap<>();
        for (Map.Entry<Character, Supplier<Enemy>> entry : initEnemies().entrySet()) {
            enemyMap.put(entry.getKey(), entry.getValue().get());
        }
        return enemyMap;
    }


    public Enemy produceEnemy(char c) {
        Map<Character, Enemy> enemyMap = enemyMap();
        return enemyMap.get(c);
    }

    public Map<Character,Tile> tilesMap(Player player) {
        Map<Character,Tile> tiles = new HashMap<>();
        tiles.put('.',new Empty());
        tiles.put('#', new Wall());
        tiles.put('@',player);
        return tiles;
    }
//    public Empty produceEmpty(Position position, ...){
//        ...
//    }
//
//    public Wall produceWall(Position position, ...){
//        ...
//    }
}
