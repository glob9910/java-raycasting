package graphics;

import logic.Game;

public class Render3D extends Render {

    private int renderDistance = 3000;
    private double[] zBuffer;

    public Render3D(int width, int height) {
        super(width, height);
        zBuffer = new double[width*height];
    }

    // Notch's technique:
    public void floor(Game game) {

        double floorPosition = 8;
        double ceilingPosition = 24;
        double forward = game.getControls().z;
        double right = game.getControls().x;
        double up = game.getControls().y;

        double rotation = game.getControls().rotation;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);

        for(int y=0; y<height; y++) {
            double ceiling = (y - height/2.0) / height;

            double z = (floorPosition + up) / ceiling;

            if(ceiling < 0) {
                z = (ceilingPosition - up) / -ceiling;
            }

            for(int x=0; x<width; x++) {
                double depth = (x - width/2.0)/height;
                depth *= z;
                double xx = depth*cosine + z*sine;
                double yy = z*cosine - depth*sine;
                int xPix = (int)(xx + right);
                int yPix = (int)(yy + forward);
                zBuffer[x + y*width] = z;
                pixels[x + y*width] = Texture.floor.pixels[(xPix & 7) + (yPix & 7) * 8];

                if(z > 500) {
                    pixels[x + y*width] = 0;
                }
            }
        }
    }

    public void renderDistanceLimiter() {
        for(int i=0; i<width*height; i++) {
            int color = pixels[i];
            int brightness = (int)(renderDistance/zBuffer[i]);

            // brightness (0-255)
            if(brightness < 0)
                brightness = 0;
            if(brightness > 255)
                brightness = 255;

            int r = (color >> 16) & 0xff;   // <3
            int g = (color >> 8) & 0xff;
            int b = color & 0xff;

            r = r*brightness/255;
            g = g*brightness/255;
            b = b*brightness/255;

            pixels[i] = r << 16 | g << 8 | b;
        }
    }
}
