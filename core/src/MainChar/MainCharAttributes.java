package MainChar;

public class MainCharAttributes {

    private int attack;

    private int defence;

    private int hp;
    public MainCharAttributes () {
        attack = 5;
        defence = 5;
        hp = 100;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}
