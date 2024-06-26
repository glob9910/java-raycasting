import graphics.Render;

import javax.swing.*;
import java.awt.*;

public class Display extends Canvas implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D graphics test";

    private Thread thread;
    private boolean running = false;
    private Render render;

    public Display() {
        JFrame frame = new JFrame();
        frame.add(this);
        //frame.pack();
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        //frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        render = new Render(WIDTH, HEIGHT);

        System.out.println("Running...");
        this.start();
    }

    private void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void run() {
        while(running){
            tick();
            render();
        }
    }

    private void tick() {

    }

    private void render() {

    }
}
