public class Trap extends Enemy{
    private int visibilityTime;
    private int inVisibilityTime;
    private int tickCount;
    private boolean visible;

    private final char trapChar ;

    public Trap(char character, String name, int HP, int AD, int defence,
                int EXPvalue, int visibilityTime, int inVisibilityTime) {
        super(character, name, HP, AD, defence, EXPvalue);
        this.visibilityTime = visibilityTime;
        this.inVisibilityTime = inVisibilityTime;
        tickCount = 0;
        visible = true;
        trapChar = character;
    }

    @Override
    public void gameTick(BoardController controller){
        visible = tickCount < visibilityTime;
        if (getRange(controller.getPlayer()) < 2){
           visit(controller.getPlayer());
        }
        if (visible)
            this.character = trapChar;
        else
            this.character = '.';
        if (tickCount == (visibilityTime + inVisibilityTime)) {
            tickCount = 0;
        }
        else
            tickCount = tickCount + 1;

    }
}


