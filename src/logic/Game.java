package logic;

public class Game {

    private int time = 0;

    public void tick() {
        time++;
    }

    public int getTime() {
        return time;
    }
}
