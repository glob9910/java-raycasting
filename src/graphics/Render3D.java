package graphics;

import logic.Game;

public class Render3D extends Render {

    public Render3D(int width, int height) {
        super(width, height);
    }

    // Notch's technique:
    public void floor(Game game) {

        double floorPosition = 8;
        double ceilingPosition = 24;
        double forward = game.getControls().z;
        double right = game.getControls().x;

        double rotation = game.getControls().rotation;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);

        for(int y=0; y<height; y++) {
            double ceiling = (y - height/2.0)/height;

            double z = floorPosition/ceiling;

            if(ceiling < 0) {
                z = ceilingPosition/-ceiling;
            }

            for(int x=0; x<width; x++) {
                double depth = (x - width/2.0)/height;
                depth *= z;
                double xx = depth*cosine + z*sine + right;
                double yy = z*cosine - depth*sine + forward;
                int xPix = (int)xx;
                int yPix = (int)yy;
                pixels[x + y*width] = (((xPix & 15) * 16) | ((yPix & 15) * 16)) << 1;   // 8 - green, 16 - red, 0 - blue
            }
        }
    }
}
