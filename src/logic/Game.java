package logic;

public class Game {

    private int time = 0;
    private int speed = 3;

    public void tick() {
        time+=speed;
    }

    public int getTime() {
        return time;
    }
}
