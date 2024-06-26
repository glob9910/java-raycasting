import graphics.Render;
import graphics.Screen;

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
    private BufferedImage img;
    private int[] pixels;

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

        //render = new Render(WIDTH, HEIGHT);
        screen = new Screen(WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

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
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();

        for(int i=0; i<WIDTH*HEIGHT; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
    }
}
