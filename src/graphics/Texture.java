package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Texture {
    public static Render floor = loadBitmap("resources/textures/floor.png");

    public static Render loadBitmap(String fileName) {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(fileName));
            int width = image.getWidth();
            int height = image.getHeight();
            Render result = new Render(width, height);
            image.getRGB(0, 0, width, height, result.pixels, 0, width);
            return result;
        } catch (Exception e) {
            System.out.println("Problem with loading texture");
            throw new RuntimeException(e);
        }
    }
}
