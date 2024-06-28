package logic;

import input.Controller;

import java.awt.event.KeyEvent;

public class Game {

    private Controller controls;
    private int time = 0;

    public Game() {
        controls = new Controller();
    }

    public void tick(boolean[] key) {
        time++;
        boolean forward = key[KeyEvent.VK_W];
        boolean back = key[KeyEvent.VK_S];
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];

        controls.tick(forward, back, left, right);
    }

    public int getTime() {
        return time;
    }

    public Controller getControls() {
        return controls;
    }
}
