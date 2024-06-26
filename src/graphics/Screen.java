package graphics;
import logic.Game;


import java.util.Random;

public class Screen extends Render {

    private Render test;

    public Screen(int width, int height) {
        super(width, height);

        // test:
        Random random = new Random();
        test = new Render(256, 256);
        for(int i=0; i<256*256; i++) {
            test.pixels[i] = random.nextInt();
        }
    }

    public void render(Game game) {

        for(int i=0; i<width*height; i++) {
            pixels[i] = 0;
        }

        int anim = (int)(Math.sin(game.getTime()%1000.0/1000*Math.PI*2)*200);
        int anim2 = (int)(Math.cos(game.getTime()%1000.0/1000*Math.PI*2)*200);

        draw(test, (width/2)+anim, (height/2)-anim2);
    }
}
