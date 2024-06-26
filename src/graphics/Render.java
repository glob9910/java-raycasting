package graphics;

// Main render class
public class Render {

    public final int width;
    public final int height;
    public final int[] pixels;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
    }

    public void draw(Render render, int xOffset, int yOffset) {

        for(int y=0; y<render.height; y++) {
            int yPix = y+yOffset;


            for(int x=0; x<render.width; x++) {
                int xPix = x + xOffset;

                if(yPix<0 || yPix>=height || xPix<0 || xPix>=width)
                    continue;

                int alpha = render.pixels[x + y * render.width]; // alpha helps with transparency and boosts performance
                if (alpha > 0) {
                    pixels[xPix + yPix * width] = alpha;
                }
            }
        }
    }
}
