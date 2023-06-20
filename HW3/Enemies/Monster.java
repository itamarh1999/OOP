import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class Monster extends Enemy{
    protected int visionRange;

    public Monster(char character, String name, int HP, int AD,
                   int defence, int EXPvalue, int visionRange) {
        super( character, name, HP, AD, defence, EXPvalue);
        this.visionRange = visionRange;
    }

        private void chasePlayer(Player player,BoardController controller)
        {
        int dx = this.position.getX() - player.position.getX();     //positive dx means player on the left
        int dy = this.position.getY() - player.position.getY();     //positive dy means player is down from me
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                interact(controller.getBoard().get(position.getX()-1,position.getY()));
            else
                interact(controller.getBoard().get(position.getX()+1,position.getY()));
        }
        else {
            if (dy > 0 )
                interact(controller.getBoard().get(position.getX(),position.getY()-1));
            else
                interact(controller.getBoard().get(position.getX(),position.getY()+1));
        }
    }

    private void moveRandomly(BoardController controller) {
        Random random = new Random();
        int index = random.nextInt(5);
        switch (index) {
            case 0: //up
                interact(controller.getBoard().get(position.getX() - 1, position.getY()));
                break;
            case 1: // down
                interact(controller.getBoard().get(position.getX() + 1, position.getY()));
                break;
            case 2: // left
                interact(controller.getBoard().get(position.getX(), position.getY() - 1));
                break;
            case 3: // right
                interact(controller.getBoard().get(position.getX(), position.getY() + 1));
                break;
            case 4:
                break;
        }

    }
    @Override
    public void gameTick(BoardController controller) {
        if (getRange(controller.getPlayer()) < visionRange){
            chasePlayer(controller.getPlayer(),controller);
        }
        else
            moveRandomly(controller);
    }}
