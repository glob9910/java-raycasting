package logic;

import graphics.Screen;
import input.Controller;
import input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.awt.image.DataBufferInt;

public class Display extends Canvas implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D graphics test";

    private Thread thread;
    private boolean running = false;
    private Screen screen;
    private Game game;
    private InputHandler input;
    private BufferedImage img;
    private int[] pixels;
    private int newX = 0;
    private int oldX = 0;

    public Display() {

        BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "blank");
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        screen = new Screen(WIDTH, HEIGHT);
        game = new Game();
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

        input = new InputHandler();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);

        JFrame frame = new JFrame();
        frame.add(this);
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setCursor(blank);
        frame.pack();
        frame.setVisible(true);

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
        int frames = 0;
        long last = System.nanoTime();

        while(running){
            tick();
            render();

            frames++;
            long now = System.nanoTime();
            if(now-last > 1000000000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                last = now;
            }

            newX = InputHandler.MouseX;
            if(newX > oldX)
                Controller.turnRight = true;
            if(newX < oldX)
                Controller.turnLeft = true;
            if(newX == oldX) {
                Controller.turnLeft = false;
                Controller.turnRight = false;
            }
            oldX = newX;
        }


    }

    private void tick() {
        game.tick(input.getKey());
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render(game);

        for(int i=0; i<WIDTH*HEIGHT; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
    }
}
