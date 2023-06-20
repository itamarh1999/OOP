import java.util.Random;

public class Health {
    private int HP;
    private int maxHP;

    public Health(int HP){//לא צריך MaxHP כי הם מתחלילם אותו הדבר
        this.HP = HP;
        this.maxHP = HP;
    }
    public void getHitedPureDamage(int damage)
    {
        this.HP = this.HP - damage;
    }
    public int getHittedWithDefence(int damage, int defence)
    {
        if (damage > defence)
            this.HP = this.HP - (damage - defence);
        return Math.max(0,damage-defence);
    }

    public void levelUp(int level){
        maxHP = maxHP + 10 * level;
        HP = maxHP;
    }

    public void warriorLevelUP(int level){
        maxHP = maxHP + (level * 5);
    }


    public boolean alive(){
        return HP > 0;
    }
    public int getHP(){return HP;}
    public int getMaxHP(){return maxHP;}
    public void heal(int amount){
        HP =Math.min(maxHP,HP+amount);
    }
}
